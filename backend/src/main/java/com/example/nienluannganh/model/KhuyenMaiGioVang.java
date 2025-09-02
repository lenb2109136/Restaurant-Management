package com.example.nienluannganh.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "KHUYENMAIGIOVANG")
public class KhuyenMaiGioVang extends KhuyenMai{
	@NotNull(message = "Thông tin giờ áp dụng không được để trống")
	@Column(name="KMGV_GIOAPDUNG")
	private LocalTime gioApDung;
	@Column(name="KMGV_GIOKETTHUC")
	@NotNull(message = "Thông tin giờ kết thúc không được để trống")
	private LocalTime gioKetThuc;
	public LocalTime getGioApDung() {
		return gioApDung;
	}
	public void setGioApDung(LocalTime gioApDung) {
		this.gioApDung = gioApDung;
	}
	public LocalTime getGioKetThuc() {
		return gioKetThuc;
	}
	public void setGioKetThuc(LocalTime gioKetThuc) {
		this.gioKetThuc = gioKetThuc;
	}
}
