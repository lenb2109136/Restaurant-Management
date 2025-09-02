package com.example.nienluannganh.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "SANH")
public class Sanh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SANH_ID")
    private int id;

    @NotBlank(message = "Tên sảnh không được để trống")
    @Column(name = "SANH_TENSANH")
    private String ten;
    
    @OneToMany(mappedBy = "sanh")
    private List<Ban> dsBan;

    
    
	public List<Ban> getDsBan() {
		return dsBan;
	}

	public void setDsBan(List<Ban> dsBan) {
		this.dsBan = dsBan;
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
    
    
}