package com.example.nienluannganh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.service.LoaiComBoService;

@RestController
@RequestMapping("/loaicombo")
public class LoaiComBoController {
	
	@Autowired
	private LoaiComBoService lcb;
	@GetMapping("/getdscombo")
	public ResponseEntity<response> getdscombo(){
		response r= new response(HttpStatus.OK,"ok", lcb.getDSComBo());
		return new ResponseEntity(r,HttpStatus.OK);
	}
	
	
}
