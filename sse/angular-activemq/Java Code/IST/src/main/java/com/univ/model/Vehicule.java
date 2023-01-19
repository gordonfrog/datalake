package com.univ.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vehicule {
	
	@Id
	@GeneratedValue
	protected int vehiculeId;
	protected String model;
	protected String plateNumber;
	protected double price;
	@OneToMany(mappedBy="vehicule", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private List<Rent> rents = new ArrayList<Rent>();
	
	
	public Vehicule() {

	}

	public int getVehiculeId() {
		return vehiculeId;
	}

	public void setVehiculeId(int vehiculeId) {
		this.vehiculeId = vehiculeId;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
