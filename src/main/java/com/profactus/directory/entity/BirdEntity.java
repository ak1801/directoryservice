package com.profactus.directory.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BIRD")
public class BirdEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6825908051763709118L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "FAMILY", nullable = false)
	private String family;
	
	@Column(name = "CONTINENTS", nullable = false)
	private String continents;
	
	@Column(name = "ADDED_ON", nullable = false)
	private Date added;
	
	@Column(name = "VISIBLE", nullable = false)
	private boolean visible;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getContinents() {
		return continents;
	}

	public void setContinents(String continents) {
		this.continents = continents;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
