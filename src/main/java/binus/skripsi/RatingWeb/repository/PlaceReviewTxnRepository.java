package binus.skripsi.RatingWeb.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import binus.skripsi.RatingWeb.model.PlaceReviewTxn;

public interface PlaceReviewTxnRepository extends JpaRepository<PlaceReviewTxn, Long>{
	@Query(value = "select txn.id, c.name as c , p.name as p , u.username,txn.title " + 
			"from tbl_place_review_txn txn " + 
			"inner join tbl_place p on txn.place_id = p.id " + 
			"inner join tbl_category c on p.category_id = c.id " + 
			"inner join tbl_user u on txn.user_id = u.id " + 
			"where txn.status_cd=0", nativeQuery = true)
	ArrayList<Object[]> getPendingReviewList();
	
	@Query(value = "select txn.id, c.name as c, p.name as p, u.username, txn.rating_pelayanan, " + 
			"       txn.rating_suasana, txn.rating_kebersihan, txn.title, txn.review, txn.insert_dt " + 
			"from tbl_place_review_txn txn " + 
			"         inner join tbl_place p on txn.place_id = p.id " + 
			"         inner join tbl_category c on p.category_id = c.id" + 
			"         inner join tbl_user u on txn.user_id = u.id " + 
			"where txn.id = :id ", nativeQuery = true)
	List<Object> getPendingDetail(@Param("id") long id);
	
}
