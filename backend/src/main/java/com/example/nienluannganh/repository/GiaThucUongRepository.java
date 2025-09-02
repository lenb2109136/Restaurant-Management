package com.example.nienluannganh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.GiaMonAn;
import com.example.nienluannganh.model.GiaThucUong;

@Repository
public interface GiaThucUongRepository extends JpaRepository<GiaThucUong, Integer>{
	@Query(value = "SELECT * FROM giathucuong WHERE TU_ID=:id AND GTU_KETTHUC IS null ORDER BY GTU_GIA desc", nativeQuery = true)
	public List<GiaThucUong> getgia(@Param("id") int id);
}
