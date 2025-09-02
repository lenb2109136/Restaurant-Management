package com.example.nienluannganh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nienluannganh.DTO.KhuyenMaiDTO;
import com.example.nienluannganh.DTO.KhuyenMaiGioVangDTO;
import com.example.nienluannganh.DTO.KhuyenMaiItem;
import com.example.nienluannganh.model.ComBo;
import com.example.nienluannganh.model.KhuyenMaiComBo;
import com.example.nienluannganh.model.KhuyenMaiGioVang;
import com.example.nienluannganh.model.KhuyenMaiMonAn;
import com.example.nienluannganh.model.KhuyenMaiThongThuong;
import com.example.nienluannganh.model.KhuyenMaiThucUong;
import com.example.nienluannganh.model.MonAn;
import com.example.nienluannganh.model.ThucUong;
import com.example.nienluannganh.model.embededid.KhuyenMaiComBoId;
import com.example.nienluannganh.model.embededid.KhuyenMaiMonAnId;
import com.example.nienluannganh.model.embededid.KhuyenMaiThucUongId;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.repository.ComBoRepository;
import com.example.nienluannganh.repository.KhuyenMaiComBoRepository;
import com.example.nienluannganh.repository.KhuyenMaiMonAnRepository;
import com.example.nienluannganh.repository.KhuyenMaiThucUongRepository;
import com.example.nienluannganh.repository.MonAnRepository;
import com.example.nienluannganh.repository.ThucUongRepository;
import com.example.nienluannganh.service.KhuyenMaiGioVangService;
import com.example.nienluannganh.service.KhuyenMaiThongThuongService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/khuyenmai")
public class KhuyenMaiController {
	@Autowired
	private KhuyenMaiThongThuongService khuyenMaiThongThuongService;
	
	@Autowired
	private KhuyenMaiGioVangService khuyenMaiGioVangService;
	
	@Autowired
	private KhuyenMaiMonAnRepository khuyenMaiMonAnRepository;
	
	@Autowired
	private KhuyenMaiThucUongRepository khuyenMaiThucUongRepository;
	
	@Autowired
	private MonAnRepository monAnRepository;
	
	@Autowired
	private ThucUongRepository thucUong;
	
	@Autowired
	private ComBoRepository comBo;
	
	@Autowired
	private KhuyenMaiComBoRepository khuyenMaiComBoRepository;
	@PostMapping("/save")
	public ResponseEntity<response> savekhuyenmaithongthuong(@Valid @RequestBody  KhuyenMaiDTO khuyenMaiDTO) throws Exception {
		khuyenMaiThongThuongService.savekhuyenmaitt(khuyenMaiDTO);
		response r= new response(HttpStatus.OK,"ok", null);
		return new ResponseEntity(r,HttpStatus.OK);
	}
	@PostMapping("/savekmgv")
	public ResponseEntity<response> savekhuyenmaigiovang(@Valid @RequestBody  KhuyenMaiGioVangDTO khuyenMaiDTO) throws Exception {
		khuyenMaiGioVangService.savekhuyenmaitt(khuyenMaiDTO);
		response r= new response(HttpStatus.OK,"ok", null);
		return new ResponseEntity(r,HttpStatus.OK);
	}
	
	@GetMapping("getkmttdto")
	public ResponseEntity<response> getkmttdto(@RequestParam(name="idkmtt",required = true) Integer id) throws Exception{
		if(id==null|| id==0) {
			throw new Exception("Vui lòng cung cấp thông tin khuyến mãi phù hợp");
		}
		KhuyenMaiThongThuong t= khuyenMaiThongThuongService.getkhuyenmaiKhuyenMaiThongThuongById(id).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy khuyến mãi"));
		KhuyenMaiDTO dto= new KhuyenMaiDTO();
		dto.setDt(t);
		List<KhuyenMaiMonAn> kmma= khuyenMaiMonAnRepository.getallkmma(id);
		List<KhuyenMaiThucUong> kmtu=khuyenMaiThucUongRepository.getallkmtu(id);
		List<KhuyenMaiComBo> kmcb= khuyenMaiComBoRepository.getallkmcb(id);
		List<KhuyenMaiItem> dsma= new ArrayList<KhuyenMaiItem>();
		List<KhuyenMaiItem> dstu= new ArrayList<KhuyenMaiItem>();
		List<KhuyenMaiItem> dscb= new ArrayList<KhuyenMaiItem>();
		kmma.forEach((data)->{
		KhuyenMaiItem i= new KhuyenMaiItem();
		i.setGia(data.getGiaTriKhuyenMai());
		i.setId(data.getMonAn().getId());
		i.setSoLuong(data.getSoLuongTu());
		dsma.add(i);
		});
		kmtu.forEach((data)->{
			KhuyenMaiItem i= new KhuyenMaiItem();
			i.setGia(data.getGiaTriKhuyenMai());
			i.setId(data.getThucUong().getId());
			i.setSoLuong(data.getSoLuongTu());
		dstu.add(i);
			});
		kmcb.forEach((data)->{
			KhuyenMaiItem i= new KhuyenMaiItem();
			i.setGia(data.getGiaTriKhuyenMai());
			i.setId(data.getComBo().getId());
			i.setSoLuong(data.getSoLuongTu());
		dscb.add(i);
			});
		dto.setDsma(dsma);
		dto.setDstu(dstu);
		dto.setDscb(dscb);
		return new ResponseEntity(new response(HttpStatus.OK,"Ok", dto),HttpStatus.OK);
	}
	
