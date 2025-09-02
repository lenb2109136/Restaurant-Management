package com.example.nienluannganh.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.Ca;
import com.example.nienluannganh.model.NhanVien;
import com.example.nienluannganh.model.ThoiGianChucVu;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
	
//	@Query(value="SELECT  t.BP_ID fROM thoigianchucvu t JOIN nhanvien nv ON t.NV_ID=nv.NV_ID  WHERE nv.NV_ID=24 AND t.TGCV_KETTHUC IS NULL",nativeQuery = true)
//	
	
	@Query(value = "SELECT  t.* fROM thoigianchucvu t JOIN nhanvien nv ON t.NV_ID=nv.NV_ID  WHERE nv.NV_SDT=:id AND t.TGCV_KETTHUC IS NULL", nativeQuery = true)
	public Optional<ThoiGianChucVu> getNhanVienById(@Param("id") String id);
	@Query(value = "SELECT * FROM nhanvien WHERE BP_ID=:id ORDER BY BP_ID, CV_ID ASC",nativeQuery = true)
	public List<NhanVien> getnhanvienbybophan(@Param("id") int id);
	@Query(value = "SELECT * FROM nhanvien WHERE LOWER(NV_TEN) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY BP_ID, CV_ID ASC", nativeQuery = true)
	public List<NhanVien> getNhanVienByname(@Param("name") String name);
	@Query(value = "SELECT * FROM nhanvien WHERE BP_ID = :id AND LOWER(NV_TEN) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY BP_ID, CV_ID ASC", nativeQuery = true)
	public List<NhanVien> huhu(@Param("id") int id, @Param("name") String name);
	
	@Query(value = "SELECT * FROM nhanvien ORDER BY BP_ID, CV_ID ASC",nativeQuery = true)
	public List<NhanVien> getall();
	
	@Query(value = "SELECT c.NV_ID,c.CA_ID, COUNT(c.CA_ID) AS tong, f.CA_THOIGIANBATDAU  FROM thoigianlamca c JOIN ca f ON f.ca_ID =c.CA_ID where c.tglc_ngaylam>=:batdau and c.tglc_ngaylam<=:ketthuc and c.nv_id=:id GROUP BY c.NV_ID,c.CA_ID  ORDER BY c.ca_id asc",nativeQuery = true)
	public List<Map<Object, Object>> getcalamtrongtuan(
			@Param("id") int id,
			@Param("batdau") LocalDate batdau,
			@Param("ketthuc") LocalDate ketthuc
			);
	@Query(value = "SELECT * FROM nhanvien \r\n"
			+ "WHERE (bp_id =:bp_all) \r\n"
			+ "AND (cv_id = :cv_all);\r\n"
			+ "", nativeQuery = true)
	public List<NhanVien> g(@Param("bp_all") int bp_id,@Param("cv_all") int cv_id );
	@Query(value = "SELECT * FROM nhanvien \r\n"
			+ "WHERE (bp_id =:bp_all) \r\n"
			+ "", nativeQuery = true)
	public List<NhanVien> g2(@Param("bp_all") int bp_id);
	
	@Query(value = "select n from NhanVien n where n.id NOT IN :dsint")
	public List<NhanVien> getnotin(@Param("dsint") List<Integer> ds);
}
