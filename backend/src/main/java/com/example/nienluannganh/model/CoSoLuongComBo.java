package com.example.nienluannganh.model;

import com.example.nienluannganh.model.embededid.CoSoLuongComBoId;
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
@Table(name="COSOLUONGCOMBO")
public class CoSoLuongComBo {
	@EmbeddedId
	private CoSoLuongComBoId id;
	
	@JsonIgnore
	@ManyToOne
	@MapsId("CTYC_ID")
	@JoinColumn(name = "CTYC_ID")
	private ChiTietYeuCau chiTietYeuCau;
	
	@ManyToOne
	@MapsId("CB_ID")
	@JoinColumn(name = "CB_ID")
	private ComBo comBo;
	 
	@Column(name= "CSLMA_SL")
	private int soLuong;

	public CoSoLuongComBoId getId() {
		return id;
	}

	public void setId(CoSoLuongComBoId id) {
		this.id = id;
	}

	public ChiTietYeuCau getChiTietYeuCau() {
		return chiTietYeuCau;
	}

	public void setChiTietYeuCau(ChiTietYeuCau chiTietYeuCau) {
		this.chiTietYeuCau = chiTietYeuCau;
	}

	public ComBo getComBo() {
		return comBo;
	}

	public void setComBo(ComBo comBo) {
		this.comBo = comBo;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	
}
