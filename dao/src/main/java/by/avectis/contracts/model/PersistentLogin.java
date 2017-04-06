package by.avectis.contracts.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="PERSISTENT_LOGINS")
public class PersistentLogin implements Serializable{

	@Id
	private String series;

	@Column(name="WORKERNAME", unique=true, nullable=false)
	private String workername;
	
	@Column(name="TOKEN", unique=true, nullable=false)
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_used;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUsername() {
		return workername;
	}

	public void setUsername(String workername) {
		this.workername = workername;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLast_used() {
		return last_used;
	}

	public void setLast_used(Date last_used) {
		this.last_used = last_used;
	}
	
	
}
