package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.nienluannganh.model.ThoiGianChucVu;

@Repository
public interface ThoiGianChucVuRepository extends JpaRepository<ThoiGianChucVu, Integer> {
	@Query(value ="SELECT * FROM thoigianchucvu WHERE NV_ID=:nv AND BP_ID=:bp AND TGCV_KETTHUC IS null",nativeQuery = true)
	public ThoiGianChucVu get(@Param("nv") int nv,@Param("bp") int bp );
}
