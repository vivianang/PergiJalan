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
@Table(name = "TBL_PLACE_TXN")
public class PlaceTxn {

	public PlaceTxn() {
		// TODO Auto-generated constructor stub
    	this.id = id;
    	this.name = name;
    	this.user = user;
    	this.address = address;
    	this.description = description;
    	this.website = website;
    	this.category = category;
    	this.actionType = actionType;
    	this.statusCd = statusCd;
    	this.insertDt = insertDt;
    	this.placeId = placeId;
	}

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String address;
    private String description;
    private String website;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", nullable = false)
    private Category category;
    
    @Column(name = "ACTION_TYPE")
    private int actionType;
    
    @Column(name = "STATUS_CD")
    private int statusCd;
    
    @Column(name = "INSERT_DT")
    private String insertDt;
    
    @Column(name = "PLACE_ID")
    private long placeId;
}
