package com.example.nienluannganh.model;

import com.example.nienluannganh.model.embededid.CoSoLuongMonAnId;
import com.example.nienluannganh.model.embededid.ComBoMonAnId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity 
@Table(name = "COMBO_MA")
public class ComBoMonAn {
	@EmbeddedId
	private ComBoMonAnId id;
	
	@ManyToOne
	@MapsId("CB_ID")
	@JoinColumn(name = "CB_ID")
	private ComBo comBo;
	
	@ManyToOne
	@MapsId("MA_ID")
	@JoinColumn(name = "MA_ID")
	private MonAn monAn;
	 
	@Column(name= "CBMA_SOLUONG")
	private int soLuong;

	public ComBoMonAnId getId() {
		return id;
	}

	public void setId(ComBoMonAnId id) {
		this.id = id;
	}

	public ComBo getComBo() {
		return comBo;
	}

	public void setComBo(ComBo comBo) {
		this.comBo = comBo;
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
