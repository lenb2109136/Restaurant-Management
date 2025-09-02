package com.example.nienluannganh.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nienluannganh.model.KhuyenMaiGioVangThucUong;
import com.example.nienluannganh.model.embededid.KhuyenMaiGioVangThucUongId;

public interface KhuyenMaiGioVangThucUongRepository extends JpaRepository<KhuyenMaiGioVangThucUong, KhuyenMaiGioVangThucUongId> {

	@Query(value = "SELECT \r\n"
			+ "    l.KM_TEN, \r\n"
			+ "    l.KM_ID,k.KM_GIATRIKHUYENMAI,k.KM_SOLUONGTU, \r\n"
			+ "    k.TU_ID\r\n"
			+ "FROM khuyenmaigiovangthucuong k\r\n"
			+ "JOIN khuyenmai l ON k.KM_ID = l.KM_ID\r\n"
			+ "WHERE NOW() BETWEEN l.KM_NGAYGIOAPDUNG AND l.KM_NGAYGIOKETTHUC \r\n"
			+ "    AND k.TU_ID =:id",nativeQuery = true)
	public List<Map<Object, Object>> getkmma(@Param("id") int id);
}
