package com.example.nienluannganh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.ComBo;
import com.example.nienluannganh.model.MonAn;
import com.example.nienluannganh.objectcontroller.responseentity.response;

@Repository
public interface ComBoRepository extends JpaRepository<ComBo, Integer> {
	@Query(value = "SELECT * FROM combo WHERE lcb_id=:idloai AND cb_tinhtrangsudung=1",nativeQuery = true)
	public List<ComBo> getDsComBoByLoai(@Param("idloai")int idloai);
	@Query(value = "SELECT a.MA_ID,a.MA_TEN,c.CBMA_SOLUONG,c.CB_ID FROM monan a JOIN combo_ma c WHERE c.MA_ID=a.MA_ID AND c.CB_ID=:idcombo",nativeQuery = true)
	public List<Object> getDSMonAnById (@Param("idcombo") int id);
	
	@Query(value = "SELECT a.TU_ID,a.TU_TEN,c.CBTU_SOLUONG,c.CB_ID FROM thucuong a JOIN combo_tu c WHERE c.TU_ID=a.TU_ID AND c.CB_ID=:idcombo",nativeQuery = true)
	public List<Object> getDSThucUongById (@Param("idcombo") int id);
}
