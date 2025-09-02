package com.example.nienluannganh.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.example.nienluannganh.model.KhuyenMai;
import com.example.nienluannganh.model.NhanVien;

public class HoaDonDTO {
		private int idkhuyenmai;
		private LocalDateTime ngaylap;
		private NhanVien nhanVien;
		private List<banan> banan;
		private float tongtien;
		
		
		public float getTongtien() {
			return tongtien;
		}
		public void setTongtien(float tongtien) {
			this.tongtien = tongtien;
		}
		public int getIdkhuyenmai() {
			return idkhuyenmai;
		}
		public void setIdkhuyenmai(int idkhuyenmai) {
			this.idkhuyenmai = idkhuyenmai;
		}
		public LocalDateTime getNgaylap() {
			return ngaylap;
		}
		public void setNgaylap(LocalDateTime ngaylap) {
			this.ngaylap = ngaylap;
		}
		public NhanVien getNhanVien() {
			return nhanVien;
		}
		public void setNhanVien(NhanVien nhanVien) {
			this.nhanVien = nhanVien;
		}
		
		public List<banan> getBanan() {
			return banan;
		}
		public void setBanan(List<banan> banan) {
			this.banan = banan;
		}
		
		
		
}


