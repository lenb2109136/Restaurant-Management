package com.example.nienluannganh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.ComBo;
import com.example.nienluannganh.model.LoaiComBo;
import com.example.nienluannganh.repository.LoaiComBoRepository;

@Service
public class LoaiComBoService {
	@Autowired
	private LoaiComBoRepository loaiComBoRepository;
	
	public LoaiComBo saveLoaiComBo(LoaiComBo l) {
		return loaiComBoRepository.save(l);
	}
	
	public Optional<LoaiComBo> getLoaiComBoById(int id) {
		return loaiComBoRepository.findById(id);
	}
	
	public List<LoaiComBo> getDSComBo(){
		return loaiComBoRepository.findAll();
	}
}
