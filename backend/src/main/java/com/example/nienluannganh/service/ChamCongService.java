package com.example.nienluannganh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.ChamCong;
import com.example.nienluannganh.repository.ChamCongRepository;

@Service
public class ChamCongService {
	@Autowired
	private ChamCongRepository chamCongRepository;
	
	public ChamCong saveChamCong(ChamCong c) {
		return chamCongRepository.save(c);
	}
	
	public Optional<ChamCong> getchamCongById(int id) {
		return chamCongRepository.findById(id);
	}
}
