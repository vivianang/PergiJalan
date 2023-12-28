package binus.skripsi.RatingWeb.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import binus.skripsi.RatingWeb.model.PlaceTxn;

public interface PlaceTxnRepository extends JpaRepository<PlaceTxn, Long>{
	@Query(value = "SELECT distinct p.id, p.name, p.address, p.website, p.action_type " + 
			" FROM tbl_place_txn p " + 
			" left JOIN tbl_place_photo_txn pp on pp.place_txn_id = p.id " + 
			" WHERE (p.status_cd=0 and pp.id is null) " + 
			" or (p.status_cd=0 and pp.status_cd=0) ", nativeQuery = true)
	ArrayList<Object[]> getPendingAttractionData();

	@Query(value = "SELECT *" + 
			" FROM tbl_place_txn p" +  
			" INNER JOIN tbl_place_photo_txn pp on pp.place_id = p.id" +  
			" WHERE p.action_type = 0 and p.is_del=0 and pp.action_type = 0 and pp.is_del=0", nativeQuery = true)
	List<PlaceTxn> getAttractionData();
}
