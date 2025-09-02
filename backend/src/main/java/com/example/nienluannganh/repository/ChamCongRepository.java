package com.example.nienluannganh.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.DTO.ChamCongDTO;
import com.example.nienluannganh.model.ChamCong;

@Repository
public interface ChamCongRepository extends JpaRepository<ChamCong, Integer>{
	 @Query(value = "SELECT new com.example.nienluannganh.DTO.ChamCongDTO(nv.id,nv,cv.chucVu,cv.boPhan,g,p,p.ca) " +
	            "FROM ThoiGianLamCa p " +
	            "JOIN NhanVien nv ON nv.id = p.nhanVien.id " +
	            "JOIN ThoiGianChucVu cv ON cv.nhanVien.id = nv.id AND " +
	            "     (p.ngaylam BETWEEN cv.batDau AND cv.ketThuc " +
	            "     OR (p.ngaylam >= cv.batDau AND cv.ketThuc IS NULL)) " +
	            "JOIN GiaCaBoPhan g ON g.ca.id = p.ca.id " +
	            "WHERE (p.ngaylam BETWEEN :batDau AND :ketThuc or (:batDau is null or :ketThuc is NULL))" +
	            "AND (p.ngaylam BETWEEN g.BatDau AND g.KetThuc " +
	            "     OR (p.ngaylam >= g.BatDau AND g.KetThuc IS NULL)) " +
	            "AND cv.boPhan.id = g.boPhan.id " +
	            "AND cv.chucVu.id = g.chucVu.id " +
	            "AND (p.batDau IS NOT NULL or p.ngaylam>=:batDau)")
	    public List<ChamCongDTO> findCaLamValid(@Param("batDau") LocalDate batDau, @Param("ketThuc") LocalDate ketThuc);
	 

	    @Query("select count(p.id) from ThoiGianLamCa p where p.id in:ids and p.ketThuc is not null and p.daChamCong =0")
	    public Integer countAllChuaChamCong(@Param("ids") List<Integer> ids);

	    @Modifying
	    @Query("update ThoiGianLamCa p set p.daChamCong=1 where p.id in:ids and p.daChamCong=0")
	    public Integer chamCongAll(@Param("ids") List<Integer> ids);
}
