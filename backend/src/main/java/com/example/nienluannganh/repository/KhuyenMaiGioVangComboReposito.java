package com.example.nienluannganh.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nienluannganh.model.KhuyenMaiGioVangComBo;
import com.example.nienluannganh.model.embededid.KhuyenMaiGioVangComBoId;

public interface KhuyenMaiGioVangComboReposito
extends JpaRepository<KhuyenMaiGioVangComBo, KhuyenMaiGioVangComBoId>
{
	@Query(value = "SELECT \r\n"
			+ "l.KM_TEN, \r\n"
			+ "l.KM_ID,k.KM_GIATRIKHUYENMAI,k.KM_SOLUONGTU, \r\n"
			+ "k.CB_ID\r\n"
			+ "FROM khuyenmaigiovangcombo k\r\n"
			+ "JOIN khuyenmai l ON k.KM_ID = l.KM_ID\r\n"
			+ "WHERE NOW() BETWEEN l.KM_NGAYGIOAPDUNG AND l.KM_NGAYGIOKETTHUC \r\n"
			+ "AND k.CB_ID =:id",nativeQuery = true)
	public List<Map<Object, Object>> getkmma(@Param("id") int id);
}


