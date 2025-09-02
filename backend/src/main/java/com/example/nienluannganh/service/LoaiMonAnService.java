package com.example.nienluannganh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.LoaiMonAn;
import com.example.nienluannganh.repository.LoaiMonAnRepository;

@Service
public class LoaiMonAnService {
	@Autowired
	private LoaiMonAnRepository loaiMonAnRepository;
	
	public LoaiMonAn saveLoaiMonAn(LoaiMonAn l) {
		return loaiMonAnRepository.save(l);
	}
	
	public Optional<LoaiMonAn> getLoaiMonAnById(int id) {
		return loaiMonAnRepository.findById(id);
	}
	
	public List<LoaiMonAn> getDSMonAn(){
		return loaiMonAnRepository.findAll();
	}
}
