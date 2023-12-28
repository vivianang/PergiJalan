package binus.skripsi.RatingWeb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_CATEGORY")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    private String name;
    
    public Category() {
		// TODO Auto-generated constructor stub
    	this.id=id;
    	this.name = name;
	}
}
