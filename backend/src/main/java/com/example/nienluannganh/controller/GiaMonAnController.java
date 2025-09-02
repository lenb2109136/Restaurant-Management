package com.example.nienluannganh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.repository.GiaMonAnRepository;

@RestController
@RequestMapping("/giamonan")
public class GiaMonAnController {
	@Autowired
	private GiaMonAnRepository giaMonAnRepository;
	@GetMapping("/getgia")
	public ResponseEntity<response> getgiamonan(@RequestParam("getbymonan") int id){
		return new ResponseEntity<response>(new response(HttpStatus.OK,"OK", giaMonAnRepository.getgia(id)),HttpStatus.OK);
	}
}
