package com.example.nienluannganh.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.Ca;
import com.example.nienluannganh.model.ThoiGianLamCa;

import jakarta.transaction.Transactional;

@Repository
public interface ThoiGianLamCaRepository extends JpaRepository<ThoiGianLamCa, Integer>{
	@Query(value = "SELECT * FROM thoigianlamca WHERE TGLC_NGAYLAM >= :nb AND TGLC_NGAYLAM <= :nk AND NV_ID=:id ORDER BY TGLC_NGAYLAM ASC",nativeQuery = true)
	public List<ThoiGianLamCa> getcanhanvien(
			@Param("nb") LocalDate batdau,
			@Param("nk") LocalDate ketthuc,
			@Param("id") int id
			);
	
	@Query(value = "SELECT * FROM thoigianlamca WHERE nv_id=:idnv AND ca_id=:idca AND tglc_ngaylam=:nl", nativeQuery =true)
	public ThoiGianLamCa kiemtraca(
			@Param("nl") LocalDate nl,
			@Param("idnv") int id,
			@Param("idca") int idca
			);
@Query(value = "SELECT * FROM thoigianlamca WHERE nv_id=:idnv AND ca_id=:idca AND tglc_ngaylam=:ngaylam", nativeQuery = true)
public ThoiGianLamCa get(int idnv, int idca, LocalDate ngaylam);
@Modifying
@Transactional
@Query(value = "DELETE FROM thoigianlamca WHERE nv_id = :nv AND tglc_ngaylam >= :nlbd AND tglc_ngaylam <= :nlkt", nativeQuery = true)
void xoacalam(@Param("nv") int idnv, @Param("nlbd") LocalDate nl, @Param("nlkt") LocalDate nlkt);

@Query(value = "SELECT b.CV_TEN,c.ca_ID, COUNT(c.ca_ID) AS tong FROM thoigianlamca t JOIN nhanvien nv ON t.NV_ID=nv.NV_ID JOIN chucvu \r\n"
		+ "b ON b.CV_ID=nv.CV_ID JOIN ca c ON c.ca_ID=t.CA_ID WHERE tglc_ngaylam=:nl  GROUP BY b.CV_TEN ORDER BY c.ca_ID",nativeQuery = true)
public List<Map<Object, Object>> getdstuan(@Param("nl") LocalDate ngayLam);

@Query(value = "SELECT c.CV_TEN,c.CV_ID,COALESCE(cv.sl, 0) AS tong \r\n"
		+ "FROM chucvu c\r\n"
		+ "left JOIN (SELECT n.CV_ID,COUNT(*) AS sl\r\n"
		+ "FROM  thoigianlamca t\r\n"
		+ "JOIN nhanvien n ON n.NV_ID=t.NV_ID \r\n"
		+ "WHERE t.TGLC_NGAYLAM=:nl AND t.CA_ID=:idca\r\n"
		+ "GROUP BY (n.CV_ID)\r\n"
		+ " ) AS cv ON cv.CV_ID=c.CV_ID",nativeQuery = true)
public List<Map<Object, Object>> getcangay(@Param("nl") LocalDate ngayLam, @Param("idca") int idCa);

@Query(value="SELECT nv.NV_TEN,nv.NV_ID FROM thoigianlamca t JOIN nhanvien nv "
		+ "ON nv.NV_ID=t.NV_ID where t.CA_ID=:idca AND t.TGLC_NGAYLAM=:nl AND nv.CV_ID=:idcv",nativeQuery = true)
public List<Map<Object, Object>> getnhanvien(int idca, LocalDate nl, int idcv);

@Query(value="SELECT NV_TEN,NV_ID FROM nhanvien WHERE CV_ID=:idcv AND NV_ID NOT IN (SELECT nv.NV_ID FROM thoigianlamca t JOIN nhanvien nv ON nv.NV_ID=t.NV_ID where t.CA_ID=:idca AND t.TGLC_NGAYLAM=:nl AND nv.CV_ID=:idcv)",nativeQuery = true)
public List<Map<Object, Object>> getnhanviennotin(int idca, LocalDate nl, int idcv);

@Query(value = "SELECT * FROM thoigianlamca WHERE NV_ID=:idnv AND TGLC_NGAYLAM=:nl AND CA_ID=:idca",nativeQuery = true)
public ThoiGianLamCa getthoigianlamca(int idnv,LocalDate nl,int idca );
// tấn 
@Query("SELECT p from ThoiGianLamCa p where p.ca.id in (select c.id  from Ca c where now() between c.ThoiGianBatDau and c.ThoiGianKetThuc) and CURDATE()=p.ngaylam and p.batDau is null ")
List<ThoiGianLamCa> getAllThoiGianLamCaNow();

@Query("SELECT p from ThoiGianLamCa p where p.ca.id in (select c.id  from Ca c where now() between c.ThoiGianBatDau and c.ThoiGianKetThuc) and CURDATE()=p.ngaylam and p.nhanVien.id=:userId and p.batDau is null")
Optional<ThoiGianLamCa> getPhanCongByUserIdAndNow(@Param("userId") Integer userId);

@Query("SELECT p from ThoiGianLamCa p where CURDATE()=p.ngaylam and p.nhanVien.id=:userId and p.ketThuc is null and p.batDau is not null")
Optional<ThoiGianLamCa> getEndPhanCongByUserIdAndNow(@Param("userId") Integer userId);

@Query("select p from ThoiGianLamCa p where p.ngaylam=:date and p.ca.id=:caId and p.batDau is not null")
List<ThoiGianLamCa> getPhanCongDaChamCong(@Param("date") LocalDate date, @Param("caId") Integer caId);

@Query("select p from ThoiGianLamCa p where p.ngaylam=:date and p.ca.id=:caId and p.batDau is null")
List<ThoiGianLamCa> getPhanCongChuaChamCong(@Param("date") LocalDate date, @Param("caId") Integer caId);

@Query(value = "SELECT * FROM thoigianlamca WHERE NV_ID=:idnv AND CA_ID=:caid AND TGLC_NGAYLAM=:thoigian",nativeQuery = true)
public ThoiGianLamCa huhu(int idnv, int caid, LocalDate thoigian);

}

