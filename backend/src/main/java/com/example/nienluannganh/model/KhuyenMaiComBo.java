package com.example.nienluannganh.model;

import com.example.nienluannganh.model.embededid.KhuyenMaiComBoId;
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
@Table(name = "KHUYENMAICOMBO")
public class KhuyenMaiComBo {
	@EmbeddedId
	private KhuyenMaiComBoId id;
	
	@ManyToOne
	@JsonIgnore
	@MapsId("KM_ID")
	@JoinColumn(name = "KM_ID")
	private KhuyenMaiThongThuong khuyenMaiThongThuong;
	
	@ManyToOne
	@MapsId("CB_ID")
	@JoinColumn(name = "CB_ID")
	private ComBo comBo;
	
	@Column(name = "KM_GIATRIKHUYENMAI")
	private float giaTriKhuyenMai;
	
	@Column(name = "KM_SOLUONGTU")
	private int soLuongTu;

	public KhuyenMaiComBoId getId() {
		return id;
	}

	public void setId(KhuyenMaiComBoId id) {
		this.id = id;
	}

	public KhuyenMaiThongThuong getKhuyenMaiThongThuong() {
		return khuyenMaiThongThuong;
	}

	public void setKhuyenMaiThongThuong(KhuyenMaiThongThuong khuyenMaiThongThuong) {
		this.khuyenMaiThongThuong = khuyenMaiThongThuong;
	}

	public ComBo getComBo() {
		return comBo;
	}

	public void setComBo(ComBo comBo) {
		this.comBo = comBo;
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
