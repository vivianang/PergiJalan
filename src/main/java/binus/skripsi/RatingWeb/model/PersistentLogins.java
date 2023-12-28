package binus.skripsi.RatingWeb.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "PERSISTENT_LOGINS")
public class PersistentLogins {

	private String series;
	private String username;
	private String token;
	private Date lastUsed;

	public PersistentLogins() {
	}

	public PersistentLogins(String series, String username, String token, Date lastUsed) {
		this.series = series;
		this.username = username;
		this.token = token;
		this.lastUsed = lastUsed;
	}

	@Id

	@Column(name = "SERIES", unique = true, nullable = false, length = 64)
	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	@Column(name = "USERNAME", nullable = false, length = 64)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "TOKEN", nullable = false, length = 64)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_USED", nullable = false, length = 7)
	public Date getLastUsed() {
		return this.lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

}
