package net.cabezudo.pokemon.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class DataLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String ip;

	@Column(name = "date", nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp timestamp;

	private String method;

	public DataLog(String ip, String method) {
		this.ip = ip;
		this.method = method;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
