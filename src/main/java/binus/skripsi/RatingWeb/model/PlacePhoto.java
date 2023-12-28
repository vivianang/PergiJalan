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
@Table(name = "TBL_PLACE_PHOTO")
public class PlacePhoto {

	public PlacePhoto() {
		// TODO Auto-generated constructor stub
    	this.id = id;
    	this.place = place;
    	this.photoPath = photoPath;
    	this.photoName = photoName;
    	this.isDel = isDel;
	}

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "placeId", nullable = false)
    private Place place;
    
    @Column(name = "PHOTO_PATH")
    private String photoPath;
    
    @Column(name = "PHOTO_NAME")
    private String photoName;
    
    @Column(name = "IS_DEL")
    private int isDel;
}
