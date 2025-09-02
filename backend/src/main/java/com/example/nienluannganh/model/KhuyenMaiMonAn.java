package com.example.nienluannganh.model;

import com.example.nienluannganh.model.embededid.KhuyenMaiComBoId;
import com.example.nienluannganh.model.embededid.KhuyenMaiMonAnId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "KHUYENMAIMONAN")
public class KhuyenMaiMonAn {
	@EmbeddedId
	private KhuyenMaiMonAnId id;
	
	@ManyToOne
	@JsonIgnore
	@MapsId("KM_ID")
	@JoinColumn(name = "KM_ID")
	private KhuyenMaiThongThuong khuyenMaiThongThuong;
	
	@ManyToOne
	@MapsId("MA_ID")
	@JoinColumn(name = "MA_ID")
	private MonAn monAn;
	
	@Column(name = "KM_GIATRIKHUYENMAI")
	private float giaTriKhuyenMai;
	
	@Column(name = "KM_SOLUONGTU")
	private int soLuongTu;

	public KhuyenMaiMonAnId getId() {
		return id;
	}

	public void setId(KhuyenMaiMonAnId id) {
		this.id = id;
	}

	public KhuyenMaiThongThuong getKhuyenMaiThongThuong() {
		return khuyenMaiThongThuong;
	}

	public void setKhuyenMaiThongThuong(KhuyenMaiThongThuong khuyenMaiThongThuong) {
		this.khuyenMaiThongThuong = khuyenMaiThongThuong;
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
