package com.example.nienluannganh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.nienluannganh.DTO.MonAnComBoItem;
import com.example.nienluannganh.DTO.ThucUongComBoItem;
import com.example.nienluannganh.exception.exception;
import com.example.nienluannganh.model.ComBo;
import com.example.nienluannganh.model.KhuyenMaiComBo;
import com.example.nienluannganh.model.KhuyenMaiGioVangComBo;
import com.example.nienluannganh.model.KhuyenMaiGioVangThucUong;
import com.example.nienluannganh.model.KhuyenMaiThucUong;
import com.example.nienluannganh.model.embededid.KhuyenMaiComBoId;
import com.example.nienluannganh.model.embededid.KhuyenMaiGioVangComBoId;
import com.example.nienluannganh.model.embededid.KhuyenMaiGioVangThucUongId;
import com.example.nienluannganh.model.embededid.KhuyenMaiThucUongId;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.repository.ComBoRepository;
import com.example.nienluannganh.repository.ComBoThucUongRepository;
import com.example.nienluannganh.repository.KhuyenMaiComBoRepository;
import com.example.nienluannganh.repository.KhuyenMaiGioVangComboReposito;
import com.example.nienluannganh.service.ComBoMonAnService;
import com.example.nienluannganh.service.ComBoService;
import com.example.nienluannganh.service.ComBoThucUongService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/combo")
public class ComboController {
	@Autowired
	private ComBoService comBoService;
	@Autowired
	private ComBoMonAnService comBoMonAnService;
	
	@Autowired
	private KhuyenMaiComBoRepository khuyenMaiComBoRepository;
	@Autowired
	private KhuyenMaiGioVangComboReposito khuyenMaiGioVangComboReposito;
	
	@Autowired
	private ComBoThucUongService comBoThucUongService;
		@GetMapping("/getbyloai")
		public ResponseEntity<response> getByLoai(@RequestParam(name = "idloai",required = false) Integer idloai) throws Exception{
			if(idloai==null||idloai==0) {
				throw new Exception("Vui lòng cho biết thông tin loại");
			}
			List a= comBoService.getDsComboByLoai(idloai);
			response r= new response(HttpStatus.OK,"ok", a);
			return new 	ResponseEntity(r,HttpStatus.OK);
		}
		
		@PostMapping("/save")
		public ResponseEntity<response> save(
				@Valid @ModelAttribute ComBo c,
				@RequestParam(value = "anh",required = false) MultipartFile file,
				@RequestParam(value ="loaicombo",required = false) Integer loai,
				@RequestPart(value ="dsma",required = false) String dsma,
				@RequestPart(value ="dstu",required = false) String dstu,
				@RequestParam(value = "giaa") String gia
				
				) throws Exception {
			List<MonAnComBoItem> l;
			List<ThucUongComBoItem> l1;
			ObjectMapper o= new ObjectMapper();
			try {
				l= o.readValue(dsma, new TypeReference<List<MonAnComBoItem>>() {});
				l1=o.readValue(dstu, new TypeReference<List<ThucUongComBoItem>>() {});
			} catch (JsonMappingException e) {
				e.printStackTrace();
				throw new Exception("Danh sách món ăn không hợp lệ");
			} catch (JsonProcessingException e) {
				throw new Exception("Danh sách món ăn không hợp lệ");
			}
			if(l.size()==0&&l1.size()==0) {
				throw new Exception("Vui lòng chọn ít nhất một món ăn hoặc thức uống cho combo");
			}
			
			comBoService.save(c, file, loai, l, l1,gia);
			response r= new response(HttpStatus.OK,"ok",null);
			return new ResponseEntity(r,HttpStatus.OK);
		}

		@GetMapping("/getdsmabyid")
		public ResponseEntity<response> getDSMonAnByIdCombo(@RequestParam(name = "id", required = false) Integer id) throws Exception{
			if(id==null) {
				throw new Exception("Vui Lòng chọn combo phù hợp");
			}
			response r= new response(HttpStatus.OK,"ok",comBoService.getDSMonAnByIdCombo(id));
			return new ResponseEntity(r,HttpStatus.OK);
		}
		
