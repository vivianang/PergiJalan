package binus.skripsi.RatingWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import binus.skripsi.RatingWeb.model.PlacePhotoTxn;

public interface PlacePhotoTxnRepository extends JpaRepository<PlacePhotoTxn, Long>{
	@Query(value = "SELECT *" + 
			" FROM tbl_place_photo_txn where place_txn_id = :id ", nativeQuery = true)
	List<PlacePhotoTxn> getPlacePhotoTxnByPlaceTxnId(@Param("id") long id);
}