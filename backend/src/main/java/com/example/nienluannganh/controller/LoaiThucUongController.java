package com.example.nienluannganh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.service.LoaiThucUongService;
@RestController
@RequestMapping("/thucuong")
public class LoaiThucUongController {
	@Autowired
	private LoaiThucUongService ltu;
	 
	@GetMapping("/getloai")
	public ResponseEntity<response> getLoai(){
		response r= new response(HttpStatus.OK,"ok",ltu.getLoaiThucuong());
		return new ResponseEntity(r,HttpStatus.OK);
	}
	
}
