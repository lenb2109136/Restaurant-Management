package com.example.nienluannganh.DTO;

import java.util.List;

import com.example.nienluannganh.model.KhuyenMai;

public class ItemKhuuyenMai {
	int id;
	int soluong ;
	List<KhuyenMai> khuyenmai;
	float dongia;
	float tonggiatrikhuyenmai;
	String ten;
	float tong;
	
	
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public float getTong() {
		return tong;
	}
	public void setTong(float tong) {
		this.tong = tong;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public List<KhuyenMai> getKhuyenmai() {
		return khuyenmai;
	}
	public void setKhuyenmai(List<KhuyenMai> khuyenmai) {
		this.khuyenmai = khuyenmai;
	}
	public float getDongia() {
		return dongia;
	}
	public void setDongia(float dongia) {
		this.dongia = dongia;
	}
	public float getTonggiatrikhuyenmai() {
		return tonggiatrikhuyenmai;
	}
	public void setTonggiatrikhuyenmai(float tonggiatrikhuyenmai) {
		this.tonggiatrikhuyenmai = tonggiatrikhuyenmai;
	}
}
