package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.Sanh;
@Repository
public interface SanhRepository extends JpaRepository<Sanh, Integer> {

}
