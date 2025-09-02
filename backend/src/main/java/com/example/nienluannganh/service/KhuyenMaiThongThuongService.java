package com.example.nienluannganh.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nienluannganh.DTO.KhuyenMaiDTO;
import com.example.nienluannganh.exception.exception;
import com.example.nienluannganh.model.ComBo;
import com.example.nienluannganh.model.KhuyenMai;
import com.example.nienluannganh.model.KhuyenMaiComBo;
import com.example.nienluannganh.model.KhuyenMaiMonAn;
import com.example.nienluannganh.model.KhuyenMaiThongThuong;
import com.example.nienluannganh.model.KhuyenMaiThucUong;
import com.example.nienluannganh.model.MonAn;
import com.example.nienluannganh.model.ThucUong;
import com.example.nienluannganh.model.embededid.KhuyenMaiComBoId;
import com.example.nienluannganh.model.embededid.KhuyenMaiMonAnId;
import com.example.nienluannganh.model.embededid.KhuyenMaiThucUongId;
import com.example.nienluannganh.repository.CoSoLuongComBoRepository;
import com.example.nienluannganh.repository.CoSoLuongMonAnRepository;
import com.example.nienluannganh.repository.CoSoLuongThucUongRepository;
import com.example.nienluannganh.repository.ComBoRepository;
import com.example.nienluannganh.repository.KhuyenMaiComBoRepository;
import com.example.nienluannganh.repository.KhuyenMaiMonAnRepository;
import com.example.nienluannganh.repository.KhuyenMaiThongThuongRepository;
import com.example.nienluannganh.repository.KhuyenMaiThucUongRepository;
import com.example.nienluannganh.repository.MonAnRepository;
import com.example.nienluannganh.repository.ThucUongRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class KhuyenMaiThongThuongService {
	@Autowired
	private KhuyenMaiThongThuongRepository  khuyenMaiThongThuongRepository;
	
	@Autowired
	private MonAnRepository monAnRepository;
	
	@Autowired
	private ThucUongRepository thucUongRepository;
	
	@Autowired
	private ComBoRepository comBoRepository;
	
	@Autowired
	private KhuyenMaiMonAnRepository khuyenMaiMonAnRepository;
	
	@Autowired 
	private KhuyenMaiThucUongRepository khuyenMaiThucUongRepository;
	
	@Autowired
	private KhuyenMaiComBoRepository khuyenMaiComBoRepository;
	
	@Autowired
	private CoSoLuongMonAnRepository coSoLuongMonAnRepository;
	
	@Autowired
	private CoSoLuongComBoRepository coSoLuongComBoRepository;
	
	@Autowired
	private CoSoLuongThucUongRepository coSoLuongThucUongRepository;
	
	public KhuyenMaiThongThuong saveKhuyenMaiThongThuong(KhuyenMaiThongThuong km) {
		return khuyenMaiThongThuongRepository.save(km);
	}
	
	public Optional<KhuyenMaiThongThuong> getkhuyenmaiKhuyenMaiThongThuongById(int id) {
		return khuyenMaiThongThuongRepository.findById(id);
	}
	@Transactional(rollbackFor = {Throwable.class})
	public void savekhuyenmaitt(KhuyenMaiDTO km) throws Exception {
		KhuyenMaiThongThuong k= km.getDt();
		if(k.getNgayGioApDung().isAfter(k.getNgayGioKetThuc())) {
			throw new Exception("Vui lòng chọn ngày bắt đầu sau ngày áp dụng");
		}
		if(km.getDscb().size()==0&&km.getDsma().size()==0&&km.getDstu().size()==0) {
			throw new Exception("Vui lòng chọn ít nhất 1 sản phẩm khuyến mãi");
		}
		khuyenMaiThongThuongRepository.save(k);
		km.getDsma().forEach((data)->{
			MonAn m= monAnRepository.findById(data.getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy món ăn cần áp dụng"));
			KhuyenMaiMonAn kmma= new KhuyenMaiMonAn();
			KhuyenMaiMonAnId idd= new KhuyenMaiMonAnId();
			idd.setKM_ID(k.getId());
			idd.setMA_ID(m.getId());
			kmma.setId(idd);
			kmma.setGiaTriKhuyenMai(data.getGia());
			kmma.setKhuyenMaiThongThuong(k);
			kmma.setMonAn(m);
			kmma.setSoLuongTu(data.getSoLuong());
			khuyenMaiMonAnRepository.save(kmma);
		});
		km.getDstu().forEach((data)->{
			ThucUong m= thucUongRepository.findById(data.getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy  thức uống cần áp dụng"));
			KhuyenMaiThucUong kmma= new KhuyenMaiThucUong();
			KhuyenMaiThucUongId idd= new KhuyenMaiThucUongId();
			idd.setKM_ID(k.getId());
			idd.setTU_ID(m.getId());
			kmma.setId(idd);
			kmma.setGiaTriKhuyenMai(data.getGia());
			kmma.setKhuyenMaiThongThuong(k);
			kmma.setThucUong(m);
			kmma.setSoLuongTu(data.getSoLuong());
			khuyenMaiThucUongRepository.save(kmma);
		});
		km.getDscb().forEach((data)->{
			ComBo m= comBoRepository.findById(data.getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy combo cần áp dụng"));
			KhuyenMaiComBo kmma= new KhuyenMaiComBo();
			KhuyenMaiComBoId idd= new KhuyenMaiComBoId();
			idd.setKM_ID(k.getId());
			idd.setCB_ID(m.getId());
			kmma.setId(idd);
			kmma.setGiaTriKhuyenMai(data.getGia());
			kmma.setKhuyenMaiThongThuong(k);
			kmma.setComBo(m);
			kmma.setSoLuongTu(data.getSoLuong());
			khuyenMaiComBoRepository.save(kmma);
		});
		
	}
	
	public List<KhuyenMaiThongThuong> getall() { 
		return khuyenMaiThongThuongRepository.findAll();
	}
	
	public Map<Object, Object> getNangSuat(int idkm) throws Exception{
		System.out.println("vào hàm");
		Map<Object, Object> re= new HashMap<Object, Object>();
		KhuyenMaiThongThuong kmtt= khuyenMaiThongThuongRepository.findById(idkm).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy khuyến mãi cần tìm"));
		if(kmtt.getNgayGioApDung().toLocalDate().isAfter(LocalDate.now())) {
			throw new Exception("Khuyến mãi chưa được áp dụng không thể thống kê");
		}
		for(int i=0;i<kmtt.getKhuyenMaiMonAn().size();i++) {
			List<Map<Object, Object>> l= new ArrayList<Map<Object,Object>>();
			LocalDate d=kmtt.getNgayGioApDung().toLocalDate();
			while(d.isBefore(LocalDate.now())&&!d.isAfter(kmtt.getNgayGioKetThuc().toLocalDate())) {
				Integer a=coSoLuongMonAnRepository.TongSo(kmtt.getKhuyenMaiMonAn().get(i).getSoLuongTu(),
						kmtt.getKhuyenMaiMonAn().get(i).getMonAn().getId(), d);
				if(a==null) {
					a=0;
				}
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put(d, a);
				l.add(map);
				d=d.plusDays(1);
			}
			re.put(kmtt.getKhuyenMaiMonAn().get(i).getMonAn().getTen(),l);
		}
		System.out.println("Số lượng phần tử: "+kmtt.getKhuyenMaiThucUong().size());
		for(int i=0;i<kmtt.getKhuyenMaiThucUong().size();i++) {
			List<Map<Object, Object>> l= new ArrayList<Map<Object,Object>>();
			LocalDate d=kmtt.getNgayGioApDung().toLocalDate();
			while(d.isBefore(LocalDate.now())&&!d.isAfter(kmtt.getNgayGioKetThuc().toLocalDate())) {
				System.out.println();
				Integer a=coSoLuongThucUongRepository.TongSo(kmtt.getKhuyenMaiThucUong().get(i).getSoLuongTu(),
						kmtt.getKhuyenMaiThucUong().get(i).getThucUong().getId(), d);
				if(a==null) {
					a=0;
				}
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put(d, a);
				l.add(map);
				d=d.plusDays(1); 
			}
			d=kmtt.getNgayGioApDung().toLocalDate();
			re.put(kmtt.getKhuyenMaiThucUong().get(i).getThucUong().getTen(),l);
		}
		for(int i=0;i<kmtt.getKhuyenMaiComBo().size();i++) {
			List<Map<Object, Object>> l= new ArrayList<Map<Object,Object>>();
			
			LocalDate d=kmtt.getNgayGioApDung().toLocalDate();
			Integer a1=1;
			while(d.isBefore(LocalDate.now())&&!d.isAfter(kmtt.getNgayGioKetThuc().toLocalDate())) {
				System.out.println(d.toString());
				Integer a=coSoLuongMonAnRepository.TongSo(kmtt.getKhuyenMaiComBo().get(i).getSoLuongTu(),
						kmtt.getKhuyenMaiComBo().get(i).getComBo().getId(), d);
				if(a==null) {
					a=0;
				}
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put(d, a);
				l.add(map);
				d=d.plusDays(1);
				a1++;
			}
			re.put(kmtt.getKhuyenMaiComBo().get(i).getComBo().getTen(),l);
		}
	
		return re;
	}
}
