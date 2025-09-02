package com.example.nienluannganh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.CoSoLuongThucUong;
import com.example.nienluannganh.model.embededid.CoSoLuongThucUongId;
import com.example.nienluannganh.repository.CoSoLuongThucUongRepository;

@Service
public class CoSoLuongThucUongService {
	@Autowired
	private CoSoLuongThucUongRepository  coSoLuongThucUongRepository;
	
	public CoSoLuongThucUong saveCoSoLuongThucUong(CoSoLuongThucUong c) {
		return coSoLuongThucUongRepository.save(c);
	}
	
	public Optional<CoSoLuongThucUong> getCoSoLuongThucUongById(CoSoLuongThucUongId id) {
		return coSoLuongThucUongRepository.findById(id);
	}
}
