package com.example.nienluannganh.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "YEUCAU")
public class YeuCau {
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "YC_ID")
	    private int id;

	    @Column(name = "YC_THOIGIANYEUCAU")
	    @NotNull(message = "thời gian yêu cầu không được rỗng")
	    private LocalDateTime thoiGianYeuCau= LocalDateTime.now();

	    @Column(name = "YC_TRANGTHAIYEUCAU")
	    private int trangThaiYeuCau;

	    @Column(name = "YC_GHEPBAN")
	    private boolean ghepBan=false;


	    @ManyToOne
	    @JoinColumn(name = "NV_ID")
	    private NhanVien nhanVien;

	    
	    private LocalDateTime YC_NGAYLAP;
	    
	    
		public LocalDateTime getYC_NGAYLAP() {
			return YC_NGAYLAP;
		}

		public void setYC_NGAYLAP(LocalDateTime yC_NGAYLAP) {
			YC_NGAYLAP = yC_NGAYLAP;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public LocalDateTime getThoiGianYeuCau() {
			return thoiGianYeuCau;
		}

		public void setThoiGianYeuCau(LocalDateTime thoiGianYeuCau) {
			this.thoiGianYeuCau = thoiGianYeuCau;
		}

		public int getTrangThaiYeuCau() {
			return trangThaiYeuCau;
		}

		public void setTrangThaiYeuCau(int trangThaiYeuCau) {
			this.trangThaiYeuCau = trangThaiYeuCau;
		}

		public boolean isGhepBan() {
			return ghepBan;
		}

		public void setGhepBan(boolean ghepBan) {
			this.ghepBan = ghepBan;
		}

		

		public NhanVien getNhanVien() {
			return nhanVien;
		}

		public void setNhanVien(NhanVien nhanVien) {
			this.nhanVien = nhanVien;
		}
	    
	    
}
