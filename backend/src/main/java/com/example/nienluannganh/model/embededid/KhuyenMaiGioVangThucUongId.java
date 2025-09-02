package com.example.nienluannganh.model.embededid;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class KhuyenMaiGioVangThucUongId implements Serializable{
	@Column(name = "KM_ID") 
	 private int KM_ID; 
	
	@Column(name = "TU_ID") 
	private int TU_ID;


	public int getKM_ID() {
		return KM_ID;
	}

	public void setKM_ID(int kM_ID) {
		KM_ID = kM_ID;
	}

	public int getTU_ID() {
		return TU_ID;
	}

	public void setTU_ID(int tU_ID) {
		TU_ID = tU_ID;
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    KhuyenMaiGioVangThucUongId that = (KhuyenMaiGioVangThucUongId) o;
	    return KM_ID == that.getKM_ID() && TU_ID == that.getTU_ID();
	}

	@Override
	public int hashCode() {
	    return Objects.hash(KM_ID, TU_ID);
	}
}
