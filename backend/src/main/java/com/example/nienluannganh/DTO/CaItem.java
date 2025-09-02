package com.example.nienluannganh.DTO;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public class CaItem {
		@NotNull(message = "Vui lòng cung cấp thông tin ca")
		private int caid;
		private LocalTime thoigianbatdau;
		private LocalTime thoigianketthuc;
		public int getCaid() {
			return caid;
		}
		public void setCaid(int caid) {
			this.caid = caid;
		}
		public LocalTime getThoigianbatdau() {
			return thoigianbatdau;
		}
		public void setThoigianbatdau(LocalTime thoigianbatdau) {
			this.thoigianbatdau = thoigianbatdau;
		}
		public LocalTime getThoigianketthuc() {
			return thoigianketthuc;
		}
		public void setThoigianketthuc(LocalTime thoigianketthuc) {
			this.thoigianketthuc = thoigianketthuc;
		}
		public CaItem(@NotNull(message = "Vui lòng cung cấp thông tin ca") int caid, LocalTime thoigianbatdau,
				LocalTime thoigianketthuc) {
			super();
			this.caid = caid;
			this.thoigianbatdau = thoigianbatdau;
			this.thoigianketthuc = thoigianketthuc;
		}
		
}
