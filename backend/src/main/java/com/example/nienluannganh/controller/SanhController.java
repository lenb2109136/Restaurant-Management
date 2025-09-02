package com.example.nienluannganh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.model.Ban;
import com.example.nienluannganh.model.Sanh;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.repository.BanRepository;
import com.example.nienluannganh.repository.SanhRepository;
import com.example.nienluannganh.service.SanhService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/sanh")
public class SanhController {
	
	@Autowired
	private SanhService sanhService;
	
	@Autowired
	private SanhRepository sanhRepository;
	
	@Autowired
	private BanRepository banRepository;
	
	@GetMapping("/getbyid")
	public ResponseEntity<response> getsanh(@RequestParam("id") int id){
		return new ResponseEntity<response>(new response(HttpStatus.OK,"ok",sanhService.getSanhById(id)),HttpStatus.OK);
	}
	
	@GetMapping("/getbyidd")
	public ResponseEntity<response> getsanhtrong(@RequestParam("id") int id){
		Sanh sanh= sanhService.getSanhById(id).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy sảnh"));
		 List<Ban> dsban= sanh.getDsBan();
		 dsban.removeIf(d-> d.getTrong()==false);
		return new ResponseEntity<response>(new response(HttpStatus.OK,"ok",dsban),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<response> getsanh(){
		return new ResponseEntity<response>(new  response(HttpStatus.OK,"ok",sanhRepository.findAll()),HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<response> save(@RequestParam("ban") String ban, @RequestParam("sanh") int id) throws Exception{
		ObjectMapper o = new ObjectMapper();
        List<Ban> b = o.readValue(ban, new TypeReference<List<Ban>>() {});
        Sanh s= sanhRepository.findById(id).orElseThrow(()-> new Exception("Không tìm thấy sảnh cần cập nhật"));
        b.get(0).setSanh(s);
        banRepository.save(b.get(0));
        return new ResponseEntity<response>(new response(HttpStatus.OK,"ok",sanhService.getSanhById(id)),HttpStatus.OK);
	}
	
	
}
