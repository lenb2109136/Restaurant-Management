package com.example.nienluannganh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.ChucVu;
@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, Integer> {
@Query(value = "select * from chucvu where BP_ID=:id",nativeQuery = true)
	public List<ChucVu> getbybp(@Param("id") int id);
}
