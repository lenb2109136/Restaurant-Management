package com.example.nienluannganh.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "KHUYENMAITHONGTHUONG")
public class KhuyenMaiThongThuong extends KhuyenMai {
	
	@OneToMany(mappedBy = "khuyenMaiThongThuong")
	private List<KhuyenMaiMonAn> khuyenMaiMonAn;
	@OneToMany(mappedBy = "khuyenMaiThongThuong")
	private List<KhuyenMaiThucUong> khuyenMaiThucUong;
	@OneToMany(mappedBy = "khuyenMaiThongThuong")
	private List<KhuyenMaiComBo> khuyenMaiComBo;
	public List<KhuyenMaiMonAn> getKhuyenMaiMonAn() {
		return khuyenMaiMonAn;
	}
	public void setKhuyenMaiMonAn(List<KhuyenMaiMonAn> khuyenMaiMonAn) {
		this.khuyenMaiMonAn = khuyenMaiMonAn;
	}
	public List<KhuyenMaiThucUong> getKhuyenMaiThucUong() {
		return khuyenMaiThucUong;
	}
	public void setKhuyenMaiThucUong(List<KhuyenMaiThucUong> khuyenMaiThucUong) {
		this.khuyenMaiThucUong = khuyenMaiThucUong;
	}
	public List<KhuyenMaiComBo> getKhuyenMaiComBo() {
		return khuyenMaiComBo;
	}
	public void setKhuyenMaiComBo(List<KhuyenMaiComBo> khuyenMaiComBo) {
		this.khuyenMaiComBo = khuyenMaiComBo;
	}
	
	
}
