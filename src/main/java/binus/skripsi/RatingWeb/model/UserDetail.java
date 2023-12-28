package binus.skripsi.RatingWeb.model;

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
@Table(name = "TBL_USER_DETAIL")
public class UserDetail {

    public UserDetail() {
		// TODO Auto-generated constructor stub
    	this.id = id;
    	this.contact = contact;
    	this.address = address;
    	this.user = user;
    	this.userRole = userRole;
    	this.name = name;
	}

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String contact;
    private String address;
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userRoleId", nullable = false)
    private UserRole userRole;
}
