package com.example.nienluannganh.model;

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
@Table(name = "GIAMONAN")
public class GiaMonAn {

    @Id
    @Column(name = "GIAMONAN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public GiaMonAn() {
	}

	@Column(name = "GIAMONAN_GIA")
    private float gia;

    @Column(name = "GIAMONAN_BATDAU")
    private LocalDate batDau;

    @Column(name = "GIAMONAN_KETTHUC")
    private LocalDate ketThuc;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MA_ID")
    private MonAn monAn;

    public GiaMonAn( Float gia, LocalDate batDau, MonAn monAn) {
  
        this.gia = gia;
        this.batDau = batDau;
        this.monAn = monAn;
    }
    
    @ManyToOne 
    @JoinColumn(name = "DMKL_ID")
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

	public Float getGia() {
		return gia;
	}

	public void setGia(Float gia) {
		this.gia = gia;
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

	public MonAn getMonAn() {
		return monAn;
	}

	public void setMonAn(MonAn monAn) {
		this.monAn = monAn;
	}
    
    
}
