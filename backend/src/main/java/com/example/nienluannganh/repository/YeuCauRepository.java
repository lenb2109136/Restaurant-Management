package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.YeuCau;
@Repository
public interface YeuCauRepository extends JpaRepository<YeuCau, Integer> {

}
