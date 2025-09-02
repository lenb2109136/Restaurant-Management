package com.example.nienluannganh.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.DTO.caSoLuong;
import com.example.nienluannganh.model.Ca;
import com.example.nienluannganh.model.ThoiGianLamCa;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.repository.CaRepository;
import com.example.nienluannganh.repository.NhanVienRepository;
@RestController
@RequestMapping("/tglc")
public class ThoiGianLamCaController {
	@Autowired
	private com.example.nienluannganh.repository.ThoiGianLamCaRepository thoiGianLamCaRepository;
	
	@Autowired
	private CaRepository caRepository;
	
	@Autowired
	private NhanVienRepository nhanVienRepository;
	
	@GetMapping("/getcangay")
	public ResponseEntity<response> getcangay(@RequestParam("nl") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate ngayLam) {
		List<Ca> c = caRepository.findAll();
		List<Map<Object, Object>> map = new ArrayList<Map<Object, Object>>();
		for (int i = 0; i < c.size(); i++) {
			Map<Object, Object> mpa = new HashMap<Object, Object>();
			mpa.put("ca", c.get(i));
			mpa.put("thontin", thoiGianLamCaRepository.getcangay(ngayLam, c.get(i).getId()));
			map.add(mpa);
		}
		return new ResponseEntity<response>(new response(HttpStatus.OK,"OK",map),HttpStatus.OK);
	}
	
	@GetMapping("/getnhanvien")
	public ResponseEntity<response> getnhanvien(@RequestParam("idca") int idca, @RequestParam("idcv") int idcv,@RequestParam("nl") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate nl ){
		Map<Object, Object> map= new HashMap<Object, Object>();
		map.put("in",thoiGianLamCaRepository.getnhanvien(idca, nl, idcv));
		map.put("notin",thoiGianLamCaRepository.getnhanviennotin(idca, nl, idcv));
		return new ResponseEntity<response>(new response(HttpStatus.OK,"OK",map),HttpStatus.OK);
	}
	
	@GetMapping("/catcalam")
	public ResponseEntity<response> catCalam(@RequestParam("idnv") int idnv,@RequestParam("idca") int idcv, @RequestParam("nl") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate nl ){
		ThoiGianLamCa t= thoiGianLamCaRepository.getthoigianlamca(idnv, nl, idcv);
		thoiGianLamCaRepository.delete(t);
		return new ResponseEntity<response>(new response(HttpStatus.OK,"Ok",null),HttpStatus.OK);
	}
	@GetMapping("/phanthemcalam")
	public ResponseEntity<response> phanthemCalama(@RequestParam("idnv") int idnv,@RequestParam("idca") int idcv, @RequestParam("nl") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate nl ){
		ThoiGianLamCa t= new ThoiGianLamCa();
		t.setCa(caRepository.getbyId(idcv));
		t.setNgaylam(nl);
		t.setNhanVien(nhanVienRepository.getById(idnv));
		thoiGianLamCaRepository.save(t);
		return new ResponseEntity<response>(new response(HttpStatus.OK,"Ok",null),HttpStatus.OK);
	}

}
