package binus.skripsi.RatingWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import binus.skripsi.RatingWeb.model.TicketPrice;

public interface TicketPriceRepository extends JpaRepository<TicketPrice, Long>{
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO TBL_TICKET_PRICE (select id, place_txn_id, day, ticket_price ,'0'" + 
			" from TBL_TICKET_PRICE_TXN" + 
			" where place_txn_id = :id)", nativeQuery = true)
	void insertTblTicketPrice(@Param("id") long id);
	
	@Query(value = "SELECT *" + 
			" FROM TBL_TICKET_PRICE where PLACE_ID = :id ", nativeQuery = true)
	List<TicketPrice> getTicketPriceByPlaceId(@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE TBL_TICKET_PRICE SET ticket_price=:ticketPrice where day=:day and place_id=:placeId ", nativeQuery = true)
	void updateTblTicketPrice(@Param("ticketPrice") String ticketPrice, @Param("day") String day, @Param("placeId") long placeId);
}