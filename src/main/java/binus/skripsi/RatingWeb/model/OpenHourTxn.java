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
@Table(name = "TBL_OPEN_HOUR_TXN")
public class OpenHourTxn {

	public OpenHourTxn() {
		// TODO Auto-generated constructor stub
    	this.id = id;
    	this.placeTxn = placeTxn;
    	this.day = day;
    	this.openHour = openHour;
    	this.closedHour = closedHour;
    	this.isClosed = isClosed;
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
    
    @Column(name = "OPEN_HOUR")
    private String openHour;
    
    @Column(name = "CLOSED_HOUR")
    private String closedHour;
    
    @Column(name = "IS_CLOSED")
    private int isClosed;
    
    @Column(name = "ACTION_TYPE")
    private int actionType;
    
    @Column(name = "STATUS_CD")
    private int statusCd;
}
