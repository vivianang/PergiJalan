package binus.skripsi.RatingWeb.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import binus.skripsi.RatingWeb.dto.ExploreMoreList;
import binus.skripsi.RatingWeb.model.Place;
import binus.skripsi.RatingWeb.model.PlacePhotoTxn;

public interface PlaceRepository extends JpaRepository<Place, Long>{
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO TBL_PLACE (select id, name, address, description, website, category_id, '0', insert_dt, ''" + 
			" from tbl_place_txn" + 
			" where id = :id)", nativeQuery = true)
	void insertTblPlace(@Param("id") long id);

	@Query(value = "select a, b, c, (select photo_name from tbl_place_photo where place_id = a order by id limit 1)," + 
			" (select photo_path from tbl_place_photo where place_id = a order by id limit 1)" + 
			" from (SELECT p.id as a, name as b, address as c" + 
			" 	FROM tbl_place p" + 
			" 	left join tbl_place_photo tpp on p.id = tpp.place_id" + 
			" 	where category_id = :id" + 
			" 	and p.is_del = 0" + 
			" 	and (tpp.is_del = 0 or tpp.id is null)" + 
			" 	group by p.id, name, address) d", 
			countQuery= "SELECT count(*) FROM tbl_place p where category_id = :id and p.is_del=0",
			nativeQuery = true)
	Page<Object[]> getAttractionByCategory(Pageable pageable, @Param("id") long id);
	
	@Query(value = "select tp.id, tp.name, (select photo_name from tbl_place_photo where place_id = tp.id order by id limit 1), " + 
			"       (select photo_path from tbl_place_photo where place_id = tp.id order by id limit 1), " + 
			"       (pr.rating_pelayanan + pr.rating_suasana + pr.rating_kebersihan)/3 as c " + 
			"from tbl_place tp " + 
			"left join tbl_place_review pr on tp.id = pr.place_id " + 
			"where category_id=4 " + 
			"order by c desc " + 
			"limit 4",
			nativeQuery = true)
	ArrayList<Object[]> getTamanHiburanPlace();
	
	@Query(value = "select tp.id, tp.name, (select photo_name from tbl_place_photo where place_id = tp.id order by id limit 1), " + 
			"       (select photo_path from tbl_place_photo where place_id = tp.id order by id limit 1) " + 
			"from tbl_place tp " + 
			"order by tp.insert_dt desc " + 
			"limit 4",
			nativeQuery = true)
	ArrayList<Object[]> getNewAttraction();
	
//	@Transactional
//	@Modifying
//	@Query(value = "REPLACE INTO tbl_place_txn VALUES (1, ‘Old’, ‘2014–08–20 18:47:00’) ", nativeQuery = true)
//	void updateTblPlace(@Param("id") long id);
	
//	@Query(value = "SELECT * " + 
//			" FROM tbl_place p" +
//			" where p.category_id = :id and p.is_del=0", nativeQuery = true)
//	Page<Place> getAttractionByCategory(Pageable pageable, long id);
	
//	Page<Place> findAll(Pageable pageable);
}