package com.example.nienluannganh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nienluannganh.model.MonAn;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.repository.MonAnRepository;

@Controller

public class testcontoller {
	@Autowired
	private MonAnRepository monAnRepository;
@GetMapping("/phantrang")
public ResponseEntity<Page<MonAn>> getphantrang(
		@RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size){
	Pageable pa= PageRequest.of(page, size);
	Page<MonAn> result= monAnRepository.findAll(pa);
	return new ResponseEntity<Page<MonAn>>(result, HttpStatus.OK);
}

}
