package com.example.nienluannganh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.service.LoaiMonAnService;

import jakarta.persistence.EntityManager;

@RestController
@RequestMapping("/getloaimonan")
public class getloaimonan {
	@Autowired
	private LoaiMonAnService loaimon;
	@GetMapping
	public ResponseEntity<response> getDsMonAn(){
		response r= new response(HttpStatus.OK,"ok",loaimon.getDSMonAn());
		return new ResponseEntity<response>(r,HttpStatus.OK);
	}
}
