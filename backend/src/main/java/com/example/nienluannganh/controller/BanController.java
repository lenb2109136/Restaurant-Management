package com.example.nienluannganh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.model.Ban;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.service.BanService;
import com.example.nienluannganh.service.SanhService;

@RestController
@RequestMapping("/ban")
public class BanController {
	@Autowired
	BanService banService;
	@Autowired
	SanhService sanhService;
	@PostMapping("/update")
	public ResponseEntity<response> update(@RequestBody List<Ban> dsban) {
		banService.update(dsban);
		return new ResponseEntity<response>(new response(HttpStatus.OK,"OK",null),HttpStatus.OK);
	}
	
	@PostMapping("/xoa")
	public ResponseEntity<response> xoa(@RequestBody List<Ban> dsban) {
		banService.xoa(dsban.get(0));
		return new ResponseEntity<response>(new response(HttpStatus.OK,"OK",null),HttpStatus.OK);
	}
	
	@PostMapping("/updateone")
	public ResponseEntity<response> updateone(@RequestBody List<Ban> dsban) {
		banService.capnhatban(dsban.get(0));
		return new ResponseEntity<response>(new response(HttpStatus.OK,"OK",null),HttpStatus.OK);
	}
	
	@PostMapping("/layy")
	public ResponseEntity<response> updateone(@RequestParam("id") int id) {
		return new ResponseEntity<response>(new response(HttpStatus.OK,"OK",banService.layyy(id)),HttpStatus.OK);
	}
	
	@GetMapping("goiyban")
	public ResponseEntity<response> goiYBan(@RequestParam("succhua") int sc) {
		Ban b=banService.goiY(sc);
		List<Ban> ghep= new ArrayList<Ban>();
		int scc=0;
		if(b!=null) {
			return new ResponseEntity<response>(new response(HttpStatus.OK, "Không", banService.goiY(sc)),HttpStatus.OK);
		}
		else {
			List<Ban> b2= banService.getBanGoiY(sc);
			for(int i=0;i<b2.size();i++) {
				sc+=b2.get(i).getSucChua();
				ghep.add(b2.get(i));
				for(int u=0;u<b2.size();u++) {
					
					if(u!=i) {
						System.out.println("số lượng ghế trống : "+b2.get(u).getSucChua());
						scc+=b2.get(u).getSucChua();
						System.out.println("số lượng ghế: "+scc);
						ghep.add(b2.get(u));
						
						if(scc>=sc) {
							break;
						}
					}
					
				}
				if(scc>=sc) {
					break;
				}
				else {
					scc=0; }ghep.clear();
			}
			if(scc==0) {
				return new ResponseEntity<response>(new response(HttpStatus.NO_CONTENT, "Không tìm thấy lựa chọn phù hợp", banService.goiY(sc)),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<response>(new response(HttpStatus.NO_CONTENT, "Không tìm thấy lựa chọn phù hợp", ghep),HttpStatus.OK);
			}
		}
		
		}
	
	@RequestMapping("/getcon")
	public ResponseEntity<response> getcon(@RequestParam("id") int id) {
		return new ResponseEntity<response>(new response(HttpStatus.OK,"OK",banService.getcon(id)),HttpStatus.OK);
	}
}
