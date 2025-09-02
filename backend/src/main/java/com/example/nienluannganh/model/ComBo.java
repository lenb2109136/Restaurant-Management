package com.example.nienluannganh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "COMBO")
public class ComBo {

    @Id
    @Column(name = "CB_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CB_TENCOMBO")
    @NotBlank(message = "Tên combo không được để trống")
    private String ten;

    @Column(name = "CB_ANHGIOITHIEU")
    private String anhGioiThieu;

    @Column(name = "CB_GIA")
    private float gia;

    @Column(name = "CB_MOTA")
    private String moTa;

    @Column(name = "CB_TINHTRANGSUDUNG")
    private boolean tinhTrangSuDung=true;
    
    @Column(name = "CB_CONHANG")
    private boolean conHang=true;
    
    @ManyToOne 
    @JoinColumn(name = "LCB_ID")
    private LoaiComBo loaiComBo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getAnhGioiThieu() {
		return anhGioiThieu;
	}

	public void setAnhGioiThieu(String anhGioiThieu) {
		this.anhGioiThieu = anhGioiThieu;
	}

	public float getGia() {
		return gia;
	}

	public void setGia(float gia) {
		this.gia = gia;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public boolean isTinhTrangSuDung() {
		return tinhTrangSuDung;
	}

	public void setTinhTrangSuDung(boolean tinhTrangSuDung) {
		this.tinhTrangSuDung = tinhTrangSuDung;
	}

	public boolean isConHang() {
		return conHang;
	}

	public void setConHang(boolean conHang) {
		this.conHang = conHang;
	}

	public LoaiComBo getLoaiComBo() {
		return loaiComBo;
	}

	public void setLoaiComBo(LoaiComBo loaiComBo) {
		this.loaiComBo = loaiComBo;
	}


    
    
}
