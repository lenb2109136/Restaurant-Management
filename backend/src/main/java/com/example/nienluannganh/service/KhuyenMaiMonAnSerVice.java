package com.example.nienluannganh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.KhuyenMaiMonAn;
import com.example.nienluannganh.model.KhuyenMaiThucUong;
import com.example.nienluannganh.model.embededid.KhuyenMaiMonAnId;
import com.example.nienluannganh.model.embededid.KhuyenMaiThucUongId;
import com.example.nienluannganh.repository.KhuyenMaiMonAnRepository;
import com.example.nienluannganh.repository.KhuyenMaiThucUongRepository;

@Service
public class KhuyenMaiMonAnSerVice {
	@Autowired
	private KhuyenMaiMonAnRepository khuyenMaiMonAnRepository;
	
	public KhuyenMaiMonAn saveKhuyenMaiMonAn  (KhuyenMaiMonAn ku) {
		return khuyenMaiMonAnRepository.save(ku);
	}
	
	public Optional<KhuyenMaiMonAn> getKhuyenMaiMonAnById(KhuyenMaiMonAnId id) {
		return khuyenMaiMonAnRepository.findById(id);
	}
}
