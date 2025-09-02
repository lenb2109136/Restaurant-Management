package com.example.nienluannganh.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class KhuyenMaiItem {
	@NotNull(message ="thông tin các thành phần không hợp lệ ")
	private Integer id;
	@NotNull(message ="thông tin các thành phần không hợp lệ")
	@Min(value = 1,message = "Số lượng mỗi món phải lớn hơn 0")
	private Integer soluong;
	@NotNull(message ="thông tin món ăn không hợp lệ")
	@Min(value = 1,message = "Giá trị khuyến mãi không được bé hơn 0")
	@Max(value = 100,message = "Giá trị khuyến mãi không được lớn hơn 100")
	private Float gia;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSoLuong() {
		return soluong;
	}
	public void setSoLuong(Integer soLuong) {
		this.soluong = soLuong;
	}
	public Float getGia() {
		return gia;
	}
	public void setGia(Float gia) {
		this.gia = gia;
	}
	
}
