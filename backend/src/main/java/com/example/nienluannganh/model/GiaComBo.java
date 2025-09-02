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
@Table(name = "GIACOMBO")
public class GiaComBo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GCB_ID")
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "CB_ID")
    private ComBo comBo;

    @Column(name = "GCB_GIA")
    private Float gia;
    
    @ManyToOne
    @JoinColumn(name = "DMSL_ID")
    private DinhMucSoLuong dinhMucSoLuong;

    @Column(name = "GCB_BATDAU")
    private LocalDate ngayBatDau;

    @Column(name = "GCB_KETTHUC")
    private LocalDate ngayKetThuc;

    
    
	public DinhMucSoLuong getDinhMucSoLuong() {
		return dinhMucSoLuong;
	}

	public void setDinhMucSoLuong(DinhMucSoLuong dinhMucSoLuong) {
		this.dinhMucSoLuong = dinhMucSoLuong;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ComBo getComBo() {
		return comBo;
	}

	public void setComBo(ComBo comBo) {
		this.comBo = comBo;
	}

	public Float getGia() {
		return gia;
	}

	public void setGia(Float gia) {
		this.gia = gia;
	}

	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

    
    
    
}
