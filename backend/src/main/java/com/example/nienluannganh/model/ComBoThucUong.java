package com.example.nienluannganh.model;

import com.example.nienluannganh.model.embededid.ComBoMonAnId;
import com.example.nienluannganh.model.embededid.ComBoThucUongId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity 
@Table(name = "COMBO_TU")
public class ComBoThucUong {
	@EmbeddedId
	private ComBoThucUongId id;
	
	@ManyToOne
	@MapsId("CB_ID")
	@JoinColumn(name = "CB_ID")
	private ComBo comBo;
	
	@ManyToOne
	@MapsId("TU_ID")
	@JoinColumn(name = "TU_ID")
	private ThucUong thucUong;
	 
	@Column(name= "CBTU_SOLUONG")
	private int soLuong;

	

	public ComBoThucUongId getId() {
		return id;
	}

	public void setId(ComBoThucUongId id) {
		this.id = id;
	}

	public ComBo getComBo() {
		return comBo;
	}

	public void setComBo(ComBo comBo) {
		this.comBo = comBo;
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
