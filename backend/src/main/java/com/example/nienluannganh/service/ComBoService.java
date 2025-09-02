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

import com.example.nienluannganh.DTO.MonAnComBoItem;
import com.example.nienluannganh.DTO.ThucUongComBoItem;
import com.example.nienluannganh.DTO.giDinhMucDTO;
import com.example.nienluannganh.model.ComBo;
import com.example.nienluannganh.model.ComBoMonAn;
import com.example.nienluannganh.model.ComBoThucUong;
import com.example.nienluannganh.model.GiaComBo;
import com.example.nienluannganh.model.GiaMonAn;
import com.example.nienluannganh.model.LoaiComBo;
import com.example.nienluannganh.model.MonAn;
import com.example.nienluannganh.model.embededid.ComBoMonAnId;
import com.example.nienluannganh.model.embededid.ComBoThucUongId;
import com.example.nienluannganh.repository.ComBoMonAnRepository;
import com.example.nienluannganh.repository.ComBoRepository;
import com.example.nienluannganh.repository.ComBoThucUongRepository;
import com.example.nienluannganh.repository.GiaComBoRepository;
import com.example.nienluannganh.repository.LoaiComBoRepository;
import com.example.nienluannganh.repository.MonAnRepository;
import com.example.nienluannganh.repository.ThucUongRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ComBoService {
	@Autowired
	private ComBoRepository comBoRepository;
	
	@Autowired
	MonAnRepository m;
	
	@Autowired
	ThucUongRepository r;
	
	@Autowired
	ComBoMonAnRepository cg;
	
	@Autowired
	ThucUongRepository t;
	
	
	@Autowired
	private LoaiComBoRepository lcb;
	
	@Autowired
	ComBoThucUongRepository fg;
	@Autowired
	private GiaComBoRepository giaComBoRepository;
	
	@Value("${app.nameSource2}")
	private String resource;
	
	public ComBo saveComBo(ComBo c) {
		return comBoRepository.save(c);
	}
	
	public Optional<ComBo> getComBoById(int id) {
		return comBoRepository.findById(id);
	}
	
	public List<ComBo> getDsComboByLoai(int id){
		lcb.findById(id).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy loại món ăn cần tìm kiếm."));
		return comBoRepository.getDsComBoByLoai(id);
	}
	
	public List<Object> getDSMonAnByIdCombo(int id){
		return comBoRepository.getDSMonAnById(id);
	}
	public List<Object> getDSThucUongByIdCombo(int id){
		return comBoRepository.getDSThucUongById(id);
	}
	
	
	@Transactional(rollbackFor = {Throwable.class})
	public void save(
		    ComBo c,
		    MultipartFile file,
		    int idloai,
		    List<MonAnComBoItem> dsma,
		    List<ThucUongComBoItem> dstu,
		    String giaa
		) throws JsonMappingException, JsonProcessingException {
		    comBoRepository.save(c);
		    String duoi = "";
		    if (file != null && file.getSize() > 0) {
		        duoi = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		        File f = new File(resource +"combo"+ c.getId() + duoi);
		        try {
		            file.transferTo(f);
		            System.out.println("thành công >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: "+file.getSize());
		            c.setAnhGioiThieu("http://localhost:8080/datasource/" +"combo"+ c.getId() + duoi);
		        } catch (IllegalStateException | IOException e) {
		            throw new RuntimeException("Không thể lưu ảnh combo vui lòng thử lại", e); 
		        }
		    }
		    ObjectMapper op = new ObjectMapper();
		    List<giDinhMucDTO> giadm = op.readValue(giaa, new TypeReference<List<giDinhMucDTO>>() {});
		    
		    giadm.forEach(data->{
		    	if(data.getGia()<=0) {
		    		throw new EntityNotFoundException("Giá món ăn phải lớn hơn 0");
		    	}
		    	if(data.getDinhMucSoLuong().getSoLuongTu()==1) {
		    		c.setGia(data.getGia());
		    	}
		    	GiaComBo gg= new GiaComBo();
		    	gg.setDinhMucSoLuong(data.getDinhMucSoLuong());
		    	gg.setComBo(c);
		    	gg.setNgayBatDau(LocalDate.now());
		    	gg.setGia(data.getGia());
		    	gg.setNgayKetThuc(null);
		    	giaComBoRepository.save(gg);
		    });
		    c.setLoaiComBo(lcb.findById(idloai)
		        .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy loại combo phù hợp")));

		    dsma.forEach(data -> {
		        ComBoMonAn ca = new ComBoMonAn();
		        ca.setComBo(c);
		        ca.setMonAn(m.findById(data.getId())
		            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy món ăn phù hợp trong danh sách")));
		        ca.setSoLuong(data.getSoluong());
		        ComBoMonAnId cc = new ComBoMonAnId();
		        cc.setCB_ID(c.getId());
		        cc.setMA_ID(data.getId());
		        ca.setId(cc);
		        cg.save(ca);  
		    });
		    
		    dstu.forEach(data->{
		    	ComBoThucUong ca = new ComBoThucUong();
		        ca.setComBo(c);
		        ca.setThucUong(r.findById(data.getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy thức uống phù hợp")));
		        ca.setSoLuong(data.getSoluong());
		        ComBoThucUongId cc = new ComBoThucUongId();
		        cc.setCB_ID(c.getId());
		        cc.setTU_ID(data.getId());
		        ca.setId(cc);
		        fg.save(ca);
		    });
		}
	
	@Transactional(rollbackFor = {Throwable.class})
	public void capnhat(
			ComBo c,
			int id,
			MultipartFile file
			, String ggg
			) throws Exception {
		 ObjectMapper op = new ObjectMapper();
		 op.registerModule(new JavaTimeModule());
		    List<GiaComBo> gg = op.readValue(ggg, new TypeReference<List<GiaComBo>>() {});
		String duoi;
		ComBo cm= comBoRepository.findById(c.getId()).orElseThrow(()-> new Exception("Không tìm thấy combo phù hợp"));
		LoaiComBo l= lcb.findById(id).orElseThrow(()-> new Exception("Không tìm thấy loại combo phù hợp"));
		cm.setLoaiComBo(l);
		cm.setMoTa(c.getMoTa());
		cm.setTen(c.getTen());
		cm.setGia(gg.get(0).getGia());
		cm.setConHang(c.isConHang());
		comBoRepository.save(cm);
		float epsilon = 0.0001f;
		for(int i=0;i<gg.size();i++) {
			if(gg.get(i).getGia()<=0) {
				throw new EntityNotFoundException("Giá combo phải lớn hơn 0");
			}
			GiaComBo mm= giaComBoRepository.findById(gg.get(i).getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy giá combo cần điều chỉnh"));
			if(Math.abs(mm.getGia() - gg.get(i).getGia()) >= epsilon) {
				GiaComBo g= new GiaComBo();
				g.setNgayBatDau(LocalDate.now());
				g.setDinhMucSoLuong(mm.getDinhMucSoLuong());
				g.setGia(gg.get(i).getGia());
				g.setNgayKetThuc(null);
				g.setComBo(cm);
				giaComBoRepository.save(g);
				mm.setNgayKetThuc(LocalDate.now().minusDays(1));
				giaComBoRepository.save(mm);
			}
		}
		if(file!=null&&file.getSize()!=0) {
			duoi=duoi=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			File f= new File(resource+"combo"+cm.getId()+duoi);
			if (f.exists()) {
		        f.delete();
		    }
			try {
				file.transferTo(f);
				cm.setAnhGioiThieu("http://localhost:8080/datasource/combo"+cm.getId()+duoi);
				comBoRepository.save(cm);
			} catch (IllegalStateException e) {
				throw new IOException("Không thể lưu ảnh đã thay đổi, vui lòng thử lại");
			} catch (IOException e) {
				throw new IOException("Không thể lưu ảnh đã thay đổi, vui lòng thử lại");
			}
		
		}
		
	}

}
