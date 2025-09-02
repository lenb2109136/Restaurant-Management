package com.example.nienluannganh.DTO;

import java.util.List;


public class ThongTinBan {
	int soLuongNguoi;
	List<Ban> ban;
	List<ItemOrder> dsma;
	List<ItemOrder> dstu;
	List<ItemOrder> dscb;
	int id;
	
	
	public ThongTinBan() {
	}
	public int getSoLuongNguoi() {
		return soLuongNguoi;
	}
	public void setSoLuongNguoi(int soLuongNguoi) {
		this.soLuongNguoi = soLuongNguoi;
	}
	public List<Ban> getBan() {
		return ban;
	}
	public void setBan(List<Ban> ban) {
		this.ban = ban;
	}
	public List<ItemOrder> getDsma() {
		return dsma;
	}
	public void setDsma(List<ItemOrder> dsma) {
		this.dsma = dsma;
	}
	public List<ItemOrder> getDstu() {
		return dstu;
	}
	public void setDstu(List<ItemOrder> dstu) {
		this.dstu = dstu;
	}
	public List<ItemOrder> getDscb() {
		return dscb;
	}
	public void setDscb(List<ItemOrder> dscb) {
		this.dscb = dscb;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
