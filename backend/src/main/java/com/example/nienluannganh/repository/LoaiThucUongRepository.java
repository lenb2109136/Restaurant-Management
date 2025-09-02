package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.LoaiThucUong;

@Repository
public interface LoaiThucUongRepository extends JpaRepository<LoaiThucUong, Integer>{

}
