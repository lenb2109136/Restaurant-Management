package com.example.nienluannganh.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.model.Ca;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.service.CaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/ca")
public class CaController {
	@Autowired
	private CaService caService;
	@GetMapping("getdsnow")
	public ResponseEntity<response> getdsnow(){
		response r= new response(HttpStatus.OK,"ok", caService.getDsCa());
		return new ResponseEntity(r,HttpStatus.OK);
	}
	@PostMapping("/themca")
	public ResponseEntity<response> themca(
			@RequestParam("giobatdau") Object giobd,
			@RequestParam("gioketthuc") Object giokt,
			@RequestParam("danhsach") String ds
			) throws Exception {
		ObjectMapper op = new ObjectMapper();
		LocalTime b;
		LocalTime a;
		List<Map<String,Object>> ds1;
		try {
			ds1= op.readValue(ds,List.class);
		} catch (JsonMappingException e) {
			throw new Exception("Thông tin ca làm chưa phù hợp");
		} catch (JsonProcessingException e) {
			throw new Exception("Thông tin ca làm chưa phù hợp");
		}
		try {
			a=LocalTime.parse(giobd.toString());
		} catch (Throwable e) {
			throw new Exception("Vui lòng chọn thời gian bắt đầu");
		}
		
		try {
			b=LocalTime.parse(giokt.toString());
		} catch (Throwable e) {
			throw new Exception("Vui lòng chọn thời gian kết thúc");
		}
		
		
	caService.luuds(a, b, ds1);
		response r= new response(HttpStatus.OK,"OK",null);
		return new ResponseEntity(r,HttpStatus.OK);
	}
	
	@PostMapping("/setgia")
	public ResponseEntity<response> setGia(@RequestParam("gia") Float gia,@RequestParam("id")  Integer id) throws Exception{
		if(gia==null|| gia ==0) {
			throw new Exception("Vui lòng cung cấp giá lớn hơn 0");
		}
		caService.setgia( gia, id);
		response r= new response(HttpStatus.OK,"ok", null);
		return new ResponseEntity(r,HttpStatus.OK);
	}
	
	@GetMapping("/thongkeca")
	public ResponseEntity<response> thongkeca(){
		return new ResponseEntity(new response(HttpStatus.OK,"ok", caService.thongke()),HttpStatus.OK);
	}
	
	@GetMapping("/getdshientai")
	public ResponseEntity<response> getdshientai(){
		return new ResponseEntity(new response(HttpStatus.OK,"ok", caService.getdsthanhvien()),HttpStatus.OK);
	}
	
	@GetMapping("/calamhientai")
	public ResponseEntity<response> calamhientai(@RequestParam(name = "id",required = false) Integer id) throws Exception{
		if(id==null) {
			throw  new Exception("Không tìm thấy nhân viên phù hợp");
		}
		return new ResponseEntity(new response(HttpStatus.OK,"ok", caService.cahientai(id)),HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<response> getall(){
		return new ResponseEntity(new response(HttpStatus.OK,"", caService.getall()),HttpStatus.OK);
	}
	
}
