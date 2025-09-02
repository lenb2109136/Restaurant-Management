package com.example.nienluannganh.DTO;

import jakarta.validation.constraints.Min;

public class ItemOrder {
	int Id;
	@Min(value = 1, message = "Số lượng order không được nhỏ hơn 1")
	int soLuong;
	String ten;
	String ghiChu;
	
	
	
	
	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public ItemOrder() {
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	
}
