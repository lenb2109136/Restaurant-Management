package com.example.nienluannganh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LOAICOMBO")
public class LoaiComBo {

    @Id
    @Column(name = "LCB_ID")
    private int id;

    @Column(name = "LCB_TEN")
    private String ten;

	@Column(name = "LCB_ANHGIOITHIEU")
    private String anhGioiThieu;
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getAnhGioiThieu() {
		return anhGioiThieu;
	}

	public void setAnhGioiThieu(String anhGioiThieu) {
		this.anhGioiThieu = anhGioiThieu;
	}

    
    
}
