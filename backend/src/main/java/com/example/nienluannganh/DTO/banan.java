package com.example.nienluannganh.DTO;

import java.util.List;

import com.example.nienluannganh.model.Ban;

public class banan {
	List<Ban> ban;
	List<ItemKhuuyenMai> item;
	
	private List<ItemKhuuyenMai> dsma;
	private List<ItemKhuuyenMai> dscb;
	private List<ItemKhuuyenMai> dstu;
	public List<Ban> getBan() {
		return ban;
	}
	public void setBan(List<Ban> ban) {
		this.ban = ban;
	}
	public List<ItemKhuuyenMai> getItem() {
		return item;
	}
	public void setItem(List<ItemKhuuyenMai> item) {
		this.item = item;
	}
	public List<ItemKhuuyenMai> getDsma() {
		return dsma;
	}
	public void setDsma(List<ItemKhuuyenMai> dsma) {
		this.dsma = dsma;
	}
	public List<ItemKhuuyenMai> getDscb() {
		return dscb;
	}
	public void setDscb(List<ItemKhuuyenMai> dscb) {
		this.dscb = dscb;
	}
	public List<ItemKhuuyenMai> getDstu() {
		return dstu;
	}
	public void setDstu(List<ItemKhuuyenMai> dstu) {
		this.dstu = dstu;
	}
	
	
	
}
