package com.hackerrank.sample.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 8730740438268478616L;

	@Id
	private String id;
	private Long dateCreated;
	private Long dateModified;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getDateModified() {
		return dateModified;
	}

	public void setDateModified(Long dateModified) {
		this.dateModified = dateModified;
	}

	@PrePersist
	public void prePersist() {
		if (this.id == null) {
			this.id = UUID.randomUUID().toString();
		} 
		this.dateCreated = new Date().getTime();
		this.dateModified = new Date().getTime();
	}

	@PreUpdate
	public void preUpdate() {
		this.dateModified = new Date().getTime();
	}

}