	@GetMapping("getkmgvdto")
	public ResponseEntity<response> getkmgvdto(@RequestParam(name="idkmtt",required = true) Integer id) throws Exception{
		if(id==null|| id==0) {
			throw new Exception("Vui lòng cung cấp thông tin khuyến mãi phù hợp");
		}
		KhuyenMaiGioVang t=khuyenMaiGioVangService.getallkmgv(id);
		KhuyenMaiGioVangDTO dto= new KhuyenMaiGioVangDTO();
		dto.setDt(t);
		List<KhuyenMaiMonAn> kmma= khuyenMaiMonAnRepository.getallkmma(id);
		List<KhuyenMaiThucUong> kmtu=khuyenMaiThucUongRepository.getallkmtu(id);
		List<KhuyenMaiComBo> kmcb= khuyenMaiComBoRepository.getallkmcb(id);
		List<KhuyenMaiItem> dsma= new ArrayList<KhuyenMaiItem>();
		List<KhuyenMaiItem> dstu= new ArrayList<KhuyenMaiItem>();
		List<KhuyenMaiItem> dscb= new ArrayList<KhuyenMaiItem>();
		kmma.forEach((data)->{
		KhuyenMaiItem i= new KhuyenMaiItem();
		i.setGia(data.getGiaTriKhuyenMai());
		i.setId(data.getMonAn().getId());
		i.setSoLuong(data.getSoLuongTu());
		dsma.add(i);
		});
		kmtu.forEach((data)->{
			KhuyenMaiItem i= new KhuyenMaiItem();
			i.setGia(data.getGiaTriKhuyenMai());
			i.setId(data.getThucUong().getId());
			i.setSoLuong(data.getSoLuongTu());
		dstu.add(i);
			});
		kmcb.forEach((data)->{
			KhuyenMaiItem i= new KhuyenMaiItem();
			i.setGia(data.getGiaTriKhuyenMai());
			i.setId(data.getComBo().getId());
			i.setSoLuong(data.getSoLuongTu());
		dscb.add(i);
			});
		dto.setDsma(dsma);
		dto.setDstu(dstu);
		dto.setDscb(dscb);
		return new ResponseEntity(new response(HttpStatus.OK,"Ok", dto),HttpStatus.OK);
	}
	
