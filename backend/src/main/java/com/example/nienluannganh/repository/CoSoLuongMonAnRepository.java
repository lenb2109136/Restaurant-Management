package com.example.nienluannganh.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.CoSoLuongMonAn;
import com.example.nienluannganh.model.embededid.CoSoLuongMonAnId;

@Repository
public interface CoSoLuongMonAnRepository extends JpaRepository<CoSoLuongMonAn, CoSoLuongMonAnId> {
		@Query(value = "SELECT SUM(c.CSLMA_SL) \r\n"
				+ "FROM cosoluongmonan c\r\n"
				+ "JOIN chitietyeucau cc ON cc.CTYC_ID = c.CTYC_ID\r\n"
				+ "JOIN yeucau y ON y.YC_ID = cc.YC_ID\r\n"
				+ "WHERE c.CSLMA_SL > :sl\r\n"
				+ "AND c.MA_ID = :idma\r\n"
				+ "AND DATE(y.YC_THOIGIANYEUCAU) =:tg\r\n"
				+ "GROUP BY DATE(y.YC_THOIGIANYEUCAU), c.MA_ID;",nativeQuery = true)
		public Integer TongSo(int sl, int idma,LocalDate tg);
}
