package com.example.nienluannganh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.DonViTinh;
import com.example.nienluannganh.repository.DonViTinhRepository;

@Service
public class DonViTinhService {
	@Autowired
	private DonViTinhRepository donViTinhRepository;
	
	public DonViTinh savedDonViTinh(DonViTinh d) {
		return donViTinhRepository.save(d);
	}
	
	public Optional<DonViTinh> getDonViTinhById(int id) {
		return donViTinhRepository.findById(id);
	}
	
	public List<DonViTinh> getAllDonviTinh(){
		return donViTinhRepository.findAll();
	}
}
