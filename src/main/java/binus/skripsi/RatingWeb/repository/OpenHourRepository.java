package binus.skripsi.RatingWeb.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import binus.skripsi.RatingWeb.model.OpenHour;

public interface OpenHourRepository extends JpaRepository<OpenHour, Long>{
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO TBL_OPEN_HOUR (select id, place_txn_id, day, open_hour, closed_hour,is_closed ,'0'" + 
			" from TBL_OPEN_HOUR_TXN" + 
			" where place_txn_id = :id)", nativeQuery = true)
	void insertTblOpenHour(@Param("id") long id);
	
	@Query(value = "SELECT *" + 
			" FROM tbl_open_hour where place_id = :id ", nativeQuery = true)
	List<OpenHour> getOpenHourByPlaceId(@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE tbl_open_hour SET open_hour=:openHour, closed_hour=:closedHour, is_closed=:isClosed where day=:day and place_id=:placeId ", nativeQuery = true)
	void updateTblOpenHour(@Param("openHour") String openHour, @Param("closedHour") String closedHour, @Param("isClosed") int isClosed, @Param("day") String day, @Param("placeId") long placeId);
}