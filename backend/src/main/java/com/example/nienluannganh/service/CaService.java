package com.example.nienluannganh.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nienluannganh.exception.exception;
import com.example.nienluannganh.model.BoPhan;
import com.example.nienluannganh.model.Ca;
import com.example.nienluannganh.model.ChucVu;
import com.example.nienluannganh.model.GiaCaBoPhan;
import com.example.nienluannganh.model.NhanVien;
import com.example.nienluannganh.repository.CaRepository;
import com.example.nienluannganh.repository.ChucVuRepository;
import com.example.nienluannganh.repository.GiaCaBoPhanRepository;
import com.example.nienluannganh.repository.NhanVienRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CaService {
	@Autowired
	private CaRepository carrepo;
	
	@Autowired
	private BoPhanService boPhanService;
	
	@Autowired 
	NhanVienRepository nhanVienRepository;
	
	@Autowired
	private GiaCaBoPhanRepository s;
	
	@Autowired
	private ChucVuRepository cvrepo;
	
	
	public Ca saveca(Ca  c) {
		return carrepo.save(c);
	}
	public Ca getCaById(int id) {
		return carrepo.getbyId(id);
	}
	
	public List<Map<Object, Object>> getDsCa(){
		
return carrepo.getdsnow();
	}
	@Transactional(rollbackFor = {Throwable.class})
	public void luuds(LocalTime bd, LocalTime kt, List<Map<String,Object>> ds1) throws Exception {
		DateTimeFormatter f= DateTimeFormatter.ofPattern("HH:mm:ss");
		List<Ca> cas=carrepo.kt(bd.format(f), kt.format(f));
		System.out.println("Thhanwgff chó "+bd.format(f));
		
		if(cas.size()>0) {
			System.out.println("số lượng ca trùng: "+cas.size());
			System.out.println("thời gian trùng là: "+cas.get(0).getThoiGianBatDau().format(f));
			throw new Exception("Thời gian bị trùng với ca có giờ bắt đầu "+cas.get(0).getThoiGianBatDau().format(f)+" Thời gian kết thúc : "+cas.get(0).getThoiGianKetThuc().format(f));
			
		}
		
		if(ds1.get(0).containsKey("dongia")==false) {
			throw new Exception("Dữ liệu ca làm bị thiếu thông tin đơn giá");
		}
		
		if(ds1.get(0).containsKey("bpid")==false) {
			throw new Exception("Dữ liệu ca làm bị thiếu thông tin bộ phận");
		}
		
		Ca c1 = new Ca();
		System.out.println(bd.format(f));
		System.out.println("====== "+bd.getHour());
		c1.setThoiGianBatDau(bd);
		c1.setThoiGianKetThuc(kt);
		c1.setConSuDung(true);
		carrepo.save(c1);
		for(int i=0;i<ds1.size();i++) {
			Integer a=0;
			try {
				a=(Integer)ds1.get(i).get("bpid");
				if(a==null || a==0) {
					throw new Exception("Không tồn tại thông tin về bộ phận");
				}
			} catch (Exception e) {
				 new Exception("Thông tin về bộ phận chưa hợp lệ");
			}
			
			ChucVu b= cvrepo.findById((int)a).orElseThrow(()-> new Exception("Không tồn tại bộ phận cần cập nhật ca"));
			
			System.out.println();
			if(Float.parseFloat((String)ds1.get(i).get("gia")) <=0) {
				throw new Exception("Giá của bộ phận "+b.getTen()+" phải lớn hơn không");
			}
			
			GiaCaBoPhan g= new GiaCaBoPhan();
			g.setBatDau(LocalDate.now());
			g.setChucVu(b);
			g.setBoPhan(b.getBoPhan());
			g.setCa(c1);
			g.setGia(Float.parseFloat((String)ds1.get(i).get("gia")));
			g.setKetThuc(null);
			s.save(g);
		}
	}
	
	@Transactional(rollbackFor = {Throwable.class})
	public void setgia(Float gia,int  id) throws Exception {
		if(gia<=0) {
			throw new Exception("Giá ca làm phải lớn hơn 0");
		}
		GiaCaBoPhan g= new GiaCaBoPhan();
		GiaCaBoPhan g2= s.findById(id).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy thông tin gia cânf cập nhật"));
		
		g.setBatDau(LocalDate.now());
		g.setBoPhan(g2.getBoPhan());
		g.setCa(g2.getCa());
		g.setChucVu(g2.getChucVu());
		g.setGia(gia);
		g.setKetThuc(null);
		s.save(g);
		if(g2.getBatDau().equals(LocalDate.now())) {
			s.delete(g2);
		}
		else {
			g2.setKetThuc(LocalDate.now());
			s.save(g2);
		}
		System.out.println("thành công qua đây"+id);
	}
	
	public List<Map<Object, Object>> thongke(){
		return carrepo.thongkeca();
	}
	
	public List<Map<Object, Object>> getdsthanhvien(){
		return carrepo.getdsnvhientai();	}
	
	
	public List<Map<Object, Object>> cahientai(int id){
		NhanVien n= nhanVienRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy nhân viên cần điều chỉnh"));
		return carrepo.calamhientai(id);
	}
	public List<Ca> getall(){
		return carrepo.getall();
	}
}
