package com.example.nienluannganh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.Ban;
import com.example.nienluannganh.repository.BanRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class BanService {
	@Autowired
	private BanRepository banRepository;
	
	public Ban saveBan(Ban b) {
		return banRepository.save(b);
	}
	
	public Optional<Ban> getBanById(int id) {
		return banRepository.findById(id);
	}
	@Transactional
	public void update(List<Ban> dsban) {
		dsban.forEach(data->{
			Ban b= banRepository.findById(data.getStt()).orElseThrow(()-> new EntityNotFoundException("Không thấy bàn"));
			b.setToaDoX(data.getToaDoX());
			b.setToaDoY(data.getToaDoY());
			b.setSucChua(data.getSucChua());
			b.setTrong(data.getTrong());
			banRepository.save(b);
		});
	}
	@Transactional
	public void xoa(Ban v) {
		Ban b= banRepository.getById(v.getStt());
		banRepository.delete(b);
	}
	public List<Ban> getcon(int id){
		return banRepository.getCon(id);
	}
	@Transactional
	public void capnhatban(Ban data) {
		Ban b= banRepository.getById(data.getStt());
		b.setToaDoX(data.getToaDoX());
		b.setToaDoY(data.getToaDoY());
		b.setSucChua(data.getSucChua());
		b.setTrong(data.getTrong());
		banRepository.save(b);
	}
	public Ban goiY(int soluong) {
		return banRepository.goiY(soluong);
	}
	public List<Ban> getBanGoiY(int soluong) {
		return banRepository.getBanGoiY();
	}
	
	public List<Ban> layyy(int id) {
		return banRepository.layy(id);
	}
}
