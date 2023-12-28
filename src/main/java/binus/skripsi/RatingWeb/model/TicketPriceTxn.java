package binus.skripsi.RatingWeb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_TICKET_PRICE_TXN")
public class TicketPriceTxn {

	public TicketPriceTxn() {
		// TODO Auto-generated constructor stub
    	this.id = id;
    	this.placeTxn = placeTxn;
    	this.day = day;
    	this.ticketPrice = ticketPrice;
    	this.actionType = actionType;
    	this.statusCd = statusCd;
	}

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "placeTxnId", nullable = false)
    private PlaceTxn placeTxn;
    
    private String day;
    
    @Column(name = "TICKET_PRICE")
    private String ticketPrice;
    
    @Column(name = "ACTION_TYPE")
    private int actionType;
    
    @Column(name = "STATUS_CD")
    private int statusCd;
}
