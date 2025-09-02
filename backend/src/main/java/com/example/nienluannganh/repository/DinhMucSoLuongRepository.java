package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.DinhMucSoLuong;

@Repository
public interface DinhMucSoLuongRepository extends JpaRepository<DinhMucSoLuong, Integer> {

}
