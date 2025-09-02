package com.example.nienluannganh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.GiaCaBoPhan;
import com.example.nienluannganh.repository.GiaCaBoPhanRepository;

@Service
public class GiaCaBoPhanService {
	@Autowired
	private GiaCaBoPhanRepository giaCaBoPhanRepository;
	public GiaCaBoPhan saveGiaCaBoPhan(GiaCaBoPhan g) {
		return giaCaBoPhanRepository.save(g);
	}
}
