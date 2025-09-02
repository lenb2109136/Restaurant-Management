package com.example.nienluannganh;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.aspectj.weaver.ast.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import com.example.nienluannganh.*;
import com.example.nienluannganh.context.ConText;
import com.example.nienluannganh.model.Ban;
import com.example.nienluannganh.model.BoPhan;
import com.example.nienluannganh.model.Ca;
import com.example.nienluannganh.model.ChamCong;
import com.example.nienluannganh.model.ChiTietYeuCau;
import com.example.nienluannganh.model.CoSoLuongMonAn;
import com.example.nienluannganh.model.CoSoLuongThucUong;
import com.example.nienluannganh.model.ComBo;
import com.example.nienluannganh.model.ComBoMonAn;
import com.example.nienluannganh.model.ComBoThucUong;
import com.example.nienluannganh.model.DonViTinh;
import com.example.nienluannganh.model.GiaCaBoPhan;
import com.example.nienluannganh.model.GiaComBo;
import com.example.nienluannganh.model.GiaMonAn;
import com.example.nienluannganh.model.GiaThucUong;
import com.example.nienluannganh.model.KhuyenMaiMonAn;
import com.example.nienluannganh.model.KhuyenMaiThongThuong;
import com.example.nienluannganh.model.LoaiComBo;
import com.example.nienluannganh.model.LoaiMonAn;
import com.example.nienluannganh.model.LoaiThucUong;
import com.example.nienluannganh.model.MonAn;
import com.example.nienluannganh.model.NhanVien;
import com.example.nienluannganh.model.Sanh;
import com.example.nienluannganh.model.ThoiGianChucVu;
import com.example.nienluannganh.model.ThoiGianLamCa;
import com.example.nienluannganh.model.ThucUong;
import com.example.nienluannganh.model.embededid.CoSoLuongMonAnId;
import com.example.nienluannganh.model.embededid.CoSoLuongThucUongId;
import com.example.nienluannganh.model.embededid.ComBoMonAnId;
import com.example.nienluannganh.model.embededid.ComBoThucUongId;
import com.example.nienluannganh.model.embededid.KhuyenMaiMonAnId;
import com.example.nienluannganh.service.BanService;
import com.example.nienluannganh.service.BoPhanService;
import com.example.nienluannganh.service.CaService;
import com.example.nienluannganh.service.ChamCongService;
import com.example.nienluannganh.service.ChiTietYeuCauService;
import com.example.nienluannganh.service.CoSoLuongMonAnService;
import com.example.nienluannganh.service.CoSoLuongThucUongService;
import com.example.nienluannganh.service.ComBoMonAnService;
import com.example.nienluannganh.service.ComBoService;
import com.example.nienluannganh.service.ComBoThucUongService;
import com.example.nienluannganh.service.DonViTinhService;
import com.example.nienluannganh.service.GiaCaBoPhanService;
import com.example.nienluannganh.service.GiaComBoService;
import com.example.nienluannganh.service.GiaMonAnService;
import com.example.nienluannganh.service.GiaThucUongService;
import com.example.nienluannganh.service.KhuyenMaiMonAnSerVice;
import com.example.nienluannganh.service.KhuyenMaiThongThuongService;
import com.example.nienluannganh.service.LoaiComBoService;
import com.example.nienluannganh.service.LoaiMonAnService;
import com.example.nienluannganh.service.LoaiThucUongService;
import com.example.nienluannganh.service.MonAnService;
import com.example.nienluannganh.service.NhanVienService;
import com.example.nienluannganh.service.SanhService;
import com.example.nienluannganh.service.ThoiGianChucVuService;
import com.example.nienluannganh.service.ThucUongService;
import com.example.nienluannganh.service.YeuCauService;
import com.example.nienluannganh.service.hoiGianLamCaService;

import jakarta.persistence.EntityNotFoundException;
@SpringBootApplication
@EnableCaching
public class NienluannganhApplication {

	public static void main(String[] args) {
		ApplicationContext contex= SpringApplication.run(NienluannganhApplication.class, args);	
	}

}
