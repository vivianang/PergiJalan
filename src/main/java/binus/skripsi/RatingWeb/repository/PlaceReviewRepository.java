package binus.skripsi.RatingWeb.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import binus.skripsi.RatingWeb.model.PlaceReview;

public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Long>{
	@Query(value = "SELECT pr.title, pr.review, pr.rating_kebersihan, pr.rating_suasana, pr.rating_pelayanan, u.username, pr.insert_dt ,'duration', pr.id" + 
			" FROM tbl_place_review pr" + 
			" INNER JOIN TBL_USER u on u.id = pr.user_id" +
			" where place_id = :id " +
			" order by pr.insert_dt desc" , 
			countQuery= "SELECT count(*) FROM tbl_place_review where place_id = :id",
			nativeQuery = true)
	Page<Object[]> getPlaceReviewByPlaceId(Pageable pageable, @Param("id") long id);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO TBL_PLACE_REVIEW (select id, place_id, user_id, title, rating_kebersihan, rating_suasana, " + 
			"       rating_pelayanan, '0', review, insert_dt " + 
			"from tbl_place_review_txn" + 
			" where id = :id)", nativeQuery = true)
	void insertReview(@Param("id") long id);
	
	@Query(value = "select count(*), round(avg(rating_kebersihan),1), round(avg(rating_suasana),1), round(avg(rating_pelayanan),1) " + 
			"from tbl_place_review " + 
			"where place_id=:id and is_del=0 " + 
			"group by place_id" , nativeQuery = true)
	List<Object>  getAvgStarAndCount(@Param("id") long id);
	
}
