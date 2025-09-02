package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.DonViTinh;

@Repository
public interface DonViTinhRepository extends JpaRepository<DonViTinh, Integer> {

}
