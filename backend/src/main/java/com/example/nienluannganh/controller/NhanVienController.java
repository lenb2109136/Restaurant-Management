package com.example.nienluannganh.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.nienluannganh.DTO.CaItem;
import com.example.nienluannganh.DTO.CaLamNhanVienDTO;
import com.example.nienluannganh.model.NhanVien;
import com.example.nienluannganh.model.ThoiGianLamCa;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.repository.ThoiGianLamCaRepository;
import com.example.nienluannganh.service.NhanVienService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/nhanvien")
public class NhanVienController {
	@Autowired
	private NhanVienService nhanVienService;
	@Autowired
	private ThoiGianLamCaRepository thoiGianLamCaRepository;
	@GetMapping("/getall")
	public ResponseEntity<response> getall(){
	return new ResponseEntity(new response(HttpStatus.OK, "ok", nhanVienService.getallnhanvien()),HttpStatus.OK);
				}
	
	@GetMapping("/tieuchi")
	public ResponseEntity<response> tieuchi(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "id", required = false) Integer id){
		response r;
		if(id==null) id=0;
		if(name==null) name="";
		r= new response(HttpStatus.OK,"ok",nhanVienService.tieuchi(name, id));
		return new ResponseEntity(new response(HttpStatus.OK,"ok",r),HttpStatus.OK);
				
	}
	
	@PostMapping("/save")
	public ResponseEntity<response> saveNhanVien(
			@ModelAttribute @Valid NhanVien nv,
			@RequestParam("cv") int cvid,
			@RequestParam("bp") Integer bpid,
			@RequestParam(name = "anh",required =false) MultipartFile file
			) throws Exception{
		if(bpid==null||bpid==0) {
			throw new Exception("Không tìm thấy chức vụ phù hợp");
		}
		nhanVienService.savenhanvien(nv, cvid,bpid, file);
		return new ResponseEntity( new response(HttpStatus.OK,"ok",null),HttpStatus.OK);
	}
	@PostMapping("/update")
	public ResponseEntity<response> update(
			@ModelAttribute @Valid NhanVien nv,
			@RequestParam("cv") int cvid,
			@RequestParam(name = "bp",required = false) Integer bpid,
			@RequestParam(name = "anh",required =false) MultipartFile file
			) throws Exception{
		if(bpid==null||bpid==0) {
			throw new Exception("Không tìm thấy chức vụ phù hợp");
		}
		nhanVienService.update(nv, cvid,bpid, file);
		return new ResponseEntity( new response(HttpStatus.OK,"ok",null),HttpStatus.OK);
	}
	
	@PostMapping("/getttca")
	public ResponseEntity<response> getthongthongtinca(
				@RequestParam("id") int id,
				@RequestParam("ngaybd") String nbd,
				@RequestParam("ngaykt") String nkt
			) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 LocalDate b= LocalDate.parse(nbd,formatter);
		 LocalDate k= LocalDate.parse(nkt,formatter);
	return	new ResponseEntity(new response(HttpStatus.OK,"ok",nhanVienService.getcalamnhanvien(id, b,k)),HttpStatus.OK);
	}
	@PostMapping("/g")
	public ResponseEntity<response> getcalamtrongtuan(
				@RequestParam("id") int id,
				@RequestParam("ngaybd") String nbd,
				@RequestParam("ngaykt") String nkt
			) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 LocalDate b= LocalDate.parse(nbd,formatter);
		 LocalDate k= LocalDate.parse(nkt,formatter);
	return	new ResponseEntity(new response(HttpStatus.OK,"ok",nhanVienService.getcalamtrongtuan(id, b,k)),HttpStatus.OK);
	}
	
	@PostMapping("/phanca")
	public ResponseEntity<response> phanca(@RequestBody @Valid CaLamNhanVienDTO dto) throws Throwable{
		nhanVienService.phancong(dto);
		return new ResponseEntity( new response(HttpStatus.OK,"ok",null),HttpStatus.OK);
	}
	@GetMapping("/tudong")
	public void tudong(@RequestParam("id") int id) {
		nhanVienService.tudong(id);
	}
	@GetMapping("/gettudong")
	public ResponseEntity<response> gettudong(@RequestParam("id") int id) {
		
		return new ResponseEntity(new response(HttpStatus.OK,"ok", nhanVienService.gettudong(id)),HttpStatus.OK);
	}
	@PostMapping("xoaca")
	public ResponseEntity<response> xoaca(
			@RequestParam("ngay") LocalDate ngay,
			@RequestParam("idnv") int idnv,
			@RequestParam("idca") int idca
			){
	ThoiGianLamCa t=	thoiGianLamCaRepository.get(idnv, idca, ngay);
		if(t!=null) {
			thoiGianLamCaRepository.delete(t);
		}
		else {
			throw new EntityNotFoundException("Không tìm thấy ca đã phân công");
		}
		return new ResponseEntity<response>(new response(HttpStatus.OK, null, ngay),HttpStatus.OK);
	}
	@GetMapping("/getnhanvinbychucvu")
	public ResponseEntity<response> getnhanvinbychucvu(@RequestParam("cvid") int cvid, @RequestParam("bpid") int bpid){
		return new ResponseEntity<response>(new response(HttpStatus.OK, "ok",nhanVienService.g(cvid, bpid)),HttpStatus.OK);
	}
	
	@GetMapping("/getnhanvinbychucv")
	public ResponseEntity<response> getnhanvinbychucvu( @RequestParam("bpid") int bpid){
		return new ResponseEntity<response>(new response(HttpStatus.OK, "ok",nhanVienService.g2(bpid)),HttpStatus.OK);
	}
	
	@PostMapping("/themcahangloattheoca")
	public ResponseEntity<response> capnhathang(
			@RequestParam("dsnv")  String dsnv,
			@RequestParam("batdau") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate bd, 
			@RequestParam("ketthuc") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate ketthuc, 
			@RequestParam("id") int id ) throws Exception {
			ObjectMapper objectMapper = new ObjectMapper();
			List<Integer> d =null;
	        try {
				d= objectMapper.readValue(dsnv, objectMapper.getTypeFactory().constructCollectionType(List.class, Integer.class));
				} catch (Throwable e) {
				throw new Exception("Danh sách nhân viên gửi đến không hợp lệ");
			}
	        nhanVienService.updatehangloat(d, bd, ketthuc, id);
	        return new ResponseEntity<response>(new response(HttpStatus.OK,"ok", null),HttpStatus.OK);
	}
	
	@PostMapping("/daoca")
	public ResponseEntity<response> capnhathan(
			@RequestParam("dsnv")  String dsnv,
			@RequestParam("batdau") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate bd, 
			@RequestParam("ketthuc") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate ketthuc, 
			@RequestParam("id") int id ) throws Exception {
			ObjectMapper objectMapper = new ObjectMapper();
			List<Integer> d =null;
	        try {
				d= objectMapper.readValue(dsnv, objectMapper.getTypeFactory().constructCollectionType(List.class, Integer.class));
				} catch (Throwable e) {
				throw new Exception("Danh sách nhân viên gửi đến không hợp lệ");
			}
	        nhanVienService.daoca(d, bd, ketthuc, id);
	        return new ResponseEntity<response>(new response(HttpStatus.OK,"ok", null),HttpStatus.OK);
	}
	
}
