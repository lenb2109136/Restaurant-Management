package com.example.nienluannganh.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHITIETYEUCAU")
public class ChiTietYeuCau {

    @Id
    @Column(name = "CTYC_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "YC_ID")
    private YeuCau yeuCau;
    
    @JsonIgnore
    @OneToMany(mappedBy = "chiTietYeuCau")
    private List<ChiTietYeuCauBan> chiTietYeuCauBans;
    
    @JsonIgnore
    @OneToMany(mappedBy = "chiTietYeuCau")
    private List<CoSoLuongComBo> coSoLuongComBos;
    
    @JsonIgnore
    @OneToMany(mappedBy = "chiTietYeuCau")
    private List<CoSoLuongMonAn> coSoLuongMonAns; 
    
    @JsonIgnore
    @OneToMany(mappedBy = "chiTietYeuCau")
    private List<CoSoLuongThucUong> coSoLuongThucUongs; 
    
    
    

	public List<ChiTietYeuCauBan> getChiTietYeuCauBans() {
		return chiTietYeuCauBans;
	}

	public void setChiTietYeuCauBans(List<ChiTietYeuCauBan> chiTietYeuCauBans) {
		this.chiTietYeuCauBans = chiTietYeuCauBans;
	}

	public List<CoSoLuongComBo> getCoSoLuongComBos() {
		return coSoLuongComBos;
	}

	public void setCoSoLuongComBos(List<CoSoLuongComBo> coSoLuongComBos) {
		this.coSoLuongComBos = coSoLuongComBos;
	}

	public List<CoSoLuongMonAn> getCoSoLuongMonAns() {
		return coSoLuongMonAns;
	}

	public void setCoSoLuongMonAns(List<CoSoLuongMonAn> coSoLuongMonAns) {
		this.coSoLuongMonAns = coSoLuongMonAns;
	}

	public List<CoSoLuongThucUong> getCoSoLuongThucUongs() {
		return coSoLuongThucUongs;
	}

	public void setCoSoLuongThucUongs(List<CoSoLuongThucUong> coSoLuongThucUongs) {
		this.coSoLuongThucUongs = coSoLuongThucUongs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public YeuCau getYeuCau() {
		return yeuCau;
	}

	public void setYeuCau(YeuCau yeuCau) {
		this.yeuCau = yeuCau;
	}

	
    
}
