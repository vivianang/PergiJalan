package binus.skripsi.RatingWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import binus.skripsi.RatingWeb.model.PlacePhotoTxn;
import binus.skripsi.RatingWeb.model.PlaceReviewPhotoTxn;

public interface PlaceReviewPhotoTxnRepository extends JpaRepository<PlaceReviewPhotoTxn, Long>{
	@Query(value = "SELECT *" + 
			" FROM tbl_place_review_photo_txn where place_review_txn_id = :id ", nativeQuery = true)
	List<PlaceReviewPhotoTxn>  getPendingReviewPhoto(@Param("id") long id);
}
