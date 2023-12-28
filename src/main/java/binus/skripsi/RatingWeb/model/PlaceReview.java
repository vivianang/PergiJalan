package binus.skripsi.RatingWeb.model;

import java.sql.Date;

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
@Table(name = "TBL_PLACE_REVIEW")
public class PlaceReview {

	public PlaceReview() {
		// TODO Auto-generated constructor stub
    	this.id = id;
    	this.place = place;
    	this.user = user;
    	this.title = title;
    	this.review = review;
    	this.ratingKebersihan = ratingKebersihan;
    	this.ratingPelayanan = ratingPelayanan;
    	this.ratingSuasana = ratingSuasana;
    	this.isDel = isDel;
    	this.insertDt = insertDt;
    	
	}

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "placeId", nullable = false)
    private Place place;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
    private User user;
    
    private String title;
    
    private String review;
    
    @Column(name = "RATING_KEBERSIHAN")
    private int ratingKebersihan;
    
    @Column(name = "RATING_PELAYANAN")
    private int ratingPelayanan;
    
    @Column(name = "RATING_SUASANA")
    private int ratingSuasana;
    
    @Column(name = "IS_DEL")
    private int isDel;
    
    @Column(name = "INSERT_DT")
    private String insertDt;
}
