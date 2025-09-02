package com.example.nienluannganh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DINHMUCSOLUONG")
public class DinhMucSoLuong {
	@Id
    @Column(name = "DMSL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(name = "DMSL_SLTU")
	private int soLuongTu;
	@Column(name = "DMSL_SLDEN")
	private int soLuongDen;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSoLuongTu() {
		return soLuongTu;
	}
	public void setSoLuongTu(int soLuongTu) {
		this.soLuongTu = soLuongTu;
	}
	public int getSoLuongDen() {
		return soLuongDen;
	}
	public void setSoLuongDen(int soLuongDen) {
		this.soLuongDen = soLuongDen;
	}
	
	
}
