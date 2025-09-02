package com.example.nienluannganh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DONVITINH")
public class DonViTinh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DVT_ID")
    private int id;

    @Column(name = "DVT_TENDVT")
    private String ten;

	public int getDvtId() {
		return id;
	}

	public void setDvtId(int dvtId) {
		this.id = dvtId;
	}

	public String getDvtTenDVT() {
		return ten;
	}

	public void setDvtTenDVT(String dvtTenDVT) {
		this.ten = dvtTenDVT;
	}
    
    
}
