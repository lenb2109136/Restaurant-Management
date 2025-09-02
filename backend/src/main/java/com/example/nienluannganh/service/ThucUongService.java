package com.example.nienluannganh.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.nienluannganh.DTO.giDinhMucDTO;
import com.example.nienluannganh.model.DonViTinh;
import com.example.nienluannganh.model.GiaMonAn;
import com.example.nienluannganh.model.GiaThucUong;
import com.example.nienluannganh.model.LoaiThucUong;
import com.example.nienluannganh.model.ThucUong;
import com.example.nienluannganh.repository.GiaThucUongRepository;
import com.example.nienluannganh.repository.ThucUongRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ThucUongService {
	@Autowired
	private ThucUongRepository thucUongRepository;
	@Autowired
	private DonViTinhService dvt;
	
	@Autowired
	private GiaThucUongRepository giaThucUongRepository;
	@Autowired
	private LoaiThucUongService ltu;
	@Value("${app.nameSource2}")
	private String resource;
	@Transactional(rollbackFor = {IOException.class,IllegalStateException.class})
	public ThucUong saveThucUong(ThucUong t, int idloai, int iddvt, MultipartFile file,String giaa) throws IOException {
		DonViTinh dv= dvt.getDonViTinhById(iddvt).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy đơn vị tính yêu cầu"));
		LoaiThucUong l= ltu.getLoaiThucUongById(idloai).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy loại thức uống yêu cầu"));
		t.setLoaiThucUong(l);
		t.setDonViTinh(dv);
		
		String duoi;
		ThucUong tu= thucUongRepository.save(t);
		
		ObjectMapper op = new ObjectMapper();
	    List<giDinhMucDTO> giadm = op.readValue(giaa, new TypeReference<List<giDinhMucDTO>>() {});
	    
	    giadm.forEach(data->{
	    	if(data.getGia()<=0) {
	    		throw new EntityNotFoundException("Giá thức uống phải lớn hơn 0");
	    	}
	    	if(data.getDinhMucSoLuong().getSoLuongTu()==1) {
	    		t.setGia(data.getGia());
	    	}
	    	GiaThucUong gg= new GiaThucUong();
	    	gg.setBatDau(LocalDate.now());
	    	gg.setDinhMucSoLuong(data.getDinhMucSoLuong());
	    	gg.setGia(data.getGia());
	    	gg.setDinhMucSoLuong(data.getDinhMucSoLuong());
	    	gg.setThucUong(t);
	    	giaThucUongRepository.save(gg);
	    });
	    	if(file==null||file.getSize()==0) {
			
		}
		else {
			duoi=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			File f= new File(resource+"tu"+tu.getId()+duoi);
			if(f.exists()==true) {
				f.delete();
			}
			try {
				file.transferTo(f);
				tu.setAnhGioiThieu("http://localhost:8080/datasource/"+"tu"+tu.getId()+duoi);
				thucUongRepository.save(t);
			} catch (IllegalStateException e) {
				throw new IOException("Không thể lưu ảnh thức uống, vui lòng thử lại.");
			} catch (IOException e) {
				throw new IOException("Không thể lưu ảnh thức uống, vui lòng thử lại.");
			}
		}
		return tu;
		
	}
	
	public Optional<ThucUong> getThucUongById(int id) {
		 return thucUongRepository.findById(id);
	}
	
	public List<ThucUong> getThucUongByLoai(int id)
	{
		return thucUongRepository.getDSThucUong(id);
	}	
	public void ngung(int id) {
		ThucUong t= thucUongRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy thức uống cần ngưng sử dụng."));
		t.setTinhTrangSuDung(false);
		thucUongRepository.save(t);
	}
	@Transactional(rollbackFor = {IOException.class,IllegalStateException.class})
	
	public void capNhatThucUong(
			MultipartFile file,
			int idloai,
			int iddvt,
			ThucUong t,
			String ggg
			) throws IOException {
		ObjectMapper op = new ObjectMapper();
		 op.registerModule(new JavaTimeModule());
		    List<GiaThucUong> gg = op.readValue(ggg, new TypeReference<List<GiaThucUong>>() {});
		String duoi;
		System.out.println("đang cập nhật cho thức uống"+t.getId());
			ThucUong tu=thucUongRepository.findById(t.getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy thức uống cần cập nhật"));
			if(tu.getLoaiThucUong().getLtuId()!=idloai) {
				LoaiThucUong l= ltu.getLoaiThucUongById(idloai).orElseThrow(()-> new EntityNotFoundException("Không tìm loại thức uống cần chỉnh"));
				tu.setLoaiThucUong(l);
				
			}
			
			if(tu.getDonViTinh().getDvtId()!=iddvt) {
				DonViTinh d=dvt.getDonViTinhById(iddvt).orElseThrow(()-> new EntityNotFoundException("Không tìm đơn vị tính cần chỉnh") );
				tu.setDonViTinh(d);
			}
			tu.setGia(gg.get(0).getGia());
			tu.setConHang(t.isConHang());
			tu.setMoTa(t.getMoTa());
			tu.setTen(t.getTen());
			thucUongRepository.save(tu);
			float epsilon = 0.0001f;
			for(int i=0;i<gg.size();i++) {
				if(gg.get(i).getGia()<=0) {
					throw new EntityNotFoundException("Giá món ăn phải lớn hơn 0");
				}
				GiaThucUong mm= giaThucUongRepository.findById(gg.get(i).getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy giá thức uống cần điều chỉnh"));
				if(Math.abs(mm.getGia() - gg.get(i).getGia()) >= epsilon) {
					GiaThucUong g= new GiaThucUong();
					g.setBatDau(LocalDate.now());
					g.setDinhMucSoLuong(mm.getDinhMucSoLuong());
					g.setGia(gg.get(i).getGia());
					g.setKetThuc(null);
					g.setThucUong(tu);
					giaThucUongRepository.save(g);
					mm.setKetThuc(LocalDate.now().minusDays(1));
					giaThucUongRepository.save(mm);
				}
			}
			if(file==null || file.getSize()==0) {
				
			}
			else {
				duoi=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				File f= new File(resource+tu.getId()+duoi);
					
				if(f.exists()==true) {
					f.delete();
					file.transferTo(f);
					
				}
				try {
					file.transferTo(f);
					
				} catch (IllegalStateException e) {
					throw new IOException("Cập nhật ảnh không thành công, vui lòng thử lại");
				} catch (IOException e) {
					throw new IOException("Cập nhật ảnh không thành công, vui lòng thử lại");
				}
			}
			
	}
	public List<ThucUong> getall(){
		return thucUongRepository.getall();
	}
}
