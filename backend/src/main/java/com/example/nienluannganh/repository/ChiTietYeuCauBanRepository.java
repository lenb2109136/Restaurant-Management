package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.ChiTietYeuCauBan;
import com.example.nienluannganh.model.embededid.ChiTietYeuCauBanId;

@Repository
public interface ChiTietYeuCauBanRepository extends JpaRepository<ChiTietYeuCauBan, ChiTietYeuCauBanId>{

}
