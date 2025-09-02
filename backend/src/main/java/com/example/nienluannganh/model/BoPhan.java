package com.example.nienluannganh.model;

import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="BOPHAN")
public class BoPhan {
	@Id
	@Column(name="BP_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name="BP_TEN")
	@NotBlank(message = "Tên bộ phận không được để trống")
	private String ten;

	
	@OneToMany(mappedBy = "boPhan")
	private List<ChucVu> chucVu;
	
	
	
	public List<ChucVu> getChucVu() {
		return chucVu;
	}

	public void setChucVu(List<ChucVu> chucVu) {
		this.chucVu = chucVu;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	
	
}
