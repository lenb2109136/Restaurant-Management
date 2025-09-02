package com.example.nienluannganh.service;

import java.util.List;

import com.example.nienluannganh.DTO.ChamCongDTO;
import com.example.nienluannganh.model.NhanVien;

public class InfoChamCong {
    private List<ChamCongDTO> chamCong;
    private Double tongSoTien;
    private NhanVien nhanVien;
    private Integer soGioDiTre;

    public Integer getSoGioDiTre() {
        return soGioDiTre;
    }

    public void setSoGioDiTre(Integer soGioDiTre) {
        this.soGioDiTre = soGioDiTre;
    }

    public Integer getSoGioTangCa() {
        return soGioTangCa;
    }

    public void setSoGioTangCa(Integer soGioTangCa) {
        this.soGioTangCa = soGioTangCa;
    }

    private Integer soGioTangCa;

    public List<ChamCongDTO> getChamCong() {
        return chamCong;
    }

    public InfoChamCong(List<ChamCongDTO> chamCong, Double tongSoTien, NhanVien nhanVien) {
        this.chamCong = chamCong;
        this.tongSoTien = tongSoTien;
        this.nhanVien = nhanVien;
    }

    public void setChamCong(List<ChamCongDTO> chamCong) {
        this.chamCong = chamCong;
    }

    public Double getTongSoTien() {
        return tongSoTien;
    }

    public InfoChamCong() {
    }

    public void setTongSoTien(Double tongSoTien) {
        this.tongSoTien = tongSoTien;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

}
