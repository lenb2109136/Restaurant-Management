package com.example.nienluannganh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.KhuyenMaiThucUong;
import com.example.nienluannganh.model.embededid.KhuyenMaiThucUongId;
import com.example.nienluannganh.repository.KhuyenMaiThongThuongRepository;
import com.example.nienluannganh.repository.KhuyenMaiThucUongRepository;

@Service
public class KhuyenMaiThucUongService {
	@Autowired
	private KhuyenMaiThucUongRepository khuyenMaiThucUongRepository;
	
	public KhuyenMaiThucUong saveKhuyenMaiThucUong(KhuyenMaiThucUong ku) {
		return khuyenMaiThucUongRepository.save(ku);
	}
	
	public Optional<KhuyenMaiThucUong> getKhuyenMaiThucUongById(KhuyenMaiThucUongId id) {
		return khuyenMaiThucUongRepository.findById(id);
	}
}
