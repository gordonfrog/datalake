package com.coffeeandcode.amqp.consumerapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

public class Machine {
    private String brand;
    private String model;
    private String fabricationYear;
	public Machine(String brand, String model, String fabricationYear) {
		super();
		this.brand = brand;
		this.model = model;
		this.fabricationYear = fabricationYear;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getFabricationYear() {
		return fabricationYear;
	}
	public void setFabricationYear(String fabricationYear) {
		this.fabricationYear = fabricationYear;
	}
	@Override
	public String toString() {
		return "Machine [brand=" + brand + ", model=" + model + ", fabricationYear=" + fabricationYear + "]";
	}
    
    
}
