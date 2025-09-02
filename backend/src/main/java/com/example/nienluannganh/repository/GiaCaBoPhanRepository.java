package com.example.nienluannganh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.BoPhan;
import com.example.nienluannganh.model.GiaCaBoPhan;

@Repository
public interface GiaCaBoPhanRepository extends JpaRepository<GiaCaBoPhan, Integer>{
	@Query(value = "SELECT * FROM giacabophan WHERE CA_ID =:caid AND BP_ID=:bpid AND GBP_KETTHUC IS NULL",nativeQuery = true)
	public GiaCaBoPhan gett(@Param("caid") int caid,@Param("bpid") int bpid);
}
