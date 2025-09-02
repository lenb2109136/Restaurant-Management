package com.example.nienluannganh.DTO;

import java.util.List;

import com.example.nienluannganh.model.KhuyenMai;
import com.example.nienluannganh.model.KhuyenMaiThongThuong;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class KhuyenMaiDTO {
	@Valid
	private KhuyenMaiThongThuong dt;
	@Valid
	private List<KhuyenMaiItem> dsma;
	@Valid
	private List<KhuyenMaiItem> dscb;
	@Valid
	private List<KhuyenMaiItem> dstu;
	public KhuyenMaiThongThuong getDt() {
		return dt;
	}
	public void setDt(KhuyenMaiThongThuong dt) {
		this.dt = dt;
	}
	public List<KhuyenMaiItem> getDsma() {
		return dsma;
	}
	public void setDsma(List<KhuyenMaiItem> dsma) {
		this.dsma = dsma;
	}
	public List<KhuyenMaiItem> getDscb() {
		return dscb;
	}
	public void setDscb(List<KhuyenMaiItem> dscb) {
		this.dscb = dscb;
	}
	public List<KhuyenMaiItem> getDstu() {
		return dstu;
	}
	public void setDstu(List<KhuyenMaiItem> dstu) {
		this.dstu = dstu;
	}
	
	
	
}

