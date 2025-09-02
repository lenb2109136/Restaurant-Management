package com.example.nienluannganh.DTO;

import java.util.List;

import com.example.nienluannganh.model.KhuyenMaiGioVang;
import com.example.nienluannganh.model.KhuyenMaiThongThuong;

import jakarta.validation.Valid;

public class KhuyenMaiGioVangDTO {
	@Valid
	private KhuyenMaiGioVang dt;
	@Valid
	private List<KhuyenMaiItem> dsma;
	@Valid
	private List<KhuyenMaiItem> dscb;
	@Valid
	private List<KhuyenMaiItem> dstu;
	public KhuyenMaiGioVang getDt() {
		return dt;
	}
	public void setDt(KhuyenMaiGioVang dt) {
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
