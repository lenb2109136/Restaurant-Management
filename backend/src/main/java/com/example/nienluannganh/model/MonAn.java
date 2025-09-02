package com.example.nienluannganh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "MONAN")
public class MonAn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MA_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "MA_TEN", length = 150)
    @NotBlank(message = "Tên món ăn không được trống")
    private String ten;

    @Column(name = "MA_MOTA", length = 1000)
    private String moTa;

    @Column(name = "MA_ANHGIOITHIEU", length = 10)
    private String anhGioiThieu;

    @Column(name = "MA_CHAY")
    @NotNull(message="Vui lòng cho biết món ăn có phải chay không ?")
    private Boolean chay;
    
    @Column(name = "MA_CONHANG")
    private Boolean conHang;

    @Column(name = "MA_TINHTRANGSUDUNG")
    private Boolean tinhTrangSuDung;
    
    @Column(name = "MA_GIA")
//    @NotNull(message = "Vui lòng cung cấp giá món ăn")
//    @Min(value = 1,message = "Giá món ăn phải lớn hơn không")
    private Float gia;
    
    @ManyToOne
    @JoinColumn(name = "LMA_ID")
    private LoaiMonAn loaiMonAn;

    // Getters and Setters
    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }

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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getAnhGioiThieu() {
        return anhGioiThieu;
    }

    public void setAnhGioiThieu(String anhGioiThieu) {
        this.anhGioiThieu = anhGioiThieu;
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

    public LoaiMonAn getLoaiMonAn() {
        return loaiMonAn;
    }

    public void setLoaiMonAn(LoaiMonAn loaiMonAn) {
        this.loaiMonAn = loaiMonAn;
    }
}
