package com.example.nienluannganh.model;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="chucvu")
public class ChucVu {
	@Id
	@Column(name = "CV_ID")
	@GeneratedValue  (strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "CV_TEN")
	private String ten;
	
	@ManyToOne
	@JoinColumn(name = "BP_ID")
	@JsonIgnore
	private BoPhan boPhan;
	
	
	public BoPhan getBoPhan() {
		return boPhan;
	}
	public void setBoPhan(BoPhan boPhan) {
		this.boPhan = boPhan;
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
