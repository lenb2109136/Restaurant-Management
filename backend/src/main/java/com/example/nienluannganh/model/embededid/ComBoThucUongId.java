package com.example.nienluannganh.model.embededid;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ComBoThucUongId {
	@Column(name = "CB_ID") 
	 private int CB_ID; 
	
	@Column(name = "TU_ID") 
	private int TU_ID;

	
	
	public int getCB_ID() {
		return CB_ID;
	}

	public void setCB_ID(int cB_ID) {
		CB_ID = cB_ID;
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
	    ComBoThucUongId that = (ComBoThucUongId) o;
	    return CB_ID == that.CB_ID && TU_ID == that.TU_ID;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(CB_ID, TU_ID);
	}
}
