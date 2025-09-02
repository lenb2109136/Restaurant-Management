package com.example.nienluannganh.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.DTO.HoaDonDTO;
import com.example.nienluannganh.RAMWEBSOCKET.RamWebsocket;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.service.ExportPDF;
import com.example.nienluannganh.service.YeuCauService;

@RestController
@RequestMapping("/yeucau")
public class YeuCauController {
	
	@Autowired
	private YeuCauService yeuCauService;
	@PostMapping("/save")
	public ResponseEntity<response> save(@RequestBody RamWebsocket r){
		yeuCauService.save(r);
		return new ResponseEntity<response>(new response(HttpStatus.OK, null, r),HttpStatus.OK);
	}
	
	@GetMapping("/huy")
	public ResponseEntity<response> huy(@RequestParam("id") int id){
		yeuCauService.huyDon(id);
		return new ResponseEntity<response>(new response(HttpStatus.OK, null, null),HttpStatus.OK);
	}
	
	@PostMapping("/capnhat")
	public ResponseEntity<response> capNhat(@RequestBody RamWebsocket r){
		yeuCauService.capNhat(r);
		return new ResponseEntity<response>(new response(HttpStatus.OK, null, r),HttpStatus.OK);
	}
	@PostMapping("/update")
	public ResponseEntity<response> update(@RequestBody RamWebsocket r){
		yeuCauService.update(r);
		return new ResponseEntity<response>(new response(HttpStatus.OK, null, r),HttpStatus.OK);
	}
	
	@GetMapping("/hoadon")
	public ResponseEntity<response> getHoaDon(@RequestParam("id") int id ,@RequestParam("idnhanvien") int idnhanvien){
		return new ResponseEntity<response>(new response(HttpStatus.OK, null, yeuCauService.getHoaDon(id, idnhanvien)),HttpStatus.OK);
	}
	
	
	@PostMapping("/xuatphieu")
	public ResponseEntity<Object> getphieuluongpdf(@RequestBody Map<Object, Object> map) throws Exception{
		ExportPDF e= new ExportPDF();
		byte[] dulieu=e.createPdf(map);
		HttpHeaders header= new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_PDF);
		header.setContentDisposition(ContentDisposition.attachment()
                .filename("sample.pdf")
                .build());
		return new ResponseEntity<Object>(dulieu,header,HttpStatus.OK);

	}

	
}
