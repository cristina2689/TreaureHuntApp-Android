package com.example.treasurehunt;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Game {
	
	@Id
	private String id;
	private String date;
	private String name;
	private int expirationTime;
	private int nrUseri;
	private String area; 

	public Game(){
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNrUseri() {
		return nrUseri;
	}

	public void setNrUseri(int nrUseri) {
		this.nrUseri = nrUseri;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(int expirationTime) {
		this.expirationTime = expirationTime;
	}
}

