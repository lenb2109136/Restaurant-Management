package com.example.nienluannganh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.GiaMonAn;

@Repository
public interface GiaMonAnRepository extends JpaRepository<GiaMonAn, Integer>{
	@Query(value = "SELECT * FROM giamonan WHERE MA_ID=:id AND giamonan_ketthuc IS null ORDER BY giamonan_gia desc", nativeQuery = true)
	public List<GiaMonAn> getgia(@Param("id") int id);
}
