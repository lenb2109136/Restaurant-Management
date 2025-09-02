package com.example.nienluannganh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.ChiTietYeuCau;

import jakarta.transaction.Transactional;

@Repository
public interface ChiTietYeuCauRepository extends JpaRepository<ChiTietYeuCau, Integer>{
	@Query(value = "SELECT * FROM chitietyeucau WHERE YC_ID=:id\r\n"
			+ "",nativeQuery = true)
	public List<ChiTietYeuCau> getctycbyid(int id);
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM chitietyeucau WHERE YC_ID = :ycId", nativeQuery = true)
	void deleteByYcId(@Param("ycId") int ycId);

}
