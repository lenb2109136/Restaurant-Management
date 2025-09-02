package com.example.nienluannganh.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.jsontoken.JWT;
import com.example.nienluannganh.model.NhanVien;
import com.example.nienluannganh.model.ThoiGianChucVu;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.repository.NhanVienRepository;

@RestController
@RequestMapping("/login")
public class login {

	
	@Autowired
	NhanVienRepository nhanVienRepository;
	
	@Autowired
	JWT jwt;
	
	@PostMapping("/login")
	public ResponseEntity<response> login(@RequestParam(name = "soDienThoai",defaultValue = "")String soDienThoai,@RequestParam(name = "matKhau",defaultValue = "")String matKhau){
		ThoiGianChucVu nhanVien=nhanVienRepository.getNhanVienById(soDienThoai).orElseGet(null);
		if(nhanVien!=null){
			if(matKhau.equals(nhanVien.getNhanVien().getMatKhau())) {
				String token=jwt.createToken(nhanVien.getId()+"",nhanVien.getBoPhan().getId()+"");
				Map<Object,Object> op= new HashMap<Object, Object>();
				op.put("token", token);
				op.put("ten",nhanVien.getNhanVien().getTen());
				op.put("anh", nhanVien.getNhanVien().getAnhGioiThieu());
				op.put("role",nhanVien.getBoPhan().getId());
				op.put("id", nhanVien.getId());
				return new ResponseEntity<response>(new response(HttpStatus.OK, "Thông tin hợp lệ", op),HttpStatus.OK);
			}
		}
		return new ResponseEntity<response>(new response(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "Thông tin không hợp lệ", null),HttpStatus.OK);
	}
}
