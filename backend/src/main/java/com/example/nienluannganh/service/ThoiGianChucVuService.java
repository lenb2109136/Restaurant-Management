package com.example.nienluannganh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.ThoiGianChucVu;
import com.example.nienluannganh.repository.ThoiGianChucVuRepository;

@Service
public class ThoiGianChucVuService {
	@Autowired
	private ThoiGianChucVuRepository thoiGianChucVuRepository;
	
	public ThoiGianChucVu savethoigianChucVu(ThoiGianChucVu t) {
		return thoiGianChucVuRepository.save(t);
	}
	
	public Optional<ThoiGianChucVu> getthoiGianChucVuById( int id) {
		return thoiGianChucVuRepository.findById(id);
	}
}
