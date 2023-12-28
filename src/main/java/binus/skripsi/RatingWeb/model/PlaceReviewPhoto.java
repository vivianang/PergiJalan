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
@Table(name = "TBL_PLACE_REVIEW_PHOTO")
public class PlaceReviewPhoto {

	public PlaceReviewPhoto() {
		// TODO Auto-generated constructor stub
    	this.id = id;
    	this.placeReviewId = placeReviewId;
    	this.photoPath = photoPath;
    	this.photoName = photoName;
    	
	}

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "placeReviewId", nullable = false)
    private PlaceReview placeReviewId;
    
    @Column(name = "PHOTO_PATH")
    private String photoPath;
    
    @Column(name = "PHOTO_NAME")
    private String photoName;
}
