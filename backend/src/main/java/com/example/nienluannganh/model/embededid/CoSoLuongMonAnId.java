package com.example.nienluannganh.model.embededid;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
@Embeddable
public class CoSoLuongMonAnId implements Serializable{
	 @Column(name = "CTYC_ID") 
	 private int CTYC_ID; 
	
	@Column(name = "MA_ID") 
	private int MA_ID;

	public int getCTYC_ID() {
		return CTYC_ID;
	}

	public void setCTYC_ID(int cTYC_ID) {
		CTYC_ID = cTYC_ID;
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
	    CoSoLuongMonAnId that = (CoSoLuongMonAnId) o;
	    return CTYC_ID == that.CTYC_ID && MA_ID == that.MA_ID;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(CTYC_ID, MA_ID);
	}

}
