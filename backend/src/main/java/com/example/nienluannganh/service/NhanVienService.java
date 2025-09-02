package com.example.nienluannganh.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.nienluannganh.DTO.CaItem;
import com.example.nienluannganh.DTO.CaLamNhanVienDTO;
import com.example.nienluannganh.model.BoPhan;
import com.example.nienluannganh.model.Ca;
import com.example.nienluannganh.model.ChucVu;
import com.example.nienluannganh.model.NhanVien;
import com.example.nienluannganh.model.ThoiGianChucVu;
import com.example.nienluannganh.model.ThoiGianLamCa;
import com.example.nienluannganh.repository.CaRepository;
import com.example.nienluannganh.repository.ChucVuRepository;
import com.example.nienluannganh.repository.NhanVienRepository;
import com.example.nienluannganh.repository.ThoiGianChucVuRepository;
import com.example.nienluannganh.repository.ThoiGianLamCaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class NhanVienService {
	@Autowired
	private NhanVienRepository nhanvienrepo;
	
	@Autowired
	private CaRepository caRepository;

	@Autowired
	private ThoiGianLamCaRepository thoiGianLamCaRepository;

	@Autowired
	private ChucVuRepository chucVuRepository;

	@Autowired
	private ThoiGianChucVuRepository tgg;

	@Autowired
	private BoPhanService bp;

	@Value("${app.nameSource2}")
	private String resource;

	public NhanVien saveNhanVien(NhanVien nv) {
		return nhanvienrepo.save(nv);
	}

	public Optional<NhanVien> getnhanVienById(int id) {
		return nhanvienrepo.findById(id);
	}

	public List<NhanVien> getallnhanvien() {
		return nhanvienrepo.getall();
	}

	public List<NhanVien> tieuchi(String name, int id) {
		if (id == 0) {
			System.out.println("tên nhân viên: " + name);
			return nhanvienrepo.getNhanVienByname(name);
		} else {
			return nhanvienrepo.huhu(id, name);
		}
	}

	@Transactional(rollbackFor = { Throwable.class })
	public void savenhanvien(NhanVien nv, int cv, int bpp, MultipartFile file) throws Exception {
		BoPhan b = bp.getBoPhanById(cv).orElseThrow(() -> new Exception("Không tìm thấy bộ phần phù hợp"));
		nv.setChucVu(b);
		ChucVu c = chucVuRepository.findById(bpp)
				.orElseThrow(() -> new EntityNotFoundException("Không tìm thấy chức vụ phù hợp"));
		if (c.getBoPhan().getId() != b.getId()) {
			throw new Exception("Vui lòng chọn chức vụ tương thích với bộ phận");
		}
		nv.setBoPhan(c);
		nhanvienrepo.save(nv);

		ThoiGianChucVu tg = new ThoiGianChucVu();
		tg.setBoPhan(b);
		tg.setNhanVien(nv);
		tg.setBatDau(LocalDate.now());
		tg.setChucVu(c);
		tgg.save(tg);
		String duoi = "";
		if (file != null && file.getSize() > 0) {
			duoi = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			File f = new File(resource + "nhanvien" + nv.getId() + duoi);
			try {
				file.transferTo(f);
				nv.setAnhGioiThieu("http://localhost:8080/datasource/" + "nhanvien" + nv.getId() + duoi);
				nhanvienrepo.save(nv);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException("Không thể lưu ảnh combo vui lòng thử lại", e);
			}
		}
	}

	@Transactional(rollbackFor = { Throwable.class })
	public void update(NhanVien nv, int cv, int bpp, MultipartFile file) throws Exception {
		BoPhan b = bp.getBoPhanById(cv).orElseThrow(() -> new Exception("Không tìm thấy bộ phần phù hợp"));
		NhanVien n = nhanvienrepo.findById(nv.getId())
				.orElseThrow(() -> new Exception("Không tìm thấy nhân viên cần cập nhật"));
		ChucVu c = chucVuRepository.findById(bpp).orElseThrow(() -> new Exception("Không tìm thấy chức vụ phù hợp"));
		if (c.getBoPhan().getId() != b.getId()) {
			throw new Exception("Vui lòng chọn chức vụ tương thích với từng bộ phận");
		}
		n.setCCCD(nv.getCCCD());
		n.setAnhGioiThieu(nv.getDiaChi());
		n.setEmail(nv.getEmail());
		n.setGioiTinhNam(nv.isGioiTinhNam());
		n.setMatKhau(nv.getMatKhau());
		n.setSDT(nv.getSDT());
		n.setTen(nv.getTen());
		ThoiGianChucVu tg2 = tgg.get(nv.getId(), n.getChucVu().getId());
		n.setChucVu(b);
		n.setBoPhan(c);
		nhanvienrepo.save(n);
		if (tg2 != null && (tg2.getBoPhan().getId() != cv || tg2.getChucVu().getId() != bpp)) {
			System.out.println("đã đi vài đây ");
			tg2.setKetThuc(LocalDate.now());
			ThoiGianChucVu tg = new ThoiGianChucVu();
			tg.setBoPhan(b);
			tg.setChucVu(c);
			tg.setNhanVien(nv);
			tg.setBatDau(LocalDate.now());
			tgg.save(tg);
			if (tg2.getBatDau().equals(LocalDate.now())) {
				tgg.delete(tg2);
			} else {
				tgg.save(tg2);
			}
		}

		String duoi = "";
		if (file != null && file.getSize() > 0) {
			duoi = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			File f = new File(resource + "nhanvien" + nv.getId() + duoi);
			if (f.exists() == true) {
				f.delete();
			}
			try {
				file.transferTo(f);
				n.setAnhGioiThieu("http://localhost:8080/datasource/" + "nhanvien" + nv.getId() + duoi);
				nhanvienrepo.save(n);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException("Không thể lưu ảnh combo vui lòng thử lại", e);
			}
		}
	}

	public List<CaLamNhanVienDTO> getcalamnhanvien(int id, LocalDate bd, LocalDate kt) {
		List<ThoiGianLamCa> dsca = thoiGianLamCaRepository.getcanhanvien(bd, kt, id);
		List<CaLamNhanVienDTO> dsql = new ArrayList<CaLamNhanVienDTO>();
		int diachi=0;
		for(int i=0;i<dsca.size();i++) {
			if(i!=0 && dsca.get(i).getNgaylam().equals(dsca.get(i-1).getNgaylam())) {
				continue;
			}
			CaLamNhanVienDTO c= new CaLamNhanVienDTO();
			List<CaItem> ds= new ArrayList<CaItem>();
			c.setNgayLam(dsca.get(i).getNgaylam());
			for(int u = 0; u < dsca.size(); u++) {
			    if(dsca.get(u).getNgaylam().equals(dsca.get(i).getNgaylam())) {
			        ds.add(new CaItem(dsca.get(u).getCa().getId(), 
			                          dsca.get(u).getCa().getThoiGianBatDau(), 
			                          dsca.get(u).getCa().getThoiGianKetThuc()));
			    }
			}

			c.setDsca(ds);
			dsql.add(c);
		}
		return dsql;
	}
	public void phancong(CaLamNhanVienDTO d) {
		NhanVien nv= nhanvienrepo.findById(d.getIdnv()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy nhân viên cần phân"));
		d.getDsca().forEach((data)->{
			
			ThoiGianLamCa t=new ThoiGianLamCa();
			Ca c=  caRepository.findById(data.getCaid()).orElseThrow(()-> new EntityNotFoundException("Khoong tìm thấy ca"));
//			t= thoiGianLamCaRepository.kiemtraca(d.getNgayLam(),nv.getId(),c.getId());
			
			t.setCa(c);
			t.setNgaylam(d.getNgayLam());
			t.setNhanVien(nv);
			thoiGianLamCaRepository.save(t);
		});
	}
	public void tudong(int id) {
		NhanVien nv= nhanvienrepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy nhân viên"));
		nv.setTudong(1);
		nhanvienrepo.save(nv);
	}
	public int gettudong(int id) {
		NhanVien nv= nhanvienrepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy nhân viên"));
		return nv.getTudong();
	}
	
	public List<Map<Object, Object>> getcalamtrongtuan(
			int id,
			LocalDate bd,
			LocalDate kt
			){
		return nhanvienrepo.getcalamtrongtuan(id, bd, kt);
	}
	public List<NhanVien> g(int cv_id, int bp_id){
		return nhanvienrepo.g(bp_id, cv_id);
	}
	
	public List<NhanVien> g2(int bp_id){
		return nhanvienrepo.g2(bp_id);
	}

	@Transactional
	public void updatehangloat(List<Integer> dsnv, LocalDate batdau, LocalDate ketthuc, int id) {
		List<CaLamNhanVienDTO> ds = getcalamnhanvien(id, LocalDate.now().plusDays(1), ketthuc);
		List<NhanVien> l = new ArrayList<NhanVien>();
		for (int i = 0; i < dsnv.size(); i++) {
			NhanVien nv = nhanvienrepo.findById(dsnv.get(i))
					.orElseThrow(() -> new EntityNotFoundException("Không tồn tại nhân viên cần Thêm ca"));
			l.add(nv);
			thoiGianLamCaRepository.xoacalam(nv.getId(), batdau, ketthuc);
		}
		for (int i = 0; i < ds.size(); i++) {
			for (int u = 0; u < ds.get(i).getDsca().size(); u++) {
				Ca c = caRepository.getbyId(ds.get(i).getDsca().get(u).getCaid());
				for (int y = 0; y < l.size(); y++) {
					ThoiGianLamCa t = new ThoiGianLamCa();
					t.setCa(c);
					t.setNgaylam(ds.get(i).getNgayLam());
					System.out.println("nhân viên: "+l.get(y));
					t.setNhanVien(l.get(y));
					thoiGianLamCaRepository.save(t);
				}
			}
		}
	}
	@Transactional
	public void daoca(List<Integer> dsnv, LocalDate batdau, LocalDate ketthuc, int id) {
		List<CaLamNhanVienDTO> ds = getcalamnhanvien(id, LocalDate.now().plusDays(1), ketthuc);
		List<NhanVien> l=nhanvienrepo.getnotin(dsnv);
		
		for (int i = 0; i < l.size(); i++) {
			thoiGianLamCaRepository.xoacalam(l.get(i).getId(), batdau, ketthuc);
		}
		for (int i = 0; i < ds.size(); i++) {
			List<Ca> cc= new ArrayList<Ca>();
			if(ds.get(i).getDsca().size()==0) {
				cc=caRepository.findAll();
			}
			else {
				List<Integer> caidList = ds.get(i).getDsca().stream()
                        .map(ca -> ca.getCaid()) 
                        .collect(Collectors.toList()); 
					cc=caRepository.getnotin(caidList);
			}
			for (int u = 0; u < cc.size(); u++) {
				for (int y = 0; y < l.size(); y++) {
					ThoiGianLamCa t = new ThoiGianLamCa();
					t.setCa(cc.get(u));
					t.setNgaylam(ds.get(i).getNgayLam());
					System.out.println("nhân viên: "+l.get(y));
					t.setNhanVien(l.get(y));
					thoiGianLamCaRepository.save(t);
				}
			}
		}
		
		
	}
}
