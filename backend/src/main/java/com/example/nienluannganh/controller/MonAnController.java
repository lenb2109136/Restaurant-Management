package com.example.nienluannganh.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.nienluannganh.DTO.giDinhMucDTO;
import com.example.nienluannganh.model.GiaMonAn;
import com.example.nienluannganh.model.KhuyenMaiGioVangMonAn;
import com.example.nienluannganh.model.KhuyenMaiMonAn;
import com.example.nienluannganh.model.LoaiMonAn;
import com.example.nienluannganh.model.MonAn;
import com.example.nienluannganh.model.embededid.KhuyenMaiGioVangMonAnId;
import com.example.nienluannganh.model.embededid.KhuyenMaiMonAnId;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.repository.KhuyenMaiGioVangMonAnRepositr;
import com.example.nienluannganh.repository.KhuyenMaiMonAnRepository;
import com.example.nienluannganh.service.GiaMonAnService;
import com.example.nienluannganh.service.LoaiMonAnService;
import com.example.nienluannganh.service.MonAnService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/monan")
public class MonAnController {
	@Autowired
	private MonAnService monAn;
	
	@Autowired
	private GiaMonAnService giaMonAnService;
	@Autowired
	private KhuyenMaiGioVangMonAnRepositr khuyenMaiGioVangMonAnRepositr;
	
	@Autowired
	private KhuyenMaiMonAnRepository khuyenMaiMonAnRepository;
	
	@Autowired
	private LoaiMonAnService lma;
	@Value("${app.nameSource2}")
	
	private String resource;
		@GetMapping("/byloai")
		public ResponseEntity<MonAn> getMonAnByLoai(@RequestParam("idloai") int idloai){
			response k= new response(HttpStatus.OK,"ok",monAn.getMonAnByLoai(idloai));
			return new ResponseEntity(k,HttpStatus.OK);
		}
		
		@GetMapping("/ngungsudung")
		public ResponseEntity<response> ngung(@RequestParam("idloai") int id){
			MonAn m= monAn.getMonAnById(id).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy món ăn cần thay đổi"));
			m.setTinhTrangSuDung(false);
			monAn.saveMonAn(m);
			response r= new response(HttpStatus.OK,"Món ăn đã ngưng cung cấp",null);
			return new ResponseEntity(r,HttpStatus.OK);
		}
		
		@PostMapping("/themmonan")
		public ResponseEntity<response> themmonan(
				@Valid @ModelAttribute MonAn m,
				@RequestParam(value="anh", required = false) MultipartFile file,
				@RequestParam(value ="maloai", required = true) int maloai,
				@RequestParam(value = "giaa") String gia
				) throws Throwable {
				    monAn.themmonan(m, file, maloai, gia);
				    response r= new response(HttpStatus.OK,"Thành công",m);
				    return new ResponseEntity(r,HttpStatus.OK);
		}
		
		@PostMapping("/updatemonan")
		public ResponseEntity<response> updatenonan(
				@Valid @ModelAttribute MonAn m,
				@RequestParam(value="anh", required = false) MultipartFile file,
				@RequestParam ("giaa") String giaa
				) throws IOException {
			
			monAn.updateMonAn(m, file,giaa);
			response r= new response(HttpStatus.OK,"Lưu dữ liệu thành công",m);
			return new ResponseEntity(r,HttpStatus.OK);
		}
		@GetMapping("/getall")
		public ResponseEntity<response> getall(){
			response r= new response(HttpStatus.OK,"ok",monAn.getallmonan());
			return new ResponseEntity(r,HttpStatus.OK);
		}
		
		@GetMapping("/getkhuyenmai")
		public ResponseEntity<response> getkm(@RequestParam("id") int id){
			List<Map<Object, Object>> kmtt=khuyenMaiMonAnRepository.getkmma(id);
			List<Map<Object, Object>> kmgv=khuyenMaiGioVangMonAnRepositr.getkmma(id);
			Float u= khuyenMaiMonAnRepository.tongkm(id);
			Map<Object, Object> map=new HashMap<>();
			map.put("tong", u);
			map.put("kmgv",kmgv);
			map.put("kmtt",kmtt);
			return new ResponseEntity<response>(new response(HttpStatus.OK, "ok", map),HttpStatus.OK);
		}
		
		@GetMapping("ngungkmtt")
		public ResponseEntity<response> ngungkmtt(@RequestParam("idma") int idma,@RequestParam("idkm") int idkm){
			KhuyenMaiMonAnId id= new KhuyenMaiMonAnId();
			id.setKM_ID(idkm);
			id.setMA_ID(idma);
			KhuyenMaiMonAn k=	khuyenMaiMonAnRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Món anư không còn áp dụng khuyến mãi"));
			khuyenMaiMonAnRepository.delete(k);
			return new ResponseEntity<response>(new response(HttpStatus.OK, "Đã ngưng sử dụng khuyến mãi",null),HttpStatus.OK);
			
		}
		@GetMapping("ngungkmgv")
		public ResponseEntity<response> ngungkmgv(@RequestParam("idma") int idma,@RequestParam("idkm") int idkm){
			KhuyenMaiGioVangMonAnId id= new KhuyenMaiGioVangMonAnId();
			id.setKM_ID(idkm);
			id.setMA_ID(idma);
			KhuyenMaiGioVangMonAn k=	khuyenMaiGioVangMonAnRepositr.findById(id).orElseThrow(()-> new EntityNotFoundException("Món anư không còn áp dụng khuyến mãi"));
			khuyenMaiGioVangMonAnRepositr.delete(k);
			return new ResponseEntity<response>(new response(HttpStatus.OK, "Đã ngưng sử dụng khuyến mãi",null),HttpStatus.OK);
			
		}
}
