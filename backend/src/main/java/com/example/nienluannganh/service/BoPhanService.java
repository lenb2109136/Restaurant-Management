package com.example.nienluannganh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.BoPhan;
import com.example.nienluannganh.repository.BoPhanRepository;

@Service
public class BoPhanService {
	@Autowired
	private BoPhanRepository boPhanRepository;
	
	public BoPhan saveBoPhan(BoPhan b) {
		return boPhanRepository.save(b);
	}
	
	public Optional<BoPhan> getBoPhanById(int id) {
		return boPhanRepository.findById(id);
	}
	
	public List<BoPhan> getDsBoPhan(){
		return boPhanRepository.findAll();
	}
}
