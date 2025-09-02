package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.ComBo;
import com.example.nienluannganh.model.ComBoMonAn;
import com.example.nienluannganh.model.embededid.ComBoMonAnId;

@Repository
public interface ComBoMonAnRepository extends JpaRepository<ComBoMonAn, ComBoMonAnId>{

}
