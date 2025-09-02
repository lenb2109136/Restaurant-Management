package com.example.nienluannganh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.MonAn;

@Repository
public interface MonAnRepository extends JpaRepository<MonAn, Integer>{
	@Query(value = "select * from monan where lma_id=:loai and ma_tinhtrangsudung=1",nativeQuery = true)
	public List<MonAn> getMonAnByLoai(@Param("loai") int idloai);
	
	
	@Query(value = "select * from monan where ma_tinhtrangsudung=1",nativeQuery = true)
	public List<MonAn> getall();
	
	@Query(value = "SELECT  g.GIAMONAN_GIA " +
            "FROM giamonan g " +
            "JOIN dinhmucsoluong d ON g.DMKL_ID = d.DMSL_ID " +
            "WHERE g.MA_ID = :id " +
            "AND g.GIAMONAN_KETTHUC IS NULL " +
            "AND :sl BETWEEN d.DMSL_SLTU AND d.DMSL_SLDEN " +
            "LIMIT 1", 
    nativeQuery = true)
public  Float getGia(@Param("id") int id, @Param("sl") int sl);
}
