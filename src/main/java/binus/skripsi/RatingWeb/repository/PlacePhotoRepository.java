package binus.skripsi.RatingWeb.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import binus.skripsi.RatingWeb.model.PlacePhoto;

public interface PlacePhotoRepository extends JpaRepository<PlacePhoto, Long>{
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO TBL_PLACE_PHOTO (select id, place_txn_id, photo_path, photo_name ,'0'" + 
			" from tbl_place_photo_txn" + 
			" where place_txn_id = :id)", nativeQuery = true)
	void insertTblPlacePhoto(@Param("id") long id);
	
	@Query(value = "SELECT *" + 
			" FROM tbl_place_photo where place_id = :id ", nativeQuery = true)
	List<PlacePhoto> getPlacePhotoByPlaceId(@Param("id") long id);
	
	@Query(value = "select photo_path, photo_name " + 
			"from tbl_place_review_photo " + 
			"inner join skripsi.tbl_place_review tpr on tbl_place_review_photo.place_review_id = tpr.id " + 
			"where tpr.place_id=:id " + 
			"union all " + 
			"select photo_path, photo_name " + 
			"from tbl_place_photo where place_id=:id  ",
			countQuery= "select sum(a) from (select count(*) as a " + 
					"      from tbl_place_review_photo prp " + 
					"               inner join skripsi.tbl_place_review tpr on prp.place_review_id = tpr.id " + 
					"      where tpr.place_id=:id " + 
					"      union all " + 
					"      select count(*) as a " + 
					"      from tbl_place_photo pp " + 
					"      where place_id=:id) as c",
			nativeQuery = true)
	Page<Object[]> getAllPhoto(Pageable pageable, @Param("id") long id);
}