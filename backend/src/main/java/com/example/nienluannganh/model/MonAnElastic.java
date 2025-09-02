package com.example.nienluannganh.model;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;


import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "monan")
public class MonAnElastic {

    private Integer id;
    private String ten;
    private String moTa;
    private Boolean chay;
    private Boolean conHang;
    private Boolean tinhTrangSuDung;
    private Float gia;
    private String tenLoaiMonAn;

    // Constructors, Getters, Setters
    public MonAnElastic() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean getChay() {
        return chay;
    }

    public void setChay(Boolean chay) {
        this.chay = chay;
    }

    public Boolean getConHang() {
        return conHang;
    }

    public void setConHang(Boolean conHang) {
        this.conHang = conHang;
    }

    public Boolean getTinhTrangSuDung() {
        return tinhTrangSuDung;
    }

    public void setTinhTrangSuDung(Boolean tinhTrangSuDung) {
        this.tinhTrangSuDung = tinhTrangSuDung;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }

    public String getTenLoaiMonAn() {
        return tenLoaiMonAn;
    }

    public void setTenLoaiMonAn(String tenLoaiMonAn) {
        this.tenLoaiMonAn = tenLoaiMonAn;
    }
}
