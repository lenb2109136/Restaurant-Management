package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.KhuyenMaiGioVang;

@Repository
public interface KhuyenMaiGioVangRepository extends JpaRepository<KhuyenMaiGioVang,Integer> {

}
