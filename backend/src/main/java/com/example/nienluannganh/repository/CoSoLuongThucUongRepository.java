package com.example.nienluannganh.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.CoSoLuongThucUong;
import com.example.nienluannganh.model.ThucUong;
import com.example.nienluannganh.model.embededid.CoSoLuongThucUongId;

@Repository
public interface CoSoLuongThucUongRepository extends JpaRepository<CoSoLuongThucUong,CoSoLuongThucUongId> {
	@Query(value = "SELECT SUM(c.CSLTU_SOLUONG) \r\n"
			+ "FROM cosoluongthucuong c\r\n"
			+ "JOIN chitietyeucau cc ON cc.CTYC_ID = c.CTYC_ID\r\n"
			+ "JOIN yeucau y ON y.YC_ID = cc.YC_ID\r\n"
			+ "WHERE c.CSLTU_SOLUONG >:sl\r\n"
			+ "AND c.TU_ID =:idma\r\n"
			+ "AND DATE(y.YC_THOIGIANYEUCAU) =:tg\r\n"
			+ "GROUP BY DATE(y.YC_THOIGIANYEUCAU), c.TU_ID;",nativeQuery = true)
	public Integer TongSo(int sl, int idma,LocalDate tg);
	
}
