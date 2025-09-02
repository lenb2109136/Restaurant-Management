package com.example.nienluannganh.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nienluannganh.model.ComBoMonAn;
import com.example.nienluannganh.model.ComBoThucUong;
import com.example.nienluannganh.model.embededid.ComBoMonAnId;
import com.example.nienluannganh.model.embededid.ComBoThucUongId;
import com.example.nienluannganh.repository.ComBoThucUongRepository;

@Service
public class ComBoThucUongService {
	@Autowired
	private ComBoThucUongRepository comBoThucUongRepository;
	
	public ComBoThucUong saveComBoThucUong(ComBoThucUong u) {
		return comBoThucUongRepository.save(u);
	}
	
	public Optional<ComBoThucUong> getComBoThucUongById(ComBoThucUongId id) {
		return comBoThucUongRepository.findById(id);
	}
	
	@Transactional(rollbackFor = {Throwable.class})
	public void capNhatMonAn(List<Map<String,Object>> dsu, List<Map<String,Object>> dsd) throws Exception {
		for(int i=0;i <dsu.size();i++) {
			Map<String, Object> a= dsu.get(i);
			if(a.containsKey("id")==false || a.containsKey("idcombo")==false|| a.containsKey("soluong")==false
					) {
				throw new Exception("Dữ liệu cần cập nhật không hợp lệ");
			}
			
			if(a.containsKey("soluong")==true && (Integer)a.get("soluong")<=0 ) {
					throw new Exception("Số lượng của món ăn phải lớn hơn 0");
			}
			
			ComBoThucUongId e= new ComBoThucUongId();
			e.setCB_ID((Integer)a.get("idcombo"));
			e.setTU_ID((Integer) a.get("id"));
			System.out.println("giá trị là : "+(int)a.get("idcombo"));
			System.out.println((Integer) a.get("id"));
			ComBoThucUong c= comBoThucUongRepository.findById(e).orElseThrow(()-> new Exception("Không tìm thấy dữ liệu phù hợp"));
			c.setSoLuong((Integer)a.get("soluong"));
			comBoThucUongRepository.save(c);
		}
		for(int i=0;i <dsd.size();i++) {
			System.out.println("vào xóa có > "+ dsd.size());
			Map<String, Object> a= dsd.get(i);
			if(a.containsKey("id")==false || a.containsKey("idcombo")==false
					) {
				throw new Exception("Dữ liệu cần cập nhật không hợp lệ");
			}
			ComBoThucUongId e= new ComBoThucUongId();
			e.setCB_ID((Integer)a.get("idcombo"));
			e.setTU_ID((Integer) a.get("id"));
			ComBoThucUong c= comBoThucUongRepository.findById(e).orElseThrow(()-> new Exception("Không tìm thấy dữ liệu phù hợp"));
			comBoThucUongRepository.delete(c);
		}
	}
	
}