	@GetMapping("/getkmtt")
	public ResponseEntity<response> getkmtt(){
		response r= new response(HttpStatus.OK,"ok", khuyenMaiThongThuongService.getall());
		return new ResponseEntity(r,HttpStatus.OK);
	}
	@GetMapping("/getkmgv")
	public ResponseEntity<response> getkmgv(){
		response r= new response(HttpStatus.OK,"ok", khuyenMaiGioVangService.getall());
		return new ResponseEntity(r,HttpStatus.OK);
	}
	@PostMapping("updatekmtt")
	public ResponseEntity<response> update(@RequestBody @Valid KhuyenMaiDTO dto) throws Exception{
		KhuyenMaiThongThuong test= khuyenMaiThongThuongService.getkhuyenmaiKhuyenMaiThongThuongById(dto.getDt().getId())
				.orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khuyến mãi cần cập nhật"));
		if(dto.getDt().getNgayGioApDung().isAfter(dto.getDt().getNgayGioKetThuc())) {
			throw new Exception("Vui lòng chọn ngày bắt đầu sau ngày áp dụng");
		}
		if(dto.getDscb().size()==0&&dto.getDsma().size()==0&&dto.getDstu().size()==0) {
			throw new Exception("Vui lòng chọn ít nhất 1 sản phẩm khuyến mãi");
		}
		khuyenMaiThongThuongService.saveKhuyenMaiThongThuong(dto.getDt());
		List<KhuyenMaiMonAn> kmma= khuyenMaiMonAnRepository.getallkmma(test.getId());
		List<KhuyenMaiThucUong> kmtu=khuyenMaiThucUongRepository.getallkmtu(test.getId());
		List<KhuyenMaiComBo> kmcb= khuyenMaiComBoRepository.getallkmcb(test.getId());
		dto.getDsma().forEach((data)->{
			KhuyenMaiMonAn k=null;
			for(int i=0;i<kmma.size();i++) {
				if(kmma.get(i).getMonAn().getId()==data.getId()) {
					k=kmma.get(i);
					break;
				}
			}
			if(k==null) {
				MonAn m1= monAnRepository.findById(data.getId()).orElseThrow(()-> new EntityNotFoundException("Không tồn tại món ăn thêm khuyến mãi"));
				
				KhuyenMaiMonAn m= new KhuyenMaiMonAn();
				m.setGiaTriKhuyenMai(data.getGia());
				m.setKhuyenMaiThongThuong(test);
				m.setMonAn(m1);
				m.setSoLuongTu(data.getSoLuong());
				KhuyenMaiMonAnId id= new KhuyenMaiMonAnId();
				id.setKM_ID(test.getId());
				id.setMA_ID(m1.getId());
				m.setId(id);
				khuyenMaiMonAnRepository.save(m);
			}
		});
		kmma.forEach((data)->{
			KhuyenMaiItem k1=null;
			for(int o=0;o<dto.getDsma().size();o++) {
				if(dto.getDsma().get(o).getId()==data.getMonAn().getId()) {
					k1=dto.getDsma().get(o);
					break;
				}
			}
			if(k1==null) {
				khuyenMaiMonAnRepository.delete(data);
			}
			else {
				data.setGiaTriKhuyenMai(k1.getGia());
				data.setSoLuongTu(k1.getSoLuong());
				khuyenMaiMonAnRepository.save(data);
			}
		});
		dto.getDstu().forEach((data)->{
			KhuyenMaiThucUong k=null;
			for(int i=0;i<kmtu.size();i++) {
				if(kmtu.get(i).getThucUong().getId()==data.getId()) {
					k=kmtu.get(i);
					break;
				}
			}
			if(k==null) {
				ThucUong m1= thucUong.findById(data.getId()).orElseThrow(()-> new EntityNotFoundException("Không tồn tại món ăn thêm khuyến mãi"));
				
				KhuyenMaiThucUong m= new KhuyenMaiThucUong();
				m.setGiaTriKhuyenMai(data.getGia());
				m.setKhuyenMaiThongThuong(test);
				m.setThucUong(m1);
				m.setSoLuongTu(data.getId());
				KhuyenMaiThucUongId id= new KhuyenMaiThucUongId();
				id.setKM_ID(test.getId());
				id.setTU_ID(m1.getId());
				m.setId(id);
				khuyenMaiThucUongRepository.save(m);
			}
		});
		kmtu.forEach((data)->{
			KhuyenMaiItem k1=null;
			for(int o=0;o<dto.getDstu().size();o++) {
				if(dto.getDstu().get(o).getId()==data.getThucUong().getId()) {
					k1=dto.getDstu().get(o);
					break;
				}
			}
			if(k1==null) {
				khuyenMaiThucUongRepository.delete(data);
			}
			else {
				data.setGiaTriKhuyenMai(k1.getGia());
				data.setSoLuongTu(k1.getSoLuong());
				khuyenMaiThucUongRepository.save(data);
			}
		});
		dto.getDscb().forEach((data)->{
			KhuyenMaiComBo k=null;
			for(int i=0;i<kmcb.size();i++) {
				if(kmcb.get(i).getComBo().getId()==data.getId()) {
					k=kmcb.get(i);
					break;
				}
			}
			if(k==null) {
				ComBo m1= comBo.findById(data.getId()).orElseThrow(()-> new EntityNotFoundException("Không tồn tại món ăn thêm khuyến mãi"));
				
				KhuyenMaiComBo m= new KhuyenMaiComBo();
				m.setGiaTriKhuyenMai(data.getGia());
				m.setKhuyenMaiThongThuong(test);
				m.setComBo(m1);
				m.setSoLuongTu(data.getId());
				
				KhuyenMaiComBoId id= new KhuyenMaiComBoId();
				id.setKM_ID(test.getId());
				id.setCB_ID(m1.getId());
				m.setId(id);
				khuyenMaiComBoRepository.save(m);
			}
		});
		kmcb.forEach((data)->{
			KhuyenMaiItem k1=null;
			for(int o=00;o<dto.getDscb().size();o++) {
				if(dto.getDscb().get(o).getId()==data.getComBo().getId()) {
					k1=dto.getDscb().get(o);
					break;
				}
			}
			if(k1==null) {
				khuyenMaiComBoRepository.delete(data);
			}
			else {
				data.setGiaTriKhuyenMai(k1.getGia());
				data.setSoLuongTu(k1.getSoLuong());
				khuyenMaiComBoRepository.save(data);
			}
		});
		return new ResponseEntity(new response(HttpStatus.OK," ",null),HttpStatus.OK);
	}
	@GetMapping("/getnangsuat")
	public ResponseEntity<response> getNangSuat(@RequestParam("id") int id) throws Exception{
		System.out.println("Cos yeeu caauf mowis");
		response r= new response(HttpStatus.OK,"ok",khuyenMaiThongThuongService.getNangSuat(id));
		System.out.println("Thành côcng");
		return new ResponseEntity<response>(r,HttpStatus.OK);
	}
}
