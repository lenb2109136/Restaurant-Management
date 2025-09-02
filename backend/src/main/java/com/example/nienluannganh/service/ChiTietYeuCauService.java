package com.example.nienluannganh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.ChiTietYeuCau;
import com.example.nienluannganh.model.MonAn;
import com.example.nienluannganh.repository.ChiTietYeuCauRepository;

@Service
public class ChiTietYeuCauService {
	@Autowired
	private ChiTietYeuCauRepository chiTietYeuCauRepository;
	
	public ChiTietYeuCau saveChiTietYeuCau(ChiTietYeuCau ma) {
		return chiTietYeuCauRepository.save(ma);
	}
	
	public Optional<ChiTietYeuCau> getChiTietYeuCauById(int id) {
		return chiTietYeuCauRepository.findById(id);
	}
}
