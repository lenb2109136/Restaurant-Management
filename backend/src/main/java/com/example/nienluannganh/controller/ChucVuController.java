package com.example.nienluannganh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.service.ChucVuService;

@RestController
@RequestMapping("/chucvu")
public class ChucVuController {
	@Autowired
	private ChucVuService chucVuService;
	
	@GetMapping("/getall")
	public ResponseEntity<response> getall(@RequestParam("id") int id){
		return new ResponseEntity(new response(HttpStatus.OK,"ok", chucVuService.getall(id)),HttpStatus.OK);
	}
	@GetMapping("/getds")
	public ResponseEntity<response> getall(){
		return new ResponseEntity(new response(HttpStatus.OK,"ok", chucVuService.getds()),HttpStatus.OK);
	}
	
}
