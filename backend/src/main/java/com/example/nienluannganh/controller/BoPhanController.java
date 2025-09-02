package com.example.nienluannganh.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.service.BoPhanService;

@RestController
@RequestMapping("/bophan")
public class BoPhanController {
	@Autowired
	private BoPhanService boPhanService;
	
	@GetMapping("/getds")
	public ResponseEntity<response> getDsBoPhan(){
		response r= new response(HttpStatus.OK,"ok", boPhanService.getDsBoPhan());
		return new ResponseEntity(r,HttpStatus.OK);
	}
}
