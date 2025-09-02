package com.example.nienluannganh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.Ban;

import jakarta.transaction.Transactional;

@Repository
public interface BanRepository extends JpaRepository<Ban, Integer> {
	@Query(value = "SELECT * FROM ban WHERE BAN_SUCCHUA>=:sl AND BAN_TRONG=TRUE ORDER BY BAN_SUCCHUA ASC LIMIT 1", nativeQuery = true)
	public Ban goiY(int sl);
	
@Query(value = "SELECT * FROM ban WHERE BAN_SUCCHUA>=0 AND BAN_TRONG=TRUE ORDER BY SANH_ID,BAN_SUCCHUA  ASC ",nativeQuery = true)
public List<Ban> getBanGoiY();

@Query(value = "SELECT b.* FROM ban b JOIN chitietyeucauban c ON c.BAN_STT=b.BAN_STT where c.CTYC_ID=:id ",nativeQuery = true)
public List<Ban> layy(int id);

@Query(value = "SELECT  b.* FROM ban  b JOIN chitietyeucauban c1 ON b.BAN_STT=c1.BAN_STT \r\n"
		+ "	JOIN chitietyeucau c2 ON c2.CTYC_ID=c1.CTYC_ID where  c2.YC_ID=:id",nativeQuery = true)
public List<Ban> getCon(int id);

@Modifying
@org.springframework.transaction.annotation.Transactional
@Query(value = "UPDATE ban \r\n"
		+ "SET ban_trong = TRUE \r\n"
		+ "WHERE ban_stt IN (\r\n"
		+ "    SELECT c1.BAN_STT \r\n"
		+ "    FROM chitietyeucauban c1 \r\n"
		+ "    JOIN chitietyeucau c2 ON c1.CTYC_ID = c2.CTYC_ID \r\n"
		+ "    JOIN yeucau c3 ON c3.YC_ID = c2.YC_ID \r\n"
		+ "    WHERE c3.YC_ID = :id\r\n"
		+ ");\r\n"
		+ "", nativeQuery = true)
public void setBanTrong(int id);
}
