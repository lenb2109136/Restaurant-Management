package com.example.nienluannganh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LOAITHUCUONG")
public class LoaiThucUong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LTU_ID")
    private int id;

    @Column(name = "LTU_TEN")
    private String ten;

	public int getLtuId() {
		return id;
	}

	public void setLtuId(int ltuId) {
		this.id = ltuId;
	}

	public String getLtuTen() {
		return ten;
	}

	public void setLtuTen(String ltuTen) {
		this.ten = ltuTen;
	}
    
    
}
