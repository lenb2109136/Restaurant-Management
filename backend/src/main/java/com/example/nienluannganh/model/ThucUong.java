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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "THUCUONG")
public class ThucUong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TU_ID")
    private int id;

    @Column(name = "TU_TEN")
    @NotBlank(message = "Vui lòng điền tên thức uống")
    private String ten;
    
    @Column(name = "TU_GIA")
//    @Min(value = 1,message = "Giá thức uống phải lớn hơn không")
//    @NotNull(message = "Giá không được để trống")
    private Float gia;
    
    @Column(name = "TU_MOTA")
    
    private String moTa;

    @Column(name = "TU_ANHGIOITHIEU")
    private String anhGioiThieu;

    @Column(name = "TU_TINHTRANGSUDUNG")
    private boolean tinhTrangSuDung=true;

    @Column(name = "TU_CONHANG")
    private boolean conHang;
    
    @ManyToOne
    @JoinColumn(name = "DVT_ID")
//    @NotNull(message = "Đơn vị tính không được để trống")
    private DonViTinh donViTinh;

    @ManyToOne
    @JoinColumn(name = "LTU_ID")
//    @NotNull(message = "Loại thức uống không được để trống")
    private LoaiThucUong loaiThucUong;

    public ThucUong() {
    }
    
	public ThucUong(@NotBlank(message = "Vui lòng điền tên thức uống") String ten,
			@Min(value = 0, message = "Giá thức uống phải lớn hơn không") @NotNull(message = "Giá không được để trống") Float gia,
			String moTa, boolean tinhTrangSuDung, boolean conHang,
			@NotNull(message = "Đơn vị tính không được để trống") DonViTinh donViTinh,
			@NotNull(message = "Loại thức uống không được để trống") LoaiThucUong loaiThucUong) {
		super();
		this.ten = ten;
		this.gia = gia;
		this.moTa = moTa;
		this.tinhTrangSuDung = tinhTrangSuDung;
		this.conHang = conHang;
		this.donViTinh = donViTinh;
		this.loaiThucUong = loaiThucUong;
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

	public DonViTinh getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(DonViTinh donViTinh) {
		this.donViTinh = donViTinh;
	}
	
	

	public LoaiThucUong getLoaiThucUong() {
		return loaiThucUong;
	}

	public void setLoaiThucUong(LoaiThucUong loaiThucUong) {
		this.loaiThucUong = loaiThucUong;
	}

	public Float getGia() {
		return gia;
	}

	public void setGia(Float gia) {
		this.gia = gia;
	}
    
    
}
