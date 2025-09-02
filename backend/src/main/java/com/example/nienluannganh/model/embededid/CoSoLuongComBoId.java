package com.example.nienluannganh.model.embededid;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CoSoLuongComBoId {
	 @Column(name = "CTYC_ID") 
	 private int CTYC_ID; 
	
	@Column(name = "CB_ID") 
	private int CB_ID;

	
	
	public int getCTYC_ID() {
		return CTYC_ID;
	}

	public void setCTYC_ID(int cTYC_ID) {
		CTYC_ID = cTYC_ID;
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
	    CoSoLuongComBoId that = (CoSoLuongComBoId) o;
	    return CTYC_ID == that.CTYC_ID && CB_ID == that.CB_ID;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(CTYC_ID, CB_ID);
	}

}
