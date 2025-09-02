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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "THOIGIANLAMCA")
public class ThoiGianLamCa {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 	@Column(name="TGLC_ID")
	    private int id;
	 	
	    @Column(name = "TGLC_BATDAU")
	    private LocalTime batDau=null;
	    
	    @Column(name = "TGLC_KETTHUC")
	    private LocalTime ketThuc=null;
	    
	    @Column(name = "TGLC_NGAYLAM")
	    @NotNull(message = "Ngày làm cca không được để trống")
	    private LocalDate ngaylam;
	    
	    @ManyToOne
	    @JoinColumn(name = "NV_ID")
	    @NotNull(message = "Bạn phải chọn nhân viên trước khi thêm")
	    private NhanVien nhanVien;

		@ManyToOne
	    @JoinColumn(name = "CA_ID")
	    @NotNull(message = "Bạn phải chọn ca làm trước khi thêm")
	    private Ca ca;
		
		@Column(name = "daChamCong")
		private Integer daChamCong;

		public Integer getDaChamCong() {
			return daChamCong;
		}

		public void setDaChamCong(Integer daChamCong) {
			this.daChamCong = daChamCong;
		}

		
		

		 
		public void setDaChamCong(int daChamCong) {
			this.daChamCong = daChamCong;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public LocalTime getBatDau() {
			return batDau;
		}

		public void setBatDau(LocalTime batDau) {
			this.batDau = batDau;
		}

		public LocalTime getKetThuc() {
			return ketThuc;
		}

		public void setKetThuc(LocalTime ketThuc) {
			this.ketThuc = ketThuc;
		}

		public LocalDate getNgaylam() {
			return ngaylam;
		}

		public void setNgaylam(LocalDate ngaylam) {
			this.ngaylam = ngaylam;
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
		
	    

}
