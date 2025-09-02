package com.example.nienluannganh.controller;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nienluannganh.DTO.ChamCongDTO;
import com.example.nienluannganh.repository.ChamCongRepository;
import com.example.nienluannganh.service.ExcelExporter;
import com.example.nienluannganh.service.InfoChamCong;

@Controller
public class excelCtrontoller {
	
	@Autowired
	private ChamCongRepository chamCongRepo;
	@GetMapping("/export-chamcong")
	public ResponseEntity<byte[]> exportChamCong(@RequestParam(name = "nhanVienId", required = false) Integer nhanVienId,
            @RequestParam(name = "ngayBatDau", required = false) LocalDate batDau,
            @RequestParam(name = "ngayKetThuc", required = false) LocalDate ketThuc,
            @RequestParam(name = "bpid",required = false) Integer bpid,
            @RequestParam(name = "cvid",required = false) Integer cvid) throws IOException {
		List<ChamCongDTO> thoiGianLamCas = chamCongRepo.findCaLamValid(batDau, ketThuc);
        thoiGianLamCas.forEach(v -> {
            if(v.getThoiGianLamCa().getBatDau()!=null&&v.getThoiGianLamCa().getKetThuc()!=null) {
        	   v.setSoGioDiTre(v.getGiaCaBoPhan().getCa().tinhPhutDiTre(v.getThoiGianLamCa().getBatDau()));
              v.setSoGioTangCa(v.getGiaCaBoPhan().getCa().tinhPhutChenhLechVe(v.getThoiGianLamCa().getKetThuc()));
           }
       });
        List<InfoChamCong> info = new ArrayList<>();
       Map<Integer, List<ChamCongDTO>> chamCongTheoNhanVien = thoiGianLamCas.stream()
               .collect(Collectors.groupingBy(ChamCongDTO::getNhanVienId));
        chamCongTheoNhanVien.forEach((k, v) -> {
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
               
               if(cvid!=null) {
               	if(i.getNhanVien().getBoPhan().getId()!=cvid) {
               		b=false;
               	}
               }
               if(bpid!=null) {
               	if(i.getNhanVien().getChucVu().getId()!=bpid) {
               		b=false;
               	} 
               }
               if(b) {
               	info.add(i);
               }
               
           }
       }); 

	    byte[] excelBytes = ExcelExporter.exportInfoChamCongToExcel(info);

	    org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDisposition(ContentDisposition.attachment().filename("chamcong.xlsx").build());

	    return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
	}

}
