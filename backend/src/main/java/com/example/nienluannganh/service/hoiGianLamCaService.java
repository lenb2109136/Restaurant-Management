package com.example.nienluannganh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.ThoiGianLamCa;
import com.example.nienluannganh.repository.ThoiGianLamCaRepository;

@Service
public class hoiGianLamCaService {
	@Autowired
	private ThoiGianLamCaRepository thoiGianLamCaRepository;
	
	public ThoiGianLamCa savethoiGianLamCa(ThoiGianLamCa t) {
		return thoiGianLamCaRepository.save(t);
	}
	
	public Optional<ThoiGianLamCa> getThoiGianLamCaById(int id){
		return thoiGianLamCaRepository.findById(id);
	}
}
