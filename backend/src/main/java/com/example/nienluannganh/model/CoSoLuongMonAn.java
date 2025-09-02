package com.example.nienluannganh.model;

import java.util.jar.Attributes.Name;

import com.example.nienluannganh.model.embededid.CoSoLuongMonAnId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity 
@Table(name = "COSOLUONGMONAN")
public class CoSoLuongMonAn {
	@EmbeddedId
	private CoSoLuongMonAnId id;
	
	
	@JsonIgnore
	@ManyToOne
	@MapsId("CTYC_ID")
	@JoinColumn(name = "CTYC_ID")
	private ChiTietYeuCau chiTietYeuCau;
	
	@ManyToOne
	@MapsId("MA_ID")
	@JoinColumn(name = "MA_ID")
	private MonAn monAn;
	 
	@Column(name= "CSLMA_SL")
	private int soLuong;

	public CoSoLuongMonAnId getId() {
		return id;
	}

	public void setId(CoSoLuongMonAnId id) {
		this.id = id;
	}

	public ChiTietYeuCau getChiTietYeuCau() {
		return chiTietYeuCau;
	}

	public void setChiTietYeuCau(ChiTietYeuCau chiTietYeuCau) {
		this.chiTietYeuCau = chiTietYeuCau;
	}

	public MonAn getMonAn() {
		return monAn;
	}

	public void setMonAn(MonAn monAn) {
		this.monAn = monAn;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	
}
