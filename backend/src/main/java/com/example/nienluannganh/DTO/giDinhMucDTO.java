package com.example.nienluannganh.DTO;

import com.example.nienluannganh.model.DinhMucSoLuong;

import jakarta.validation.constraints.Min;

public class giDinhMucDTO {
	private DinhMucSoLuong dinhMucSoLuong;
	@Min(value = 1,message = "Giá không thể nhỏ hơn 0")
	private float gia;
	public DinhMucSoLuong getDinhMucSoLuong() {
		return dinhMucSoLuong;
	}
	public void setDinhMucSoLuong(DinhMucSoLuong dinhMucSoLuong) {
		this.dinhMucSoLuong = dinhMucSoLuong;
	}
	public float getGia() {
		return gia;
	}
	public void setGia(float gia) {
		this.gia = gia;
	}
	
	
}
