package com.example.nienluannganh.RAMWEBSOCKET;

import java.util.List;
import java.util.Map;

import com.example.nienluannganh.DTO.ThongTinBan;

public class RamWebsocket {
	List<ThongTinBan> listOrder;
	int id;
	int trangThai=0;
	public List<ThongTinBan> getListOrder() {
		return listOrder;
	}
	public void setListOrder(List<ThongTinBan> listOrder) {
		this.listOrder = listOrder;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
