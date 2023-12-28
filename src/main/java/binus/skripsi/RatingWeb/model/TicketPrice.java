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
@Table(name = "TBL_TICKET_PRICE")
public class TicketPrice {

	public TicketPrice() {
		// TODO Auto-generated constructor stub
    	this.id = id;
    	this.place = place;
    	this.day = day;
    	this.ticketPrice = ticketPrice;
    	this.isDel = isDel;
	}

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "placeId", nullable = false)
    private Place place;
    
    private String day;
    
    @Column(name = "TICKET_PRICE")
    private String ticketPrice;
    
    @Column(name = "IS_DEL")
    private int isDel;
}
