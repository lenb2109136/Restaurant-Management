package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.KhuyenMaiThongThuong;

@Repository
public interface KhuyenMaiThongThuongRepository extends JpaRepository<KhuyenMaiThongThuong, Integer>{
		
}
