package com.example.nienluannganh.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nienluannganh.model.KhuyenMaiGioVangMonAn;
import com.example.nienluannganh.model.embededid.KhuyenMaiGioVangMonAnId;

public interface KhuyenMaiGioVangMonAnRepositr extends JpaRepository<KhuyenMaiGioVangMonAn, KhuyenMaiGioVangMonAnId> {
	@Query(value = "SELECT \r\n"
	        + "l.KM_TEN, \r\n"
	        + "l.KM_ID,\r\n"
	        + "k.KM_GIATRIKHUYENMAI,\r\n"
	        + "k.KM_SOLUONGTU, \r\n"
	        + "k.MA_ID\r\n"
	        + "FROM khuyenmaigiovangmonan k \r\n"
	        + "JOIN khuyenmai l ON k.KM_ID = l.KM_ID\r\n"
	        + "JOIN khuyenmaigiovang gv ON gv.KM_ID = l.KM_ID\r\n"
	        + "WHERE NOW() BETWEEN l.KM_NGAYGIOAPDUNG AND l.KM_NGAYGIOKETTHUC\r\n"
	        + "  AND CURRENT_TIME BETWEEN gv.KMGV_GIOAPDUNG AND gv.KMGV_GIOKETTHUC\r\n"
	        + "  AND k.MA_ID = :id", nativeQuery = true)
	List<Map<Object, Object>> getkmma(@Param("id") int id);

}


