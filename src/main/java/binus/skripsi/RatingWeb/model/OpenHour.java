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
@Table(name = "TBL_OPEN_HOUR")
public class OpenHour {

	public OpenHour() {
		// TODO Auto-generated constructor stub
    	this.id = id;
    	this.place = place;
    	this.day = day;
    	this.openHour = openHour;
    	this.closedHour = closedHour;
    	this.isClosed = isClosed;
    	this.isDel = isDel;
	}

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "placeId", nullable = false)
    private Place place;
    
    private String day;
    
    @Column(name = "OPEN_HOUR")
    private String openHour;
    
    @Column(name = "CLOSED_HOUR")
    private String closedHour;
    
    @Column(name = "IS_CLOSED")
    private int isClosed;
    
    @Column(name = "IS_DEL")
    private int isDel;
}
