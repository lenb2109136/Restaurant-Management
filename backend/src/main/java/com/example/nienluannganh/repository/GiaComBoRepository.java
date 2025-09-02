package com.example.nienluannganh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.GiaComBo;
import com.example.nienluannganh.model.GiaMonAn;

@Repository
public interface GiaComBoRepository extends JpaRepository<GiaComBo, Integer>{
	
	
	@Query(value = "SELECT * FROM giacombo WHERE CB_ID=:id AND gcb_ketthuc IS null ORDER BY gcb_gia desc", nativeQuery = true)
	public List<GiaComBo> getgia(@Param("id") int id);
	

}
