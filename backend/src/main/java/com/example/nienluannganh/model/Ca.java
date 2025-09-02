package com.example.nienluannganh.model;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="CA")
public class Ca {
	@Id
	@Column(name="CA_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="CA_THOIGIANBATDAU")
	@NotNull(message = "Thời gian bắt đầu ca không được để trống")
	private LocalTime ThoiGianBatDau;
	
	@Column(name="CA_THOIGIANKETTHUC")
	@NotNull(message = "Thời gian kết thúc ca không được để trống")
	private LocalTime ThoiGianKetThuc;
	
	
	@Column(name = "CA_CONSUDUNG")
	private boolean conSuDung;
	
	@OneToMany(mappedBy = "ca")
	private List<GiaCaBoPhan> giaCaBoPhan;
	public int getId() {
		return id;
	}

	public Integer tinhPhutDiTre(LocalTime thoiGianDen) {
		return (int) Duration.between(this.ThoiGianBatDau, thoiGianDen).toMinutes();
	}

	public Integer tinhPhutChenhLechVe(LocalTime thoiGianVe) {
		return (int) Duration.between(this.ThoiGianKetThuc, thoiGianVe).toMinutes();
	}

	
	public boolean isConSuDung() {
		return conSuDung;
	}



	public void setConSuDung(boolean conSuDung) {
		this.conSuDung = conSuDung;
	}



	public List<GiaCaBoPhan> getGiaCaBoPhan() {
		return giaCaBoPhan;
	}

	public void setGiaCaBoPhan(List<GiaCaBoPhan> giaCaBoPhan) {
		this.giaCaBoPhan = giaCaBoPhan;
	}

	public void setId(int id) {
		id = id;
	}

	public LocalTime getThoiGianBatDau() {
		return ThoiGianBatDau;
	}

	public void setThoiGianBatDau(LocalTime thoiGianBatDau) {
		ThoiGianBatDau = thoiGianBatDau;
	}

	public LocalTime getThoiGianKetThuc() {
		return ThoiGianKetThuc;
	}

	public void setThoiGianKetThuc(LocalTime thoiGianKetThuc) {
		ThoiGianKetThuc = thoiGianKetThuc;
	}

	
	
}
