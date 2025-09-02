package com.example.nienluannganh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "BAN")
public class Ban {

    @Id
    @Column(name = "BAN_STT")
    private int stt;

    @Column(name = "BAN_TOADOX")
    private Float toaDoX=0.0f;

    @Column(name = "BAN_TOADOY")
    private Float toaDoY=0.0f;

    @Column(name = "BAN_TRONG")
    private Boolean trong;

    @Column(name = "BAN_SUCCHUA")
    private int sucChua;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "SANH_ID")
    private Sanh sanh;
    
    

	public int getSucChua() {
		return sucChua;
	}

	public void setSucChua(int sucChua) {
		this.sucChua = sucChua;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public Float getToaDoX() {
		return toaDoX;
	}

	public void setToaDoX(Float toaDoX) {
		this.toaDoX = toaDoX;
	}

	public Float getToaDoY() {
		return toaDoY;
	}

	public void setToaDoY(Float toaDoY) {
		this.toaDoY = toaDoY;
	}

	public Boolean getTrong() {
		return trong;
	}

	public void setTrong(Boolean trong) {
		this.trong = trong;
	}

	public Sanh getSanh() {
		return sanh;
	}

	public void setSanh(Sanh sanh) {
		this.sanh = sanh;
	}
    
}
