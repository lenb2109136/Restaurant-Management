package com.example.nienluannganh.model.embededid;

import java.util.Objects;

import jakarta.persistence.Column;

public class ChiTietYeuCauBanId {
	@Column(name = "CTYC_ID") 
	 private int CTYC_ID; 
	
	@Column(name = "BAN_STT") 
	private int BAN_STT;

	

	public int getCTYC_ID() {
		return CTYC_ID;
	}

	public void setCTYC_ID(int cTYC_ID) {
		CTYC_ID = cTYC_ID;
	}

	public int getBAN_STT() {
		return BAN_STT;
	}

	public void setBAN_STT(int bAN_STT) {
		BAN_STT = bAN_STT;
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    ChiTietYeuCauBanId that = (ChiTietYeuCauBanId) o;
	    return CTYC_ID == that.CTYC_ID && BAN_STT == that.BAN_STT;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(CTYC_ID, BAN_STT);
	}

}
