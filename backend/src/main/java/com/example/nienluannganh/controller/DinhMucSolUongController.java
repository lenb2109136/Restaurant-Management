package com.example.nienluannganh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.repository.DinhMucSoLuongRepository;

@RestController
@RequestMapping("dinhmuc")
public class DinhMucSolUongController {
	@Autowired
	private DinhMucSoLuongRepository dinhMucSoLuongRepository;
	@GetMapping("/getds")
	public ResponseEntity<response> getDS(){
		return new ResponseEntity<response>(new response(HttpStatus.OK,"ok", dinhMucSoLuongRepository.findAll()),HttpStatus.OK);
	}
	
}
