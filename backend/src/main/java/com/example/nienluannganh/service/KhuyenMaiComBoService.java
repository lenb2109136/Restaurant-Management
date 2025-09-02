package com.example.nienluannganh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.KhuyenMaiComBo;
import com.example.nienluannganh.model.KhuyenMaiThucUong;
import com.example.nienluannganh.model.embededid.KhuyenMaiComBoId;
import com.example.nienluannganh.model.embededid.KhuyenMaiThucUongId;
import com.example.nienluannganh.repository.KhuyenMaiComBoRepository;
import com.example.nienluannganh.repository.KhuyenMaiThucUongRepository;
@Service
public class KhuyenMaiComBoService {
	@Autowired
	private KhuyenMaiComBoRepository khuyenMaiComBoRepository;
	
	public KhuyenMaiComBo khuyenMaiComBo(KhuyenMaiComBo ku) {
		return khuyenMaiComBoRepository.save(ku);
	}
	
	public Optional<KhuyenMaiComBo> getKhuyenMaiComBoById(KhuyenMaiComBoId id) {
		return khuyenMaiComBoRepository.findById(id);
	}
}
