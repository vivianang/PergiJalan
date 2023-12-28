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
@Table(name = "TBL_PLACE")
public class Place {

	public Place() {
		// TODO Auto-generated constructor stub
    	this.id = id;
    	this.name = name;
    	this.address = address;
    	this.description = description;
    	this.website = website;
    	this.category = category;
    	this.isDel = isDel;
    	this.insertDt = insertDt;
    	this.updateDt = updateDt;
    	
	}

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String address;
    private String description;
    private String website;
   
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", nullable = false)
    private Category category;
    
    @Column(name = "IS_DEL")
    private int isDel;
    
    @Column(name = "INSERT_DT")
    private String insertDt;
    
    @Column(name = "UPDATE_DT")
    private String updateDt;
}
