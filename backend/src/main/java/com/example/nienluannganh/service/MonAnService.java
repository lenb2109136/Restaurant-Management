package com.example.nienluannganh.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.nienluannganh.DTO.giDinhMucDTO;
import com.example.nienluannganh.model.GiaMonAn;
import com.example.nienluannganh.model.LoaiMonAn;
import com.example.nienluannganh.model.MonAn;
import com.example.nienluannganh.repository.GiaMonAnRepository;
import com.example.nienluannganh.repository.MonAnRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class MonAnService {
	@Autowired
	private GiaMonAnService gm;
	
	@Autowired
	private GiaMonAnRepository giaMonAnRepository;
	
	@Autowired
	private MonAnRepository monAnRepository;
	
	
	@Autowired
	RedisTemplate<String, Object> template;
	@Autowired
	private LoaiMonAnService lma;
	@Value("${app.nameSource2}")
	private String resource;
	
	@Transactional
	public MonAn saveMonAn(MonAn ma) {
		MonAn m=monAnRepository.save(ma);
//		GiaMonAn g= new GiaMonAn(ma.getGia(),LocalDate.now(),m);
//		gm.saveGiaMonAn(g);
		return m;
	}
	
	public Optional<MonAn> getMonAnById(int id) {
		return monAnRepository.findById(id);
	}
	
//	@Cacheable(value = "item", key = "#loai")
	public List<MonAn>getMonAnByLoai(int loai){
		  System.out.println("Calling getMonAnByLoai with loai = " + loai);

		    // Kiểm tra xem dữ liệu có trong cache không
		    List<MonAn> monAnList = (List<MonAn>) template.opsForValue().get("loai:" + loai);

		    if (monAnList == null) {
		        // Nếu không có trong cache, gọi repository để lấy dữ liệu
		        monAnList = monAnRepository.getMonAnByLoai(loai);
		        
		        // Lưu dữ liệu vào cache và đặt thời gian sống cho cache (30 phút)
		        template.opsForValue().set("loai:" + loai, monAnList, 30, TimeUnit.MINUTES);
		    } else {
		        System.out.println("Cache hit for loai = " + loai);
		    }

		    return monAnList;
	}
	@Transactional
	public boolean xoaMonAn(int id) {
		if (monAnRepository.existsById(id)) {
	        monAnRepository.deleteById(id);
	        return true;
	    }
	    return false;
	}
	@Transactional(rollbackFor = {IOException.class,IllegalStateException.class})
	public void updateMonAn(MonAn m, MultipartFile file, String ggg) throws IOException {
		 ObjectMapper op = new ObjectMapper();
		 op.registerModule(new JavaTimeModule());
		    List<GiaMonAn> gg = op.readValue(ggg, new TypeReference<List<GiaMonAn>>() {});
		MonAn k=monAnRepository.findById(m.getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy món ăn cần chỉnh sửa"));
		k.setChay(m.getChay());
		k.setConHang(m.getConHang());
		k.setGia(gg.get(0).getGia());
		k.setTen(m.getTen());
		k.setMoTa(m.getMoTa());
		monAnRepository.save(k);
		float epsilon = 0.0001f;
		for(int i=0;i<gg.size();i++) {
			if(gg.get(i).getGia()<=0) {
				throw new EntityNotFoundException("Giá món ăn phải lớn hơn 0");
			}
			GiaMonAn mm= giaMonAnRepository.findById(gg.get(i).getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy giá món ăn cần điều chỉnh"));
			if(Math.abs(mm.getGia() - gg.get(i).getGia()) >= epsilon) {
				GiaMonAn g= new GiaMonAn();
				g.setBatDau(LocalDate.now());
				g.setDinhMucSoLuong(mm.getDinhMucSoLuong());
				g.setGia(gg.get(i).getGia());
				g.setKetThuc(null);
				g.setMonAn(m);
				giaMonAnRepository.save(g);
				System.out.println("huhuh haa: "+g.getGia());
				mm.setKetThuc(LocalDate.now().minusDays(1));
				giaMonAnRepository.save(mm);
			}
		}
		String duoi;
		if(file==null || file.getSize()==0) {
			
		}
		else {
			duoi=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			File f= new File(resource+k.getId()+duoi);
			System.out.println(resource+k.getId()+duoi);
			if (f.exists()) {
		        f.delete();
		    }
			try {
				file.transferTo(f);
				k.setAnhGioiThieu("http://localhost:8080/datasource/"+k.getId()+duoi);
			} catch (IllegalStateException e) {
				throw new IOException("Không thể lưu ảnh đã thay đổi, vui lòng thử lại");
			} catch (IOException e) {
				throw new IOException("Không thể lưu ảnh đã thay đổi, vui lòng thử lại");
			}
		}
	}
	@Transactional(rollbackFor = {Throwable.class})
	public void themmonan(
			 MonAn m,
			MultipartFile file,
			int maloai,
			String gia
			) throws Throwable {
		LoaiMonAn l= lma.getLoaiMonAnById(maloai).orElseThrow(()->new EntityNotFoundException("Không tìm thấy loại món ăn phù hợp"));
	    m.setLoaiMonAn(l);
	    m.setTinhTrangSuDung(true);
	   saveMonAn(m);
	    ObjectMapper op = new ObjectMapper();
	    List<giDinhMucDTO> giadm = op.readValue(gia, new TypeReference<List<giDinhMucDTO>>() {});
	    
	    giadm.forEach(data->{
	    	if(data.getGia()<=0) {
	    		throw new EntityNotFoundException("Giá món ăn phải lớn hơn 0");
	    	}
	    	if(data.getDinhMucSoLuong().getSoLuongTu()==1) {
	    		m.setGia(data.getGia());
	    	}
	    	GiaMonAn gg= new GiaMonAn(data.getGia(),LocalDate.now(), m);
	    	gg.setDinhMucSoLuong(data.getDinhMucSoLuong());
	    	gm.saveGiaMonAn(gg);
	    });
	    if(file!=null) {
	    	String duoi=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
	    	File f= new File("C:/Users/ADMIN/Documents/A- CT263/images/"+m.getId()+duoi);
	    	if(f.exists()==false) {
	    		try {
					f.createNewFile();
				} catch (IOException e) {
					xoaMonAn(m.getId());
					throw new IOException("Lưu hình ảnh món ăn thất bại ,vui lòng thử lại");
				}
	    	}
	    	try {
				file.transferTo(f);
				m.setAnhGioiThieu("http://localhost:8080/datasource/"+m.getId()+duoi);
				saveMonAn(m);
			} catch (IllegalStateException e) {
				if(f.exists()==true) {
					f.delete();
				}
				xoaMonAn(m.getId());
				throw new IOException("Lưu hình ảnh món ăn thất bại ,vui lòng thử lại");
			} catch (IOException e) {
				if(f.exists()==true) {
					f.delete();
				}
			xoaMonAn(m.getId());
				throw new IOException("Lưu hình ảnh món ăn thất bại ,vui lòng thử lại");
			}
	    }
	}

	public List<MonAn> getallmonan(){
		return monAnRepository.getall();
	}
}
