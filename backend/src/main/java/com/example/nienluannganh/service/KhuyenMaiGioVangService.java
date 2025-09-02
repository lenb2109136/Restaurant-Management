package com.example.nienluannganh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nienluannganh.DTO.KhuyenMaiDTO;
import com.example.nienluannganh.DTO.KhuyenMaiGioVangDTO;
import com.example.nienluannganh.model.ComBo;
import com.example.nienluannganh.model.KhuyenMaiComBo;
import com.example.nienluannganh.model.KhuyenMaiGioVang;
import com.example.nienluannganh.model.KhuyenMaiGioVangComBo;
import com.example.nienluannganh.model.KhuyenMaiGioVangMonAn;
import com.example.nienluannganh.model.KhuyenMaiGioVangThucUong;
import com.example.nienluannganh.model.KhuyenMaiMonAn;
import com.example.nienluannganh.model.KhuyenMaiThongThuong;
import com.example.nienluannganh.model.KhuyenMaiThucUong;
import com.example.nienluannganh.model.MonAn;
import com.example.nienluannganh.model.ThucUong;
import com.example.nienluannganh.model.embededid.KhuyenMaiComBoId;
import com.example.nienluannganh.model.embededid.KhuyenMaiGioVangComBoId;
import com.example.nienluannganh.model.embededid.KhuyenMaiGioVangMonAnId;
import com.example.nienluannganh.model.embededid.KhuyenMaiGioVangThucUongId;
import com.example.nienluannganh.model.embededid.KhuyenMaiMonAnId;
import com.example.nienluannganh.model.embededid.KhuyenMaiThucUongId;
import com.example.nienluannganh.repository.ComBoRepository;
import com.example.nienluannganh.repository.KhuyenMaiComBoRepository;
import com.example.nienluannganh.repository.KhuyenMaiGioVangComboReposito;
import com.example.nienluannganh.repository.KhuyenMaiGioVangMonAnRepositr;
import com.example.nienluannganh.repository.KhuyenMaiGioVangRepository;
import com.example.nienluannganh.repository.KhuyenMaiGioVangThucUongRepository;
import com.example.nienluannganh.repository.KhuyenMaiMonAnRepository;
import com.example.nienluannganh.repository.KhuyenMaiThongThuongRepository;
import com.example.nienluannganh.repository.KhuyenMaiThucUongRepository;
import com.example.nienluannganh.repository.MonAnRepository;
import com.example.nienluannganh.repository.ThucUongRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class KhuyenMaiGioVangService {
	@Autowired
	private KhuyenMaiGioVangRepository  khuyenMaiGioVangRepository;
	
	@Autowired
	private MonAnRepository monAnRepository;
	
	@Autowired
	private ThucUongRepository thucUongRepository;
	
	@Autowired
	private ComBoRepository comBoRepository;
	
	@Autowired
	private KhuyenMaiGioVangMonAnRepositr khuyenMaiGioVangMonAnRepositr;
	
	@Autowired 
	private KhuyenMaiGioVangThucUongRepository khuyenMaiGioVangThucUongRepository;
	
	@Autowired
	private KhuyenMaiGioVangComboReposito khuyenMaiGioVangComboReposito;
	
	@Transactional(rollbackFor = {Throwable.class})
	public void savekhuyenmaitt(KhuyenMaiGioVangDTO km) throws Exception {
		KhuyenMaiGioVang k= km.getDt();
		if(k.getNgayGioApDung().isAfter(k.getNgayGioKetThuc())) {
			throw new Exception("Vui lòng chọn ngày bắt đầu sau ngày áp dụng");
		}
		if(k.getGioApDung().isAfter(k.getGioKetThuc())) {
			throw new Exception("Vui lòng chọn Khung giờ phù hợp");
		}
		if(km.getDscb().size()==0&&km.getDsma().size()==0&&km.getDstu().size()==0) {
			throw new Exception("Vui lòng chọn ít nhất 1 sản phẩm khuyến mãi");
		}
		khuyenMaiGioVangRepository.save(k);
		km.getDsma().forEach((data)->{
			MonAn m= monAnRepository.findById(data.getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy món ăn cần áp dụng"));
			KhuyenMaiGioVangMonAn kmma= new KhuyenMaiGioVangMonAn();
			KhuyenMaiGioVangMonAnId idd= new KhuyenMaiGioVangMonAnId();
			idd.setKM_ID(k.getId());
			idd.setMA_ID(m.getId());
			kmma.setId(idd);
			kmma.setGiaTriKhuyenMai(data.getGia());
			kmma.setKhuyenMaiThongThuong(k);
			kmma.setMonAn(m);
			kmma.setSoLuongTu(data.getSoLuong());
			khuyenMaiGioVangMonAnRepositr.save(kmma);
		});
		km.getDstu().forEach((data)->{
			ThucUong m= thucUongRepository.findById(data.getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy món ăn cần áp dụng"));
			KhuyenMaiGioVangThucUong kmma= new KhuyenMaiGioVangThucUong();
			KhuyenMaiGioVangThucUongId idd= new KhuyenMaiGioVangThucUongId();
			idd.setKM_ID(k.getId());
			idd.setTU_ID(m.getId());
			kmma.setId(idd);
			kmma.setGiaTriKhuyenMai(data.getGia());
			kmma.setKhuyenMaiThongThuong(k);
			kmma.setThucUong(m);
			kmma.setSoLuongTu(data.getSoLuong());
			khuyenMaiGioVangThucUongRepository.save(kmma);
		});
		km.getDstu().forEach((data)->{
			ComBo m= comBoRepository.findById(data.getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy món ăn cần áp dụng"));
			KhuyenMaiGioVangComBo kmma= new KhuyenMaiGioVangComBo();
			KhuyenMaiGioVangComBoId idd= new KhuyenMaiGioVangComBoId();
			idd.setKM_ID(k.getId());
			idd.setCB_ID(m.getId());
			kmma.setId(idd);
			kmma.setGiaTriKhuyenMai(data.getGia());
			kmma.setKhuyenMaiThongThuong(k);
			kmma.setComBo(m);
			kmma.setSoLuongTu(data.getSoLuong());
			khuyenMaiGioVangComboReposito.save(kmma);
		});
		
	}
	public List<KhuyenMaiGioVang> getall(){
		return khuyenMaiGioVangRepository.findAll();
	}
	
	public KhuyenMaiGioVang getallkmgv(int id) {
		return khuyenMaiGioVangRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy khuyến mãi cần cập nhật"));
	}
}