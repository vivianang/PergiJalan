package binus.skripsi.RatingWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import binus.skripsi.RatingWeb.model.TicketPriceTxn;

public interface TicketPriceTxnRepository extends JpaRepository<TicketPriceTxn, Long>{
	@Query(value = "SELECT *" + 
			" FROM TBL_TICKET_PRICE_TXN where PLACE_TXN_ID = :id ", nativeQuery = true)
	List<TicketPriceTxn> getTicketPriceTxnByPlaceTxnId(@Param("id") long id);
}