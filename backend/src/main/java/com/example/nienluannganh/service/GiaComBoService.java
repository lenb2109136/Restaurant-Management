package com.example.nienluannganh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.GiaComBo;
import com.example.nienluannganh.repository.GiaComBoRepository;

@Service
public class GiaComBoService {
	@Autowired
	private GiaComBoRepository giaComBoRepository;
	
	public GiaComBo saveGiaComBo(GiaComBo g) {
		return giaComBoRepository.save(g);
	}
	
	public Optional<GiaComBo> getGiaComBoById(int id) {
		return giaComBoRepository.findById(id);
	}
}
