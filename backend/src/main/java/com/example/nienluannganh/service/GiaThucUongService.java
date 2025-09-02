package com.example.nienluannganh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.GiaThucUong;
import com.example.nienluannganh.repository.GiaThucUongRepository;

@Service
public class GiaThucUongService {
	@Autowired
	private GiaThucUongRepository giaThucUongRepository;
	
	public GiaThucUong saveGiaThucUong(GiaThucUong g) {
		return giaThucUongRepository.save(g);
	}
	
	public Optional<GiaThucUong> getGiaThucUongById(int id) {
		return giaThucUongRepository.findById(id);
	}
}
