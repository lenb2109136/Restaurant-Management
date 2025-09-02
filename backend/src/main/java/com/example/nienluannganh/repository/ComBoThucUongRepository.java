package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.ComBoThucUong;
import com.example.nienluannganh.model.embededid.ComBoThucUongId;

@Repository
public interface ComBoThucUongRepository extends JpaRepository<ComBoThucUong, ComBoThucUongId>{

}
