package com.example.nienluannganh.model;

import java.sql.Date;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "GIATHUCUONG")
public class GiaThucUong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GTU_ID")
    private int id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn (name = "TU_ID")
    private ThucUong thucUong;

    @Column(name = "GTU_GIA")
    private float Gia;

    @Column(name = "GTU_BATDAU")
    private LocalDate batDau;

    @Column(name = "GTU_KETTHUC")
    private LocalDate ketThuc;
    
   
    @ManyToOne 
    @JoinColumn(name = "DMSL_ID")
    private DinhMucSoLuong dinhMucSoLuong;
    
    

    
	public DinhMucSoLuong getDinhMucSoLuong() {
		return dinhMucSoLuong;
	}

	public void setDinhMucSoLuong(DinhMucSoLuong dinhMucSoLuong) {
		this.dinhMucSoLuong = dinhMucSoLuong;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ThucUong getThucUong() {
		return thucUong;
	}

	public void setThucUong(ThucUong thucUong) {
		this.thucUong = thucUong;
	}

	public float getGia() {
		return Gia;
	}

	public void setGia(float gia) {
		Gia = gia;
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

	
    
    
}
