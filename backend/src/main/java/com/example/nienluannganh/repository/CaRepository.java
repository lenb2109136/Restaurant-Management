package com.example.nienluannganh.repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nienluannganh.model.Ca;
import com.example.nienluannganh.model.GiaCaBoPhan;

@Repository
public interface CaRepository extends JpaRepository<Ca, Integer>{
	@Query(value = "SELECT * FROM ca WHERE ( ca_thoigianbatdau >= :bd AND  ca_thoigianbatdau <= :kt) OR ( CA_THOIGIANKETTHUC >=:bd  AND  ca_thoigianketthuc <= :kt)\r\n"
			+ "OR ( :bd >= ca_thoigianbatdau AND  :bd <= CA_THOIGIANKETTHUC) OR ( :kt >= ca_thoigianbatdau  AND  :kt <= CA_THOIGIANKETTHUC) OR ( ca_thoigianbatdau >= :bd   AND  ca_thoigianbatdau >= :kt AND :kt<=:bd)"
			+ " OR ( ca_thoigianbatdau <= :bd   AND  ca_thoigianbatdau <= :kt AND :kt<=:bd)"
			+ "", nativeQuery = true)
	public List<Ca> kt (@Param("bd") String bd,@Param("kt") String kt);
	
	@Query(value = "SELECT * FROM ca WHERE ca_consudung=true", nativeQuery = true)
	public List<Ca> getall();
	
	@Query(value = "SELECT * FROM ca WHERE ca_consudung=true and ca_id=:id", nativeQuery = true)
	public Ca getbyId(@Param("id") int id);
	
	@Query(value = "SELECT c.ca_ID,c.CA_THOIGIANBATDAU,c.CA_THOIGIANKETTHUC, COUNT(n.NV_GIOITINHNAM) AS soluong,n.NV_GIOITINHNAM AS gioitinh\r\n"
			+ "FROM ca c \r\n"
			+ "JOIN thoigianlamca t ON c.ca_ID = t.CA_ID \r\n"
			+ "JOIN nhanvien n ON n.NV_ID=t.NV_ID\r\n"
			+ "WHERE t.TGLC_KETTHUC IS NULL \r\n"
			+ "GROUP BY c.ca_ID,n.NV_GIOITINHNAM;",nativeQuery = true)
	public List<Map<Object, Object>> thongkeca();
	
	
	@Query(value = "SELECT c.ca_ID,c.CA_THOIGIANBATDAU, c.CA_THOIGIANKETTHUC, n.NV_TEN, t.TGLC_ID, b.BP_TEN FROM ca c \r\n"
			+ "JOIN thoigianlamca t ON c.ca_ID=t.CA_ID \r\n"
			+ "JOIN nhanvien n ON n.NV_ID=t.NV_ID \r\n"
			+ "JOIN bophan b ON b.BP_ID=n.BP_ID\r\n"
			+ "WHERE t.TGLC_KETTHUC IS NULL ORDER BY c.ca_ID ASC",nativeQuery = true)
	public List<Map<Object, Object>> getdsnvhientai();
	
	
	@Query(value = "SELECT g.GBP_ID,b.BP_TEN,c.CV_TEN,g.GBP_GIA,j.CA_THOIGIANKETTHUC,j.CA_THOIGIANBATDAU FROM giacabophan g JOIN bophan b ON g.BP_ID=b.BP_ID JOIN chucvu c ON c.CV_ID=g.CV_ID JOIN ca j ON j.ca_ID=g.CA_ID WHERE g.GBP_KETTHUC IS NULL\r\n"
			+ "ORDER BY b.BP_TEN, c.CV_TEN\r\n"
			+ "",nativeQuery = true)
	public List<Map<Object, Object>> getdsnow();
	
	@Query(value = "(SELECT c.ca_ID,c.CA_THOIGIANBATDAU,c.CA_THOIGIANKETTHUC,t.TGLC_ID FROM nhanvien n JOIN thoigianlamca t ON n.NV_ID=t.NV_ID JOIN ca c ON c.ca_ID=t.CA_ID WHERE n.NV_ID=:id )\r\n"
			+ "\r\n"
			+ "UNION\r\n"
			+ "\r\n"
			+ "( (SELECT c.ca_ID,c.CA_THOIGIANBATDAU,c.CA_THOIGIANKETTHUC,t.TGLC_ID FROM ca c left JOIN thoigianlamca t ON c.ca_ID=t.CA_ID WHERE c.ca_ID NOT IN (SELECT c.ca_ID FROM nhanvien n JOIN thoigianlamca t ON n.NV_ID=t.NV_ID JOIN ca c ON c.ca_ID=t.CA_ID WHERE n.NV_ID=:id )))\r\n"
			+ "", nativeQuery = true)
	public List<Map<Object, Object>> calamhientai(@Param("id") int id);
	
	
	@Query(value = "select c from Ca c where c.id NOT IN :dsint")
	public List<Ca> getnotin(@Param("dsint") List<Integer> dsint);
	
}
