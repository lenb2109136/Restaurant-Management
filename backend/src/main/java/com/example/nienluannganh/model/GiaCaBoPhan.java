package com.example.nienluannganh.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "GIACABOPHAN")
public class GiaCaBoPhan {
	    @Id
	    @Column(name = "GBP_ID")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @JsonIgnore
	    private int giaBoPhanId;

	    @Column(name = "GPB_BATDAU")
	    private LocalDate BatDau= LocalDate.now();

	    @Column(name = "GBP_KETTHUC")
	    private LocalDate KetThuc;
	    
	    @Min(value = 0, message = "Giá ca làm của từng bộ phải lớn hơn 0")
	    @Column(name = "GBP_GIA")
	    private Float Gia;
	    
	    @ManyToOne
	    @JoinColumn(name = "CA_ID")
	    @NotNull(message = "Bạn phải chọn ca làm trước khi thêm")
	    @JsonIgnore
	    private Ca ca;

	    @ManyToOne
	    @JoinColumn(name = "BP_ID")
	    @NotNull(message = "Bạn phải chọn bộ phận trước khi thêm")
	    private BoPhan boPhan;
	    
	    @ManyToOne
	    @JoinColumn(name="CV_ID")
	    private ChucVu chucVu;
	    
	    public ChucVu getChucVu() {
			return chucVu;
		}

		public void setChucVu(ChucVu chucVu) {
			this.chucVu = chucVu;
		}

		public int getGiaBoPhanId() {
			return giaBoPhanId;
		}

		public void setGiaBoPhanId(int giaBoPhanId) {
			this.giaBoPhanId = giaBoPhanId;
		}

		public LocalDate getBatDau() {
			return BatDau;
		}

		public void setBatDau(LocalDate batDau) {
			BatDau = batDau;
		}

		public LocalDate getKetThuc() {
			return KetThuc;
		}

		public void setKetThuc(LocalDate ketThuc) {
			KetThuc = ketThuc;
		}

		public Float getGia() {
			return Gia;
		}

		public void setGia(Float gia) {
			Gia = gia;
		}

		public Ca getCa() {
			return ca;
		}

		public void setCa(Ca ca) {
			this.ca = ca;
		}

		public BoPhan getBoPhan() {
			return boPhan;
		}

		public void setBoPhan(BoPhan boPhan) {
			this.boPhan = boPhan;
		}

	    
	    
}
