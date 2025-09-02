package com.example.nienluannganh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.ThucUong;

@Repository
public interface ThucUongRepository extends JpaRepository<ThucUong, Integer> {
		@Query(value = "SELECT * FROM thucuong WHERE ltu_id=:loai AND tu_tinhtrangsudung=1",nativeQuery = true)
		public List<ThucUong> getDSThucUong(@Param("loai")int id);
		
		@Query(value = "SELECT * FROM thucuong WHERE tu_tinhtrangsudung=1",nativeQuery = true)
		public List<ThucUong> getall();
}
