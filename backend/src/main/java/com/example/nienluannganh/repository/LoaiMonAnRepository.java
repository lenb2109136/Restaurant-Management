package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.LoaiMonAn;

@Repository
public interface LoaiMonAnRepository extends JpaRepository<LoaiMonAn, Integer>{

}
