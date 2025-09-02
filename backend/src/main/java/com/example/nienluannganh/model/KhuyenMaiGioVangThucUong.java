package com.example.nienluannganh.model;

import com.example.nienluannganh.model.embededid.KhuyenMaiGioVangThucUongId;
import com.example.nienluannganh.model.embededid.KhuyenMaiThucUongId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "KHUYENMAIGIOVANGTHUCUONG")
public class KhuyenMaiGioVangThucUong {
	@EmbeddedId
	private KhuyenMaiGioVangThucUongId id;
	
	@ManyToOne
	@MapsId("KM_ID")
	@JoinColumn(name = "KM_ID")
	private KhuyenMaiGioVang khuyenMaiGioVang;
	
	@ManyToOne
	@MapsId("TU_ID")
	@JoinColumn(name = "TU_ID")
	private ThucUong thucUong;
	
	@Column(name = "KM_GIATRIKHUYENMAI")
	private float giaTriKhuyenMai;
	
	@Column(name = "KM_SOLUONGTU")
	private int soLuongTu;

	public KhuyenMaiGioVangThucUongId getId() {
		return id;
	}

	public void setId(KhuyenMaiGioVangThucUongId id) {
		this.id = id;
	}

	public KhuyenMaiGioVang getKhuyenMaiThongThuong() {
		return khuyenMaiGioVang;
	}

	public void setKhuyenMaiThongThuong(KhuyenMaiGioVang khuyenMaiThongThuong) {
		this.khuyenMaiGioVang = khuyenMaiThongThuong;
	}

	public ThucUong getThucUong() {
		return thucUong;
	}

	public void setThucUong(ThucUong thucUong) {
		this.thucUong = thucUong;
	}

	public float getGiaTriKhuyenMai() {
		return giaTriKhuyenMai;
	}

	public void setGiaTriKhuyenMai(float giaTriKhuyenMai) {
		this.giaTriKhuyenMai = giaTriKhuyenMai;
	}

	public int getSoLuongTu() {
		return soLuongTu;
	}

	public void setSoLuongTu(int soLuongTu) {
		this.soLuongTu = soLuongTu;
	}
}
