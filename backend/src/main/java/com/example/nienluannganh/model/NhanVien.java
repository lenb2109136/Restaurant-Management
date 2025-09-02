package com.example.nienluannganh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="NHANVIEN")
public class NhanVien {
	@Id
    @Column(name = "NV_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NV_TEN", length = 50)
    @NotBlank(message = "Tên người dùng không được phép rỗng")
    private String ten;

    @Column(name = "NV_DIACHI", length = 120)
    @NotBlank(message = "Địa chỉ không được để trống")
    private String diaChi;

    @Column(name = "NV_ANHGIOITHIEU", length = 120)
    private String anhGioiThieu;

    @Column(name = "NV_GIOITINHNAM")
    private boolean gioiTinhNam;

    @Column(name = "NV_SDT", length = 20)
    @NotBlank(message = "Số điện thoại nhân viên không được để trống")
    @Size(min = 10, max = 10, message = "Số điện thoại phải đúng 10 ký tự")
    @Pattern(regexp = "\\d+", message = "Số điện thoại chỉ được chứa các chữ số")
    private String SDT;
    
    @Column(name = "NV_EMAIL", length = 50)
    private String email;
    
    @Column(name = "NV_TUDONG")
    private int tudong;
    
	@Column(name = "NV_CCCD", length = 40)
    @Size(min = 12, max = 12, message = "Căn cước công dân phải đúng 12 ký tự")
    private String CCCD;
    
    
    @ManyToOne()
    @JoinColumn(name = "BP_ID")
    private BoPhan chucVu;
    
    @ManyToOne()
    @JoinColumn(name = "CV_ID")
    private ChucVu boPhan;
    
    
    
	public int getTudong() {
		return tudong;
	}

	public void setTudong(int tudong) {
		this.tudong = tudong;
	}

	public ChucVu getBoPhan() {
		return boPhan;
	}

	public void setBoPhan(ChucVu boPhan) {
		this.boPhan = boPhan;
	}

	@Column(name = "NV_MATKHAU", length = 20)
    @Size(min = 8, max = 16, message = "Mật khẩu phải có từ 8 - 16 ký tự")
    private String matKhau;

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

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getAnhGioiThieu() {
		return anhGioiThieu;
	}

	public void setAnhGioiThieu(String anhGioiThieu) {
		this.anhGioiThieu = anhGioiThieu;
	}

	public boolean isGioiTinhNam() {
		return gioiTinhNam;
	}

	public void setGioiTinhNam(boolean gioiTinhNam) {
		this.gioiTinhNam = gioiTinhNam;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCCCD() {
		return CCCD;
	}

	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}

	
	public BoPhan getChucVu() {
		return chucVu;
	}

	public void setChucVu(BoPhan chucVu) {
		this.chucVu = chucVu;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	
	

}
