package com.example.nienluannganh.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LOAIMONAN")
public class LoaiMonAn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "LMA_ID")
    private int id;

    @Column(name = "LMA_TENLOAI")
    private String tenLoai;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
    
    
}
