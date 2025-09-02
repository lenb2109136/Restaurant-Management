package com.example.nienluannganh.service;

import org.springframework.data.jpa.domain.Specification;

import com.example.nienluannganh.model.MonAn;

public class monAnSpectification {
	public static Specification<MonAn> hasName(String name) {
	    return (root, query, cb) -> {
	        if (name == null || name.trim().isEmpty()) {
	            return null; // không lọc nếu chuỗi null hoặc rỗng
	        }
	        return cb.like(cb.lower(root.get("ten")), "%" + name.toLowerCase() + "%");
	    };
	}

}
