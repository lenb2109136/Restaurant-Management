package com.example.nienluannganh.model;

import com.example.nienluannganh.model.embededid.CoSoLuongThucUongId;
import com.example.nienluannganh.model.embededid.KhuyenMaiThucUongId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "KHUYENMAITHUCUONG")
public class KhuyenMaiThucUong {
	@EmbeddedId
	private KhuyenMaiThucUongId id;
	
	@ManyToOne
	@MapsId("KM_ID")
	@JoinColumn(name = "KM_ID")
	@JsonIgnore
	private KhuyenMaiThongThuong khuyenMaiThongThuong;
	
	@ManyToOne
	@MapsId("TU_ID")
	@JoinColumn(name = "TU_ID")
	private ThucUong thucUong;
	
	@Column(name = "KM_GIATRIKHUYENMAI")
	private float giaTriKhuyenMai;
	
	@Column(name = "KM_SOLUONGTU")
	private int soLuongTu;

	public KhuyenMaiThucUongId getId() {
		return id;
	}

	public void setId(KhuyenMaiThucUongId id) {
		this.id = id;
	}

	public KhuyenMaiThongThuong getKhuyenMaiThongThuong() {
		return khuyenMaiThongThuong;
	}

	public void setKhuyenMaiThongThuong(KhuyenMaiThongThuong khuyenMaiThongThuong) {
		this.khuyenMaiThongThuong = khuyenMaiThongThuong;
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
