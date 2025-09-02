package com.example.nienluannganh.DTO;

import jakarta.validation.constraints.Min;

public class MonAnComBoItem {
	@Min(value = 1,message = "Mã của món ăn trong danh sách không hợp lệ")
	private int id;
	@Min(value = 1,message = "Số lượng của từng món ăn trong danh sách phải lớn hơn 0")
	private int soluong;
	private String ten;
	public MonAnComBoItem(@Min(value = 1, message = "Mã của món ăn trong danh sách không hợp lệ") int id,
			@Min(value = 1, message = "Số lượng của từng món ăn trong danh sách phải lớn hơn 0") int soluong) {
		super();
		this.id = id;
		this.soluong = soluong;
	}
	public MonAnComBoItem() {
		super();
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
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	
	
}
