package com.example.nienluannganh.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "KHUYENMAI")
@Inheritance
(strategy = InheritanceType.JOINED)

public class KhuyenMai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KM_ID")
    private Integer id;

    @Column(name = "KM_TEN", length = 500)
    @NotBlank(message = "Tên khuyến mãi không được để trống")
    private String ten;

    @Column(name = "KM_MOTA", length = 500)
    private String moTa;

    @Column(name = "KM_ANHDECO", length = 500)
    private String anhDeco;

    @Column(name = "KM_NGAYGIOAPDUNG")
    @NotNull(message = "Ngày giờ áp dụng không được để trống")
    private LocalDateTime ngayGioApDung;

    @Column(name = "KM_NGAYGIOKETTHUC")
    @NotNull(message = "Ngày giờ kết thúc không được để trống")
    private LocalDateTime ngayGioKetThuc;

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

	public String getAnhDeco() {
		return anhDeco;
	}

	public void setAnhDeco(String anhDeco) {
		this.anhDeco = anhDeco;
	}

	public LocalDateTime getNgayGioApDung() {
		return ngayGioApDung;
	}

	public void setNgayGioApDung(LocalDateTime ngayGioApDung) {
		this.ngayGioApDung = ngayGioApDung;
	}

	public LocalDateTime getNgayGioKetThuc() {
		return ngayGioKetThuc;
	}

	public void setNgayGioKetThuc(LocalDateTime ngayGioKetThuc) {
		this.ngayGioKetThuc = ngayGioKetThuc;
	}
    
    
}
