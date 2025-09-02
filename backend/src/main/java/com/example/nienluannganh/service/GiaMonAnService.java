package com.example.nienluannganh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.GiaMonAn;
import com.example.nienluannganh.repository.GiaMonAnRepository;

@Service
public class GiaMonAnService {
	@Autowired
	private GiaMonAnRepository giaMonAnRepository;
	
	public GiaMonAn saveGiaMonAn(GiaMonAn g) {
		return giaMonAnRepository.save(g);
	}
	
	public Optional<GiaMonAn> getGiaMonAnById(int id) {
		return giaMonAnRepository.findById(id);
	}
}
