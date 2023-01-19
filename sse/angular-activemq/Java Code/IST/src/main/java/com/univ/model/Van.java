package com.univ.model;

import javax.persistence.Entity;

@Entity
public class Van extends Vehicule{
	
	private double maxWeight;
	
	
	public Van() {

	}

	public double getMaxWeight() {
		return maxWeight;
	}


	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}
}