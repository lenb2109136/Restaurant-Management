package com.example.nienluannganh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.Sanh;
import com.example.nienluannganh.repository.SanhRepository;

@Service
public class SanhService {
	@Autowired
	private SanhRepository sanhRepository;
	
	public Sanh saveSanh(Sanh s) {
		return sanhRepository.save(s);
	}
	
	public Optional<Sanh> getSanhById(int id) {
		return sanhRepository.findById(id);
	}
	

}
