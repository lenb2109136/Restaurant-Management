package com.example.nienluannganh.model;

import com.example.nienluannganh.model.embededid.ChiTietYeuCauBanId;
import com.example.nienluannganh.model.embededid.CoSoLuongComBoId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHITIETYEUCAUBAN")
public class ChiTietYeuCauBan {
	@EmbeddedId
	private ChiTietYeuCauBanId id;
	
	@ManyToOne
	@MapsId("CTYC_ID")
	@JoinColumn(name = "CTYC_ID")
	private ChiTietYeuCau chiTietYeuCau;
	
	@ManyToOne
	@MapsId("BAN_STT")
	@JoinColumn(name = "BAN_STT")
	private Ban ban;

	public ChiTietYeuCauBanId getId() {
		return id;
	}

	public void setId(ChiTietYeuCauBanId id) {
		this.id = id;
	}

	public ChiTietYeuCau getChiTietYeuCau() {
		return chiTietYeuCau;
	}

	public void setChiTietYeuCau(ChiTietYeuCau chiTietYeuCau) {
		this.chiTietYeuCau = chiTietYeuCau;
	}

	public Ban getBan() {
		return ban;
	}

	public void setBan(Ban ban) {
		this.ban = ban;
	}
}
