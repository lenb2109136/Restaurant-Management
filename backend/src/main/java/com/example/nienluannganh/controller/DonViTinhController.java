package com.example.nienluannganh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.model.DonViTinh;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.service.DonViTinhService;

@RestController
@RequestMapping("/donvitinh")
public class DonViTinhController {
	@Autowired
	private DonViTinhService d;
		@GetMapping("/getds")
		public ResponseEntity<response> getall(){
			response r= new response(HttpStatus.OK,"ok",d.getAllDonviTinh());
			return new ResponseEntity(r,HttpStatus.OK);
		}
		
}
