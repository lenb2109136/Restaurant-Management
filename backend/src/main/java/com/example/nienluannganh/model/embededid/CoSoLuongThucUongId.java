package com.example.nienluannganh.model.embededid;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class CoSoLuongThucUongId {
	@Column(name = "CTYC_ID") 
	 private int CTYC_ID; 
	
	@Column(name = "TU_ID") 
	private int TU_ID;

	public int getCTYC_ID() {
		return CTYC_ID;
	}

	public void setCTYC_ID(int cTYC_ID) {
		CTYC_ID = cTYC_ID;
	}

	public int getMA_ID() {
		return TU_ID;
	}

	public void setMA_ID(int mA_ID) {
		TU_ID = mA_ID;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    CoSoLuongThucUongId that = (CoSoLuongThucUongId) o;
	    return CTYC_ID == that.CTYC_ID && TU_ID == that.TU_ID;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(CTYC_ID, TU_ID);
	}
}