		@GetMapping("/getdstubyid")
		public ResponseEntity<response> getDSThucUongByIdCombo(@RequestParam(name = "id", required = false) Integer id) throws Exception{
			if(id==null) {
				throw new Exception("Vui Lòng chọn combo phù hợp");
			}
			response r= new response(HttpStatus.OK,"ok",comBoService.getDSThucUongByIdCombo(id));
			return new ResponseEntity(r,HttpStatus.OK);
		}
		
		
		@PostMapping("/updatemonan")
		public ResponseEntity<response> updateMonAn(@RequestBody Map<String, String> data) throws Exception {
			String delete = data.get("delete");
			String update = data.get("update");
			ObjectMapper op= new ObjectMapper();
			List<Map<String, Object>> dsu;
			List<Map<String, Object>> dsd;
			try {
				 dsu= op.readValue(update,List.class);
				dsd= op.readValue(delete,List.class);
				System.out.println(dsu.size());
				System.out.println(dsd.size());
			} catch (Throwable e) {
				throw new Exception("dữ liệu cập nhật không hợp lệ");
			} 
			comBoMonAnService.capNhatMonAn(dsu, dsd);
			response r= new response(HttpStatus.OK, "ok",null);
			return new ResponseEntity(r,HttpStatus.OK);
		}
		
		@PostMapping("/updatethucuong")
		public ResponseEntity<response> updateThucuong(@RequestBody Map<String, String> data) throws Exception {
			String delete = data.get("delete");
			String update = data.get("update");
			ObjectMapper op= new ObjectMapper();
			List<Map<String, Object>> dsu;
			List<Map<String, Object>> dsd;
			try {
				 dsu= op.readValue(update,List.class);
				dsd= op.readValue(delete,List.class);
				System.out.println(dsu.size());
				System.out.println(dsd.size());
			} catch (Throwable e) {
				throw new Exception("dữ liệu cập nhật không hợp lệ");
			} 
			comBoThucUongService.capNhatMonAn(dsu, dsd);
			response r= new response(HttpStatus.OK, "ok",null);
			return new ResponseEntity(r,HttpStatus.OK);
		}
		
		@PostMapping("/capnhatcombo")
		public ResponseEntity<response> update(
			@Valid @ModelAttribute ComBo c,
			@RequestParam("idloai") Integer idloai,
			@RequestParam("anh") MultipartFile file,
			@RequestParam ("giaa") String giaa
				) throws Exception {
			
				comBoService.capnhat(c, idloai, file,giaa);
			
			return new ResponseEntity(new response(HttpStatus.OK, "ok",null),HttpStatus.OK);
		}
		
		@GetMapping("/getkhuyenmai")
		public ResponseEntity<response> getkm(@RequestParam("id") int id){
			List<Map<Object, Object>> kmtt=khuyenMaiComBoRepository.getkmma(id);
			List<Map<Object, Object>> kmgv=khuyenMaiGioVangComboReposito.getkmma(id);
			float i= khuyenMaiComBoRepository.tongkm(id);
			Map<Object, Object> map=new HashMap<>();;
			map.put("kmgv",kmgv);
			map.put("kmtt",kmtt);
			map.put("tong",i);
			return new ResponseEntity<response>(new response(HttpStatus.OK, "ok", map),HttpStatus.OK);
		}
		
		@GetMapping("ngungkmtt")
		public ResponseEntity<response> ngungkmtt(@RequestParam("idma") int idma,@RequestParam("idkm") int idkm){
			KhuyenMaiComBoId id= new KhuyenMaiComBoId();
			id.setKM_ID(idkm);
			id.setCB_ID(idma);
			KhuyenMaiComBo k=khuyenMaiComBoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Món anư không còn áp dụng khuyến mãi"));
			khuyenMaiComBoRepository.delete(k);
			return new ResponseEntity<response>(new response(HttpStatus.OK, "Đã ngưng sử dụng khuyến mãi",null),HttpStatus.OK);
			
		}
		@GetMapping("ngungkmgv")
		public ResponseEntity<response> ngungkmgv(@RequestParam("idma") int idma,@RequestParam("idkm") int idkm){
			KhuyenMaiGioVangComBoId id= new KhuyenMaiGioVangComBoId();
			id.setKM_ID(idkm);
			id.setCB_ID(idma);
			KhuyenMaiGioVangComBo k=	khuyenMaiGioVangComboReposito.findById(id).orElseThrow(()-> new EntityNotFoundException("Món anư không còn áp dụng khuyến mãi"));
			khuyenMaiGioVangComboReposito.delete(k);
			return new ResponseEntity<response>(new response(HttpStatus.OK, "Đã ngưng sử dụng khuyến mãi",null),HttpStatus.OK);
			
		}
		
}
