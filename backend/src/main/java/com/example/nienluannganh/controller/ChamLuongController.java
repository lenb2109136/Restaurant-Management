package com.example.nienluannganh.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.DTO.ChamCongDTO;
import com.example.nienluannganh.model.NhanVien;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.repository.ChamCongRepository;
import com.example.nienluannganh.service.InfoChamCong;

import jakarta.transaction.Transactional;
import com.*;
@RestController
public class ChamLuongController {

    @Autowired
    ChamCongRepository chamCongRepo;

    @GetMapping("chamluong/getall")
    public ResponseEntity<Object> getAllChamCong(
            @RequestParam(name = "nhanVienId", required = false) Integer nhanVienId,
            @RequestParam(name = "ngayBatDau", required = false) LocalDate batDau,
            @RequestParam(name = "ngayKetThuc", required = false) LocalDate ketThuc,
            @RequestParam(name = "bpid",required = false) Integer bpid,
            @RequestParam(name = "cvid",required = false) Integer cvid
    		) {
         List<ChamCongDTO> thoiGianLamCas = chamCongRepo.findCaLamValid(batDau, ketThuc);
         
         thoiGianLamCas.forEach(v -> {
        	 System.out.println("Chưc vu: "+v.getBoPhan().getId()+" - "+v.getGiaCaBoPhan().getBoPhan().getId());
             if(v.getThoiGianLamCa().getBatDau()!=null&&v.getThoiGianLamCa().getKetThuc()!=null) {
         	   v.setSoGioDiTre(v.getGiaCaBoPhan().getCa().tinhPhutDiTre(v.getThoiGianLamCa().getBatDau()));
               v.setSoGioTangCa(v.getGiaCaBoPhan().getCa().tinhPhutChenhLechVe(v.getThoiGianLamCa().getKetThuc()));
            }
        });
         List<InfoChamCong> info = new ArrayList<>();
        Map<Integer, List<ChamCongDTO>> chamCongTheoNhanVien = thoiGianLamCas.stream()
                .collect(Collectors.groupingBy(ChamCongDTO::getNhanVienId));
         chamCongTheoNhanVien.forEach((k, v) -> {
        	 if(k==21) {
        		 System.out.println("==============");
        		 System.out.println(v.size());
        		 System.out.println("==============");
        	 }
            if (nhanVienId == null || nhanVienId == k) {
                InfoChamCong i = new InfoChamCong();
                i.setChamCong(v);
                i.setNhanVien(v.get(0).getNhanVien());
                Integer gioDiTre = 0;
                Integer gioTangCa = 0;
                Double tongTien = 0.0;
                for (ChamCongDTO vv : v) { 
                    gioDiTre += vv.getSoGioDiTre();
                    gioTangCa += vv.getSoGioTangCa();
                    System.out.println("so gio tang ca lac: "+vv.getSoGioTangCa());
                    if(vv.getThoiGianLamCa().getBatDau()!=null&&vv.getThoiGianLamCa().getKetThuc()!=null) {
                    	tongTien += vv.getGiaCaBoPhan().getGia();
                    }
                }
                 i.setTongSoTien(tongTien);
                i.setSoGioTangCa(gioTangCa);
                i.setSoGioDiTre(gioDiTre);
                Boolean b= true;
                
                if(cvid!=null&&cvid!=0) {
                	if(i.getNhanVien().getBoPhan().getId()!=cvid) {
                		b=false;
                	}
                }
                if(bpid!=null&&bpid!=0) {
                	if(i.getNhanVien().getChucVu().getId()!=bpid) {
                		b=false;
                	} 
                }
                if(b) {
                	info.add(i);
                }
                
            }
        }); 
         
        
         
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("chamcong/submitall")
    public ResponseEntity<Object> chamCong(@RequestBody List<Integer> phanCongIds) {
        if (phanCongIds.size() == chamCongRepo.countAllChuaChamCong(phanCongIds)) {
            Integer countChamCong = chamCongRepo.chamCongAll(phanCongIds);
            if (countChamCong == phanCongIds.size()) {
                return new ResponseEntity<>(new response(HttpStatus.OK, "success", null), HttpStatus.OK);
            }
            return new ResponseEntity<>(new response(HttpStatus.BAD_REQUEST, "Yêu cầu cập nhật không tành công", null),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                new response(HttpStatus.BAD_REQUEST, "Danh sách yêu cầu trả lương không hợp lệ", null),
                HttpStatus.BAD_REQUEST);
    }

}

