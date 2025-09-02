package com.example.nienluannganh.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.KhuyenMaiMonAn;
import com.example.nienluannganh.model.embededid.KhuyenMaiMonAnId;

@Repository
public interface KhuyenMaiMonAnRepository extends JpaRepository<KhuyenMaiMonAn, KhuyenMaiMonAnId>{
	@Query(value = "SELECT * FROM khuyenmaimonan where KM_ID=:id",nativeQuery = true)
	public List<KhuyenMaiMonAn> getallkmma(@Param("id") int id);
	
	@Query(value = "SELECT \r\n"
			+ "    l.KM_TEN, \r\n"
			+ "    l.KM_ID,k.KM_GIATRIKHUYENMAI,k.KM_SOLUONGTU, \r\n"
			+ "    k.MA_ID\r\n"
			+ "FROM khuyenmaimonan k \r\n"
			+ "JOIN khuyenmai l ON k.KM_ID = l.KM_ID\r\n"
			+ "WHERE NOW() BETWEEN l.KM_NGAYGIOAPDUNG AND l.KM_NGAYGIOKETTHUC AND k.MA_ID=:id",nativeQuery = true)
	public List<Map<Object, Object>> getkmma(@Param("id") int id);
	
	@Query(value = "SELECT  \r\n"
			+ "    SUM(TongGiaTriKhuyenMai) AS TongGiaTriKhuyenMai\r\n"
			+ "FROM (\r\n"
			+ "    SELECT  \r\n"
			+ "        SUM(k.KM_GIATRIKHUYENMAI) AS TongGiaTriKhuyenMai\r\n"
			+ "    FROM khuyenmaimonan k\r\n"
			+ "    JOIN khuyenmai l ON k.KM_ID = l.KM_ID\r\n"
			+ "    WHERE NOW() BETWEEN l.KM_NGAYGIOAPDUNG AND l.KM_NGAYGIOKETTHUC\r\n"
			+ "    AND k.MA_ID =:id\r\n"
			+ "\r\n"
			+ "    UNION ALL\r\n"
			+ "\r\n"
			+ "    SELECT  \r\n"
			+ "        SUM(k.KM_GIATRIKHUYENMAI) AS TongGiaTriKhuyenMai\r\n"
			+ "    FROM khuyenmaigiovangmonan k\r\n"
			+ "    JOIN khuyenmai l ON k.KM_ID = l.KM_ID\r\n"
			+ "    WHERE NOW() BETWEEN l.KM_NGAYGIOAPDUNG AND l.KM_NGAYGIOKETTHUC\r\n"
			+ "    AND k.MA_ID =:id\r\n"
			+ ") AS CombinedResult;\r\n"
			+ "",nativeQuery = true)
	public Float tongkm(int id);
}
