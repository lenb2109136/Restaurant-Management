package com.example.nienluannganh.model;

import com.example.nienluannganh.model.embededid.KhuyenMaiGioVangMonAnId;
import com.example.nienluannganh.model.embededid.KhuyenMaiMonAnId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "KHUYENMAIGIOVANGMONAN")
public class KhuyenMaiGioVangMonAn {
	@EmbeddedId
	private KhuyenMaiGioVangMonAnId id;
	
	@ManyToOne
	@MapsId("KM_ID")
	@JoinColumn(name = "KM_ID")
	private KhuyenMaiGioVang khuyenMaiGioVang;
	
	@ManyToOne
	@MapsId("MA_ID")
	@JoinColumn(name = "MA_ID")
	private MonAn monAn;
	
	@Column(name = "KM_GIATRIKHUYENMAI")
	private float giaTriKhuyenMai;
	
	@Column(name = "KM_SOLUONGTU")
	private int soLuongTu;

	public KhuyenMaiGioVangMonAnId getId() {
		return id;
	}

	public void setId(KhuyenMaiGioVangMonAnId id) {
		this.id = id;
	}

	public KhuyenMaiGioVang getKhuyenMaiThongThuong() {
		return khuyenMaiGioVang;
	}

	public void setKhuyenMaiThongThuong(KhuyenMaiGioVang khuyenMaiThongThuong) {
		this.khuyenMaiGioVang = khuyenMaiThongThuong;
	}

	public MonAn getMonAn() {
		return monAn;
	}

	public void setMonAn(MonAn monAn) {
		this.monAn = monAn;
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
