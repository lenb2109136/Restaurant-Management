package com.example.nienluannganh.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.KhuyenMaiThucUong;
import com.example.nienluannganh.model.embededid.KhuyenMaiThucUongId;

@Repository
public interface KhuyenMaiThucUongRepository extends JpaRepository<KhuyenMaiThucUong, KhuyenMaiThucUongId>{
	@Query(value = "SELECT * FROM khuyenmaithucuong where KM_ID=:id", nativeQuery = true)
	public List<KhuyenMaiThucUong> getallkmtu(@Param("id") int id);
	
	@Query(value = "SELECT \r\n"
			+ "    l.KM_TEN, \r\n"
			+ "    l.KM_ID,k.KM_GIATRIKHUYENMAI,k.KM_SOLUONGTU, \r\n"
			+ "    k.TU_ID\r\n"
			+ "FROM khuyenmaithucuong k\r\n"
			+ "JOIN khuyenmai l ON k.KM_ID = l.KM_ID\r\n"
			+ "WHERE NOW() BETWEEN l.KM_NGAYGIOAPDUNG AND l.KM_NGAYGIOKETTHUC \r\n"
			+ "    AND k.TU_ID =:id",nativeQuery = true)
	public List<Map<Object, Object>> getkmma(@Param("id") int id);
	
	
	@Query(value = "SELECT  \r\n"
			+ "    SUM(TongGiaTriKhuyenMai) AS TongGiaTriKhuyenMai\r\n"
			+ "FROM (\r\n"
			+ "    SELECT  \r\n"
			+ "        SUM(k.KM_GIATRIKHUYENMAI) AS TongGiaTriKhuyenMai\r\n"
			+ "    FROM khuyenmaithucuong k\r\n"
			+ "    JOIN khuyenmai l ON k.KM_ID = l.KM_ID\r\n"
			+ "    WHERE NOW() BETWEEN l.KM_NGAYGIOAPDUNG AND l.KM_NGAYGIOKETTHUC\r\n"
			+ "    AND k.TU_ID = :id\r\n"
			+ "\r\n"
			+ "    UNION ALL\r\n"
			+ "\r\n"
			+ "    SELECT  \r\n"
			+ "        SUM(k.KM_GIATRIKHUYENMAI) AS TongGiaTriKhuyenMai\r\n"
			+ "    FROM khuyenmaigiovangthucuong k\r\n"
			+ "    JOIN khuyenmai l ON k.KM_ID = l.KM_ID\r\n"
			+ "    WHERE NOW() BETWEEN l.KM_NGAYGIOAPDUNG AND l.KM_NGAYGIOKETTHUC\r\n"
			+ "    AND k.TU_ID = :id\r\n"
			+ ") AS CombinedResult;\r\n"
			+ "",nativeQuery = true)
	public float tongkm(int id);
}
