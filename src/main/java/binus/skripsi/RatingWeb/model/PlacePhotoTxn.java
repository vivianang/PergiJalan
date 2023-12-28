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
@Table(name = "TBL_PLACE_PHOTO_TXN")
public class PlacePhotoTxn {

	public PlacePhotoTxn() {
		// TODO Auto-generated constructor stub
    	this.id = id;
    	this.placeTxn = placeTxn;
    	this.photoPath = photoPath;
    	this.photoName = photoName;
    	this.statusCd = statusCd;
	}

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "placeTxnId", nullable = false)
    private PlaceTxn placeTxn;
    
    @Column(name = "PHOTO_PATH")
    private String photoPath;
    
    @Column(name = "PHOTO_NAME")
    private String photoName;
    
    @Column(name = "STATUS_CD")
    private int statusCd;
    
    @Column(name = "ACTION_TYPE")
    private int actionType;
}
