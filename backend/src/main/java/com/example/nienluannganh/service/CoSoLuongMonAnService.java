package com.example.nienluannganh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.CoSoLuongMonAn;
import com.example.nienluannganh.model.embededid.CoSoLuongMonAnId;
import com.example.nienluannganh.repository.CoSoLuongMonAnRepository;

@Service
public class CoSoLuongMonAnService {
	@Autowired
	private CoSoLuongMonAnRepository coSoLuongMonAnRepository;
	
	public CoSoLuongMonAn savecCoSoLuongMonAn (CoSoLuongMonAn c) {
		return coSoLuongMonAnRepository.save(c);
	}
	
	public Optional<CoSoLuongMonAn> getCoSoLuongMonAn(CoSoLuongMonAnId id) {
		return coSoLuongMonAnRepository.findById(id);
	}
	
}
