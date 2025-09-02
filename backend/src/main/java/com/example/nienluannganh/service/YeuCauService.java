package com.example.nienluannganh.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.DTO.HoaDonDTO;
import com.example.nienluannganh.DTO.ItemKhuuyenMai;
import com.example.nienluannganh.DTO.banan;
import com.example.nienluannganh.RAMWEBSOCKET.RamWebsocket;
import com.example.nienluannganh.model.Ban;
import com.example.nienluannganh.model.ChiTietYeuCau;
import com.example.nienluannganh.model.ChiTietYeuCauBan;
import com.example.nienluannganh.model.CoSoLuongComBo;
import com.example.nienluannganh.model.CoSoLuongMonAn;
import com.example.nienluannganh.model.CoSoLuongThucUong;
import com.example.nienluannganh.model.ComBo;
import com.example.nienluannganh.model.MonAn;
import com.example.nienluannganh.model.NhanVien;
import com.example.nienluannganh.model.ThucUong;
import com.example.nienluannganh.model.YeuCau;
import com.example.nienluannganh.model.embededid.ChiTietYeuCauBanId;
import com.example.nienluannganh.model.embededid.CoSoLuongComBoId;
import com.example.nienluannganh.model.embededid.CoSoLuongMonAnId;
import com.example.nienluannganh.model.embededid.CoSoLuongThucUongId;
import com.example.nienluannganh.repository.BanRepository;
import com.example.nienluannganh.repository.ChiTietYeuCauBanRepository;
import com.example.nienluannganh.repository.ChiTietYeuCauRepository;
import com.example.nienluannganh.repository.CoSoLuongComBoRepository;
import com.example.nienluannganh.repository.CoSoLuongMonAnRepository;
import com.example.nienluannganh.repository.CoSoLuongThucUongRepository;
import com.example.nienluannganh.repository.ComBoRepository;
import com.example.nienluannganh.repository.KhuyenMaiComBoRepository;
import com.example.nienluannganh.repository.KhuyenMaiGioVangComboReposito;
import com.example.nienluannganh.repository.KhuyenMaiGioVangMonAnRepositr;
import com.example.nienluannganh.repository.KhuyenMaiGioVangThucUongRepository;
import com.example.nienluannganh.repository.KhuyenMaiMonAnRepository;
import com.example.nienluannganh.repository.KhuyenMaiThucUongRepository;
import com.example.nienluannganh.repository.MonAnRepository;
import com.example.nienluannganh.repository.NhanVienRepository;
import com.example.nienluannganh.repository.ThucUongRepository;
import com.example.nienluannganh.repository.YeuCauRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class YeuCauService {
	@Autowired
	private YeuCauRepository yeuCauRepository;
	
	@Autowired
	MonAnRepository monAnRepository;
	
	
	@Autowired
	ThucUongRepository thucUongRepository;
	
	@Autowired
	ComBoRepository comBoRepository;
	
	@Autowired
	ChiTietYeuCauRepository chiTietYeuCauRepository;
	
	@Autowired
	CoSoLuongMonAnRepository coSoLuongMonAnRepository;
	
	@Autowired
	CoSoLuongComBoRepository coSoLuongComBoRepository;
	
	@Autowired
	CoSoLuongThucUongRepository coSoLuongThucUongRepository;
	
	@Autowired
	BanRepository banRepository;
	
	@Autowired
	private ChiTietYeuCauBanRepository chiTietYeuCauBanRepository;
	
	
	@Autowired
	private KhuyenMaiMonAnRepository khuyenMaiMonAnRepository;
	
	@Autowired
	private KhuyenMaiGioVangMonAnRepositr khuyenMaiGioVangMonAnRepositr;
	
	@Autowired
	private NhanVienRepository nhanVienRepository;
	
	@Autowired
	private KhuyenMaiThucUongRepository khuyenMaiThucUongRepository;
	
	@Autowired
	private KhuyenMaiComBoRepository khuyenMaiComBoRepository;
	
	@Autowired
	private KhuyenMaiGioVangThucUongRepository khuyenMaiGioVangThucUongRepository;
	
	@Autowired
	private KhuyenMaiGioVangComboReposito khuyenMaiGioVangComboReposito;
	
	
	public YeuCau saveYeuCau(YeuCau y) {
		return yeuCauRepository.save(y);
	}
	public Optional<YeuCau> getYeuCauById(int id) {
		return yeuCauRepository.findById(id);
	}
	
	
	@org.springframework.transaction.annotation.Transactional
	public void update(RamWebsocket l) {
		YeuCau y = yeuCauRepository.findById(l.getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy yêu cầu cần chỉnh sửa")) ;
		y.setThoiGianYeuCau(LocalDateTime.now());
		y.setTrangThaiYeuCau(0);
		chiTietYeuCauRepository.deleteByYcId(y.getId());
		l.getListOrder().forEach(data->{
		});
		l.getListOrder().forEach(d1->{
			ChiTietYeuCau c= new ChiTietYeuCau();
			c.setYeuCau(y);
			chiTietYeuCauRepository.save(c);
			d1.setId(c.getId());
			d1.getBan().forEach(d2->{
				ChiTietYeuCauBan c1= new ChiTietYeuCauBan();
				ChiTietYeuCauBanId c2= new ChiTietYeuCauBanId();
				Ban b1=banRepository.findById(d2.getBanId()).orElseThrow(()-> new EntityNotFoundException("Thông tin không hợp lệ"));
				b1.setTrong(false);
				banRepository.save(b1);
				c1.setBan(b1);
				c1.setChiTietYeuCau(c);
				c2.setBAN_STT(b1.getStt());
				c2.setCTYC_ID(c.getId());
				c1.setId(c2);
				chiTietYeuCauBanRepository.save(c1);
				
			});
			d1.getDsma().forEach(d3->{
				MonAn m= monAnRepository.findById(d3.getId()).orElseThrow(()-> new EntityNotFoundException("Thông tin món ăn không hợp lệ"));
				CoSoLuongMonAn c2= new CoSoLuongMonAn();
				c2.setChiTietYeuCau(c);
				c2.setMonAn(m);
				c2.setSoLuong(d3.getSoLuong());
				CoSoLuongMonAnId id= new CoSoLuongMonAnId();
				id.setCTYC_ID(c.getId());
				id.setMA_ID(m.getId());
				c2.setId(id);
				coSoLuongMonAnRepository.save(c2);
			});
			d1.getDstu().forEach(d4->{
				ThucUong m= thucUongRepository.findById(d4.getId()).orElseThrow(()-> new EntityNotFoundException("Thông tin thức uống không hợp lệ"));
				CoSoLuongThucUong c2= new CoSoLuongThucUong();
				c2.setChiTietYeuCau(c);
				c2.setThucUong(m);
				c2.setSoLuong(d4.getSoLuong());
				CoSoLuongThucUongId id= new CoSoLuongThucUongId();
				id.setCTYC_ID(c.getId());
				id.setMA_ID(m.getId());
				c2.setId(id);
				coSoLuongThucUongRepository.save(c2);
			});
			d1.getDscb().forEach(d5->{
				ComBo m= comBoRepository.findById(d5.getId()).orElseThrow(()-> new EntityNotFoundException("Thông tin combo không hợp lệ"));
				CoSoLuongComBo c2= new CoSoLuongComBo();
				c2.setChiTietYeuCau(c);
				c2.setComBo(m);
				c2.setSoLuong(d5.getSoLuong());
				CoSoLuongComBoId id= new CoSoLuongComBoId();
				id.setCTYC_ID(c.getId());
				id.setCB_ID(m.getId());
				c2.setId(id);
				coSoLuongComBoRepository.save(c2);
			});
		});
	}
	@org.springframework.transaction.annotation.Transactional
	public void save(RamWebsocket l) {
		YeuCau y = new YeuCau();
		y.setThoiGianYeuCau(LocalDateTime.now());
		y.setTrangThaiYeuCau(0);
		yeuCauRepository.save(y);
		// set
		l.setId(y.getId());;
		l.getListOrder().forEach(d1->{
			ChiTietYeuCau c= new ChiTietYeuCau();
			c.setYeuCau(y);
			chiTietYeuCauRepository.save(c);
			//set
			d1.setId(c.getId());
			d1.getBan().forEach(d2->{
				ChiTietYeuCauBan c1= new ChiTietYeuCauBan();
				ChiTietYeuCauBanId c2= new ChiTietYeuCauBanId();
				Ban b1=banRepository.findById(d2.getBanId()).orElseThrow(()-> new EntityNotFoundException("Thông tin không hợp lệ"));
				b1.setTrong(false);
				banRepository.save(b1);
				c1.setBan(b1);
				c1.setChiTietYeuCau(c);
				c2.setBAN_STT(b1.getStt());
				c2.setCTYC_ID(c.getId());
				c1.setId(c2);
				chiTietYeuCauBanRepository.save(c1);
				
			});
			d1.getDsma().forEach(d3->{
				MonAn m= monAnRepository.findById(d3.getId()).orElseThrow(()-> new EntityNotFoundException("Thông tin món ăn không hợp lệ"));
				CoSoLuongMonAn c2= new CoSoLuongMonAn();
				c2.setChiTietYeuCau(c);
				c2.setMonAn(m);
				c2.setSoLuong(d3.getSoLuong());
				CoSoLuongMonAnId id= new CoSoLuongMonAnId();
				id.setCTYC_ID(c.getId());
				id.setMA_ID(m.getId());
				c2.setId(id);
				coSoLuongMonAnRepository.save(c2);
			});
			d1.getDstu().forEach(d4->{
				ThucUong m= thucUongRepository.findById(d4.getId()).orElseThrow(()-> new EntityNotFoundException("Thông tin thức uống không hợp lệ"));
				CoSoLuongThucUong c2= new CoSoLuongThucUong();
				c2.setChiTietYeuCau(c);
				c2.setThucUong(m);
				c2.setSoLuong(d4.getSoLuong());
				CoSoLuongThucUongId id= new CoSoLuongThucUongId();
				id.setCTYC_ID(c.getId());
				id.setMA_ID(m.getId());
				c2.setId(id);
				coSoLuongThucUongRepository.save(c2);
			});
			d1.getDscb().forEach(d5->{
				ComBo m= comBoRepository.findById(d5.getId()).orElseThrow(()-> new EntityNotFoundException("Thông tin combo không hợp lệ"));
				CoSoLuongComBo c2= new CoSoLuongComBo();
				c2.setChiTietYeuCau(c);
				c2.setComBo(m);
				c2.setSoLuong(d5.getSoLuong());
				CoSoLuongComBoId id= new CoSoLuongComBoId();
				id.setCTYC_ID(c.getId());
				id.setCB_ID(m.getId());
				c2.setId(id);
				coSoLuongComBoRepository.save(c2);
			});
		});
	}
	
	@Transactional
	public void capNhat(RamWebsocket l) {
			YeuCau y = yeuCauRepository.findById(l.getId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy yêu cầu cần tìm"));
			yeuCauRepository.delete(y);
			save(l);
	}
	@Transactional
	public void huyDon(int yeuCauId) {
		banRepository.setBanTrong(yeuCauId);
		YeuCau y= yeuCauRepository.findById(yeuCauId).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy yêu cầu"));
		yeuCauRepository.delete(y);
	}
	public HoaDonDTO getHoaDon(int id, int idnhanvien) {
		System.out.println("đã vào");
		YeuCau y= yeuCauRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy hóa đơn cần thanh toán"));
		
		NhanVien n= nhanVienRepository.findById(idnhanvien).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy nhân viên thanh toán"));
		HoaDonDTO h= new HoaDonDTO();
		y.setYC_NGAYLAP(LocalDateTime.now());
		y.setNhanVien(n);
		y.setTrangThaiYeuCau(1);
		yeuCauRepository.save(y);
		h.setNhanVien(n);
		h.setNgaylap(LocalDateTime.now());
		AtomicReference<Float> tongGia = new AtomicReference<>(0.0f);
		List<banan> bbb=new ArrayList<banan>();
		List<ChiTietYeuCau> dsct=chiTietYeuCauRepository.getctycbyid(y.getId());
		for(int i=0;i<dsct.size();i++) {
				banan b= new banan();
				b.setBan(dsct.get(i).getChiTietYeuCauBans()
					    .stream()
					    .map(ChiTietYeuCauBan::getBan)
					    .collect(Collectors.toList()));
				b.getBan().forEach(d->{
					d.setTrong(true);
					banRepository.save(d);
					System.out.println("có vào trong setban");
				});

				b.setDsma(dsct.get(i).getCoSoLuongMonAns().stream().map((d)->{
					ItemKhuuyenMai li= new ItemKhuuyenMai();
					
					li.setId(d.getMonAn().getId());
					System.out.println(d.getMonAn().getId());
					System.out.println(d.getSoLuong());
					Float gg= monAnRepository.getGia(d.getMonAn().getId(),d.getSoLuong());
					li.setDongia((gg != null ? gg : d.getMonAn().getGia()));
					List<Map<Object, Object>> kmtt=khuyenMaiMonAnRepository.getkmma(d.getMonAn().getId());
					System.out.println("danh sacsh khuyen mai: "+kmtt.size());
					List<Map<Object, Object>> kmgv=khuyenMaiGioVangMonAnRepositr.getkmma(d.getMonAn().getId());
					System.out.println("khueyens mãi giừo vagf");
					System.out.println("danh sacsh khuyen mai vàng: "+kmgv.size());
					Float u=0f;
					for(int k=0;k<kmtt.size();k++) {
						int sl=(Integer)kmtt.get(k).get("KM_SOLUONGTU");
						if(d.getSoLuong()>=sl) {
							System.out.println("cộng tjwipnfh");
							u=u+(float)kmtt.get(k).get("KM_GIATRIKHUYENMAI");
							System.out.println(u);
						}
					}
					for(int k=0;k<kmgv.size();k++) {
						int sl=(Integer)kmtt.get(k).get("KM_SOLUONGTU");
						if(d.getSoLuong()>sl) {
							System.out.println("đã vào đây cộng vàng");
							u=u+(float)kmtt.get(k).get("KM_GIATRIKHUYENMAI");
							System.out.println(u);
						}
					}
					li.setSoluong(d.getSoLuong());
					li.setTonggiatrikhuyenmai(u);
					float gia = (gg != null ? gg : d.getMonAn().getGia()) * d.getSoLuong() * (1 - u / 100.0f);
					li.setTen(d.getMonAn().getTen());
					li.setTong(gia);
					tongGia.updateAndGet(v -> v + gia);
					return li;
				}).collect(Collectors.toList()));
				b.setDstu(dsct.get(i).getCoSoLuongThucUongs().stream().map((d)->{
					ItemKhuuyenMai li= new ItemKhuuyenMai();
					li.setDongia(d.getThucUong().getGia());
					li.setId(d.getThucUong().getId());
					List<Map<Object, Object>> kmtt=khuyenMaiThucUongRepository.getkmma(d.getThucUong().getId());
					List<Map<Object, Object>> kmgv=khuyenMaiGioVangThucUongRepository.getkmma(d.getThucUong().getId());
					Float u=0f;
					for(int k=0;k<kmtt.size();k++) {
						int sl=(Integer)kmtt.get(k).get("KM_SOLUONGTU");
						if(d.getSoLuong()>sl) {
							u=u+(float)kmtt.get(k).get("KM_GIATRIKHUYENMAI");
						}
					}
					for(int k=0;k<kmgv.size();k++) {
						int sl=(Integer)kmtt.get(k).get("KM_SOLUONGTU");
						if(d.getSoLuong()>sl) {
							u=u+(float)kmtt.get(k).get("KM_GIATRIKHUYENMAI");
						}
					}
					li.setSoluong(d.getSoLuong());
					li.setTonggiatrikhuyenmai(u);
					float gia=d.getThucUong().getGia() * d.getSoLuong() * (1 - u / 100.0f);
					li.setTen(d.getThucUong().getTen());
					li.setTong(gia);
					tongGia.updateAndGet(v -> v + gia);
					return li;
				}).collect(Collectors.toList()));
				b.setDscb(dsct.get(i).getCoSoLuongComBos().stream().map((d)->{
					ItemKhuuyenMai li= new ItemKhuuyenMai();
					li.setDongia(d.getComBo().getGia());
					li.setId(d.getComBo().getId());
					List<Map<Object, Object>> kmtt=khuyenMaiComBoRepository.getkmma(d.getComBo().getId());
					List<Map<Object, Object>> kmgv=khuyenMaiGioVangComboReposito.getkmma(d.getComBo().getId());
					Float u=0f;
					for(int k=0;k<kmtt.size();k++) {
						int sl=(Integer)kmtt.get(k).get("KM_SOLUONGTU");
						if(d.getSoLuong()>sl) {
							u=u+(float)kmtt.get(k).get("KM_GIATRIKHUYENMAI");
						}
					}
					for(int k=0;k<kmgv.size();k++) {
						int sl=(Integer)kmtt.get(k).get("KM_SOLUONGTU");
						if(d.getSoLuong()>sl) {
							u=u+(float)kmtt.get(k).get("KM_GIATRIKHUYENMAI");
						}
					}
					li.setSoluong(d.getSoLuong());
					li.setTonggiatrikhuyenmai(u);
					float gia=d.getComBo().getGia() * d.getSoLuong() * (1 - u / 100.0f);
					li.setTen(d.getComBo().getTen());
					li.setTong(gia);
					tongGia.updateAndGet(v -> v + gia);
					return li;
				}).collect(Collectors.toList()));
				bbb.add(b);
		}
		h.setTongtien(tongGia.get());
		h.setBanan(bbb);
		return h;
	}
}
