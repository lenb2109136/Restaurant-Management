package com.example.nienluannganh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.nienluannganh.model.KhuyenMaiGioVangMonAn;
import com.example.nienluannganh.model.KhuyenMaiGioVangThucUong;
import com.example.nienluannganh.model.KhuyenMaiMonAn;
import com.example.nienluannganh.model.KhuyenMaiThucUong;
import com.example.nienluannganh.model.MonAn;
import com.example.nienluannganh.model.ThucUong;
import com.example.nienluannganh.model.embededid.KhuyenMaiGioVangMonAnId;
import com.example.nienluannganh.model.embededid.KhuyenMaiGioVangThucUongId;
import com.example.nienluannganh.model.embededid.KhuyenMaiMonAnId;
import com.example.nienluannganh.model.embededid.KhuyenMaiThucUongId;
import com.example.nienluannganh.objectcontroller.responseentity.response;
import com.example.nienluannganh.repository.KhuyenMaiGioVangThucUongRepository;
import com.example.nienluannganh.repository.KhuyenMaiThucUongRepository;
import com.example.nienluannganh.service.ThucUongService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/thucuong")
public class ThucUongController {
	@Autowired
	private ThucUongService tu;
	
	@Autowired
	private KhuyenMaiThucUongRepository khuyenMaiThucUongRepository;
	
	@Autowired
	private KhuyenMaiGioVangThucUongRepository khuyenMaiGioVangThucUongRepository;

	@GetMapping("/thucuongbyloai")
	public ResponseEntity<MonAn> getMonAnByLoai(@RequestParam("idloai") int idloai){
		response k= new response(HttpStatus.OK,"ok",tu.getThucUongByLoai(idloai));
		return new ResponseEntity(k,HttpStatus.OK);
	}
	
	
	@GetMapping("/ngung")
	public ResponseEntity<response> ngungSuDung(@RequestParam(value="idloai",required = false) Integer idloai) throws Exception{
		if(idloai==null||idloai==0) {
			throw new Exception("Vui lòng chọn đúng loại thức uống");
		}
		tu.ngung(idloai);
		response r= new response(HttpStatus.OK,"Đã ngưng sử dụng thức uống",null);
		return new ResponseEntity(r,HttpStatus.OK);
	}
	
	@PostMapping("/savethucuong")
	public ResponseEntity<response> savetu(
		    @NotNull(message = "Bạn thiếu thông tin thức uống")	@ModelAttribute ThucUong t,
			@RequestParam(value = "idloai",required = false) Integer idloai,
			@RequestParam(value = "iddvt",required = false) Integer iddvt,
			@RequestParam("anh") MultipartFile file,
			@RequestParam("giaa") String giaa
			) throws Exception
	{
			if(idloai==null) {
				throw new Exception("Vui lòng chọn loại thức uống");
			}
			if(iddvt==null) {
				throw new Exception("Vui lòng chọn đơn vị tính phù hợp");
			}
			tu.saveThucUong(t, idloai, iddvt, file,giaa);
			
			response r= new response(HttpStatus.OK,"ok",null);
			return new ResponseEntity(r,HttpStatus.OK);
	}

	@PostMapping("/updatethucuong")
	public ResponseEntity<response> update(
			@RequestParam(value="anh",required = false) MultipartFile file,
			@RequestParam(value = "iddvt", required = false) Integer iddvt,
			@RequestParam(value = "idloai", required = false) Integer idloai,
			@Valid @ModelAttribute ThucUong tu,
			@RequestParam ("giaa") String giaa
			) throws Exception {
		if(iddvt==null||iddvt==0) {
			throw new Exception("Vui lòng chọn đơn vị tính đã được cung cấp");
		}
		if(idloai==null||idloai==0) {
			throw new Exception("Vui lòng chọn loại thức uống đã được cung cấp");
		}
		this.tu.capNhatThucUong(file, idloai, iddvt,tu,giaa);
		
		response r= new response(HttpStatus.OK,"Cập nhật thông tin thành công",null);
		return new ResponseEntity(r,HttpStatus.OK);
		
	}
	@GetMapping("/getall")
	public ResponseEntity<response> getall(){
		response r= new response(HttpStatus.OK,"ok",tu.getall());
		return new ResponseEntity(r,HttpStatus.OK);
	}
	@GetMapping("/getkhuyenmai")
	public ResponseEntity<response> getkm(@RequestParam("id") int id){
		List<Map<Object, Object>> kmtt=khuyenMaiThucUongRepository.getkmma(id);
		List<Map<Object, Object>> kmgv=khuyenMaiGioVangThucUongRepository.getkmma(id);
		float a= khuyenMaiThucUongRepository.tongkm(id);
		Map<Object, Object> map=new HashMap<>();;
		map.put("kmgv",kmgv);
		map.put("kmtt",kmtt);
		map.put("tong", a);
		return new ResponseEntity<response>(new response(HttpStatus.OK, "ok", map),HttpStatus.OK);
	}
	
	@GetMapping("ngungkmtt")
	public ResponseEntity<response> ngungkmtt(@RequestParam("idma") int idma,@RequestParam("idkm") int idkm){
		KhuyenMaiThucUongId id= new KhuyenMaiThucUongId();
		id.setKM_ID(idkm);
		id.setTU_ID(idma);
		KhuyenMaiThucUong k=	khuyenMaiThucUongRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Món anư không còn áp dụng khuyến mãi"));
		khuyenMaiThucUongRepository.delete(k);
		return new ResponseEntity<response>(new response(HttpStatus.OK, "Đã ngưng sử dụng khuyến mãi",null),HttpStatus.OK);
		
	}
	@GetMapping("ngungkmgv")
	public ResponseEntity<response> ngungkmgv(@RequestParam("idma") int idma,@RequestParam("idkm") int idkm){
		KhuyenMaiGioVangThucUongId id= new KhuyenMaiGioVangThucUongId();
		id.setKM_ID(idkm);
		id.setTU_ID(idma);
		KhuyenMaiGioVangThucUong k=	khuyenMaiGioVangThucUongRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Món anư không còn áp dụng khuyến mãi"));
		khuyenMaiGioVangThucUongRepository.delete(k);
		return new ResponseEntity<response>(new response(HttpStatus.OK, "Đã ngưng sử dụng khuyến mãi",null),HttpStatus.OK);
		
	}
	
}
