package com.example.nienluannganh.model;
import com.example.nienluannganh.model.embededid.CoSoLuongThucUongId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity 
@Table(name = "COSOLUONGTHUCUONG")
public class CoSoLuongThucUong {
	@EmbeddedId
	private CoSoLuongThucUongId id;
	
	@JsonIgnore
	@ManyToOne
	@MapsId("CTYC_ID")
	@JoinColumn(name = "CTYC_ID")
	private ChiTietYeuCau chiTietYeuCau;
	
	@ManyToOne
	@MapsId("TU_ID")
	@JoinColumn(name = "TU_ID")
	private ThucUong thucUong;
	 
	@Column(name= "CSLTU_SOLUONG")
	private int soLuong;

	public CoSoLuongThucUongId getId() {
		return id;
	}

	public void setId(CoSoLuongThucUongId id) {
		this.id = id;
	}

	public ChiTietYeuCau getChiTietYeuCau() {
		return chiTietYeuCau;
	}

	public void setChiTietYeuCau(ChiTietYeuCau chiTietYeuCau) {
		this.chiTietYeuCau = chiTietYeuCau;
	}

	public ThucUong getThucUong() {
		return thucUong;
	}

	public void setThucUong(ThucUong thucUong) {
		this.thucUong = thucUong;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	
}
