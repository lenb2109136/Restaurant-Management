package com.example.nienluannganh.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "CHAMCONG")
public class ChamCong {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

	@ManyToOne
	@JoinColumn(name = "NV_ID")
    @NotNull(message = "Thiếu thông tin nhân viên")
    private NhanVien nhanVien;

    
    @NotNull(message = "Thiếu thông tin ca làm")
    @ManyToOne
    @JoinColumn(name = "CA_ID")
    private Ca ca;

    @Column(name = "CC_THOIGIANVAO")
    @NotNull(message = "Thiếu thông thời gian vào")
    private LocalTime thoiGianVao;

	@Column(name = "CC_THOIGIANRA")
    @NotNull(message = "Thiếu thông tin thòi gian ra")
    private LocalTime thoiGianRa;

    @Column(name = "CC_VIPHAM")
    private Boolean viPham=false;

    @Column(name = "CC_GHICHU")
    private String ghiChu;

    @Column(name = "CC_NGAYCHAMCONG")
    @NotNull(message = "Thiếu thông tin ngày chấm công")
    private LocalDate ngayChamCong;
    
    public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Ca getCa() {
		return ca;
	}

	public void setCa(Ca ca) {
		this.ca = ca;
	}

	public LocalTime getThoiGianVao() {
		return thoiGianVao;
	}

	public void setThoiGianVao(LocalTime thoiGianVao) {
		this.thoiGianVao = thoiGianVao;
	}

	public LocalTime getThoiGianRa() {
		return thoiGianRa;
	}

	public void setThoiGianRa(LocalTime thoiGianRa) {
		this.thoiGianRa = thoiGianRa;
	}

	public Boolean getViPham() {
		return viPham;
	}

	public void setViPham(Boolean viPham) {
		this.viPham = viPham;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public LocalDate getNgayChamCong() {
		return ngayChamCong;
	}

	public void setNgayChamCong(LocalDate ngayChamCong) {
		this.ngayChamCong = ngayChamCong;
	}

}
