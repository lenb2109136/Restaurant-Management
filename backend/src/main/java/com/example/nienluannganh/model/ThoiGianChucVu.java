package com.example.nienluannganh.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "THOIGIANCHUCVU")
public class ThoiGianChucVu {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TGCV_ID")
    private int id;

    @Column(name = "TGCV_BATDAU")
    private LocalDate batDau=LocalDate.now();

    @Column(name = "TGCV_KETTHUC")
    private LocalDate ketThuc;

	@ManyToOne
    @JoinColumn(name = "BP_ID")
    @NotNull(message = "Vui lòng chọn bộ phận trước khi thêm")
    private BoPhan boPhan;
	
	@ManyToOne
    @JoinColumn(name = "CV_ID")
    private ChucVu chucVu;
	
	

    public ChucVu getChucVu() {
		return chucVu;
	}

	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getBatDau() {
		return batDau;
	}

	public void setBatDau(LocalDate batDau) {
		this.batDau = batDau;
	}

	public LocalDate getKetThuc() {
		return ketThuc;
	}

	public void setKetThuc(LocalDate ketThuc) {
		this.ketThuc = ketThuc;
	}

	public BoPhan getBoPhan() {
		return boPhan;
	}

	public void setBoPhan(BoPhan boPhan) {
		this.boPhan = boPhan;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@ManyToOne
    @JoinColumn(name = "NV_ID")
    @NotNull(message = "Vui lòng chọn nhân viên trước khi thêm")
    private NhanVien nhanVien;
    
   

}
