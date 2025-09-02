package com.example.nienluannganh.model;

import com.example.nienluannganh.model.embededid.KhuyenMaiComBoId;
import com.example.nienluannganh.model.embededid.KhuyenMaiGioVangComBoId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name="KHUYENMAIGIOVANGCOMBO")
public class KhuyenMaiGioVangComBo {
	@EmbeddedId
	private KhuyenMaiGioVangComBoId id;
	
	@ManyToOne
	@MapsId("KM_ID")
	@JoinColumn(name = "KM_ID")
	private KhuyenMaiGioVang khuyenMaiGioVang;
	
	@ManyToOne
	@MapsId("CB_ID")
	@JoinColumn(name = "CB_ID")
	private ComBo comBo;
	
	@Column(name = "KM_GIATRIKHUYENMAI")
	private float giaTriKhuyenMai;
	
	@Column(name = "KM_SOLUONGTU")
	private int soLuongTu;

	public KhuyenMaiGioVangComBoId getId() {
		return id;
	}

	public void setId(KhuyenMaiGioVangComBoId id) {
		this.id = id;
	}

	public KhuyenMaiGioVang getKhuyenMaiThongThuong() {
		return khuyenMaiGioVang;
	}

	public void setKhuyenMaiThongThuong(KhuyenMaiGioVang khuyenMaiThongThuong) {
		this.khuyenMaiGioVang = khuyenMaiThongThuong;
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
