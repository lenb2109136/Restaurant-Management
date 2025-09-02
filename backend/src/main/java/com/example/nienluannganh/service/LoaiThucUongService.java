package com.example.nienluannganh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.LoaiThucUong;
import com.example.nienluannganh.repository.LoaiThucUongRepository;

@Service
public class LoaiThucUongService {
	@Autowired
	private LoaiThucUongRepository loaiThucUongRepository;
	
	public LoaiThucUong saveLoaiThucUong(LoaiThucUong l) {
		return loaiThucUongRepository.save(l);
	}
	
	public Optional<LoaiThucUong> getLoaiThucUongById(int id) {
		return loaiThucUongRepository.findById(id);
	}
	
	public List<LoaiThucUong> getLoaiThucuong(){
		return loaiThucUongRepository.findAll();
	}
	
	
}
