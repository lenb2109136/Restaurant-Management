package com.example.nienluannganh.model.embededid;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ComBoMonAnId {
	@Column(name = "CB_ID") 
	 private int CB_ID; 
	
	@Column(name = "MA_ID") 
	private int MA_ID;

	
	
	public int getCB_ID() {
		return CB_ID;
	}

	public void setCB_ID(int cB_ID) {
		CB_ID = cB_ID;
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
	    ComBoMonAnId that = (ComBoMonAnId) o;
	    return CB_ID == that.CB_ID && MA_ID == that.MA_ID;
	}
	@Override
	public int hashCode() {
	    return Objects.hash(CB_ID, MA_ID);
	}
}
