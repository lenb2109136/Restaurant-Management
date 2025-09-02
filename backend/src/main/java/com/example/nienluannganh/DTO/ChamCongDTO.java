package com.example.nienluannganh.DTO;

import com.example.nienluannganh.model.BoPhan;
import com.example.nienluannganh.model.Ca;
import com.example.nienluannganh.model.ChucVu;
import com.example.nienluannganh.model.GiaCaBoPhan;
import com.example.nienluannganh.model.NhanVien;
import com.example.nienluannganh.model.ThoiGianLamCa;

public class ChamCongDTO {
    private Integer nhanVienId;
    private NhanVien nhanVien;
    private ChucVu chucVu;
    private BoPhan boPhan;
    private GiaCaBoPhan giaCaBoPhan;
    private Integer soGioDiTre=0;
    private Integer soGioTangCa=0;
    private ThoiGianLamCa thoiGianLamCa;
    private Ca ca;

    public ChamCongDTO(Integer nhanVienId, NhanVien nhanVien, ChucVu chucVu, BoPhan boPhan, GiaCaBoPhan giaCaBoPhan,
            ThoiGianLamCa t, Ca c) {
        this.nhanVienId = nhanVienId;
        this.nhanVien = nhanVien;
        this.chucVu = chucVu;
        this.boPhan = boPhan;
        this.giaCaBoPhan = giaCaBoPhan;
        this.thoiGianLamCa = t;
        this.ca = c;
    }

    public Integer getNhanVienId() {
        return nhanVienId;
    }

    public Ca getCa() {
        return ca;
    }

    public void setCa(Ca ca) {
        this.ca = ca;
    }

    public ThoiGianLamCa getThoiGianLamCa() {
        return thoiGianLamCa;
    }

    public void setThoiGianLamCa(ThoiGianLamCa thoiGianLamCa) {
        this.thoiGianLamCa = thoiGianLamCa;
    }

    public void setNhanVienId(Integer nhanVienId) {
        this.nhanVienId = nhanVienId;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public BoPhan getBoPhan() {
        return boPhan;
    }

    public void setBoPhan(BoPhan boPhan) {
        this.boPhan = boPhan;
    }

    public GiaCaBoPhan getGiaCaBoPhan() {
        return giaCaBoPhan;
    }

    public void setGiaCaBoPhan(GiaCaBoPhan giaCaBoPhan) {
        this.giaCaBoPhan = giaCaBoPhan;
    }

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

}