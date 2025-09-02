package com.example.nienluannganh.DTO;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public class CaLamNhanVienDTO {
	@NotNull(message = "Vui Lòng cung cấp thông tin ngày làm cho từng ca")
	private LocalDate ngayLam;
	private int idnv;
	private List<CaItem> dsca;
	public LocalDate getNgayLam() {
		return ngayLam;
	}
	public void setNgayLam(LocalDate ngayLam) {
		this.ngayLam = ngayLam;
	}
	public List<CaItem> getDsca() {
		return dsca;
	}
	public int getIdnv() {
		return idnv;
	}
	public void setIdnv(int idnv) {
		this.idnv = idnv;
	}
	public void setDsca(List<CaItem> dsca) {
		this.dsca = dsca;
	}
	public CaLamNhanVienDTO(@NotNull(message = "Vui Lòng cung cấp thông tin ngày làm cho từng ca") LocalDate ngayLam,
			List<CaItem> dsca) {
		super();
		this.ngayLam = ngayLam;
		this.dsca = dsca;
	}
	public CaLamNhanVienDTO() {
		super();
	}
	
}

