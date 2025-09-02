package com.example.nienluannganh.model.embededid;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class KhuyenMaiGioVangMonAnId implements Serializable{
	@Column(name = "KM_ID") 
	 private int KM_ID; 
	
	@Column(name = "MA_ID") 
	private int MA_ID;

	public int getKM_ID() {
		return KM_ID;
	}

	public void setKM_ID(int kM_ID) {
		KM_ID = kM_ID;
	}

	public int getMA_ID() {
		return MA_ID;
	}

	public void setMA_ID(int mA_ID) {
		MA_ID = mA_ID;
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    KhuyenMaiGioVangMonAnId that = (KhuyenMaiGioVangMonAnId) o;
	    return KM_ID == that.getKM_ID() && MA_ID == that.getMA_ID();
	}

	@Override
	public int hashCode() {
	    return Objects.hash(KM_ID, MA_ID);
	}
}
