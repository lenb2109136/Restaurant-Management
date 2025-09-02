package com.example.nienluannganh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.ChucVu;
import com.example.nienluannganh.repository.ChucVuRepository;

@Service
public class ChucVuService {
@Autowired
private ChucVuRepository chucVuRepository;

	public List<ChucVu> getall(int id) {
		return chucVuRepository.getbybp(id);
	}
	public List<ChucVu> getds(){
		return chucVuRepository.findAll();
	}
}
