package com.example.nienluannganh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.repository.DinhMucSoLuongRepository;

@Service
public class DinhMucSoLuongService {
	@Autowired
	private DinhMucSoLuongRepository dinhMucSoLuongRepository;
}
