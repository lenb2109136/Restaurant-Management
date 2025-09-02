package com.example.nienluannganh.model.embededid;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class KhuyenMaiGioVangComBoId implements Serializable{
	@Column(name = "KM_ID") 
	 private int KM_ID; 
	
	@Column(name = "CB_ID") 
	private int CB_ID;
	
	
	
	public int getKM_ID() {
		return KM_ID;
	}

	public void setKM_ID(int kM_ID) {
		KM_ID = kM_ID;
	}

	public int getCB_ID() {
		return CB_ID;
	}

	public void setCB_ID(int cB_ID) {
		CB_ID = cB_ID;
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    KhuyenMaiGioVangComBoId that = (KhuyenMaiGioVangComBoId) o;
	    return KM_ID == that.getKM_ID() && CB_ID == that.getCB_ID();
	}

	@Override
	public int hashCode() {
	    return Objects.hash(KM_ID, CB_ID);
	}
}
