package binus.skripsi.RatingWeb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    private String username;
    private String email;
    private String password;
    
    public User() {
		// TODO Auto-generated constructor stub
    	this.id=id;
    	this.username = username;
    	this.email = email;
    	this.password = password;
	}
}
