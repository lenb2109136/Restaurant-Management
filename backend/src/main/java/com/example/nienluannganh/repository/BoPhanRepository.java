package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.BoPhan;

@Repository
public interface BoPhanRepository extends JpaRepository<BoPhan, Integer>{

}
