package binus.skripsi.RatingWeb.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import binus.skripsi.RatingWeb.model.PlacePhotoTxn;
import binus.skripsi.RatingWeb.model.PlaceReviewPhoto;
import binus.skripsi.RatingWeb.model.PlaceReviewPhotoTxn;

public interface PlaceReviewPhotoRepository extends JpaRepository<PlaceReviewPhoto, Long>{
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO TBL_PLACE_REVIEW_PHOTO (select id, place_review_txn_id, photo_path, photo_name" + 
			" from TBL_PLACE_REVIEW_PHOTO_txn" + 
			" where place_review_txn_id = :id)", nativeQuery = true)
	void insertReviewPhoto(@Param("id") long id);
	
	@Query(value = "select prp.* " + 
			"from tbl_place_review_photo prp " + 
			"inner join tbl_place_review pr on prp.place_review_id = pr.id " + 
			"where pr.place_id=:id ", nativeQuery = true)
	List<PlaceReviewPhoto>  getReviewPhoto(@Param("id") long id);
}
