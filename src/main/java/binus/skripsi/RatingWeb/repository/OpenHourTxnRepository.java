package binus.skripsi.RatingWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import binus.skripsi.RatingWeb.model.OpenHourTxn;

public interface OpenHourTxnRepository extends JpaRepository<OpenHourTxn, Long>{
	@Query(value = "SELECT *" + 
			" FROM tbl_open_hour_txn where place_txn_id = :id ", nativeQuery = true)
	List<OpenHourTxn> getOpenHourTxnByPlaceTxnId(@Param("id") long id);
}