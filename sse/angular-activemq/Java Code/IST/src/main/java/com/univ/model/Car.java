package com.univ.model;

import javax.persistence.Entity;

@Entity
public class Car extends Vehicule{
	
	
	private int seats;
	
	public Car() {
		
	}
	
	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}
}
