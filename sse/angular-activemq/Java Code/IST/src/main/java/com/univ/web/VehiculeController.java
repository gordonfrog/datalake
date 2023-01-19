package com.univ.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univ.jms.Sender;
import com.univ.model.Car;
import com.univ.model.Van;
import com.univ.repository.CarRepository;
import com.univ.repository.VanRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/vehicules")
public class VehiculeController {
	
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private VanRepository vanRepository;
	@Autowired
	private Sender car_sender;
	@Value(value = "CAR.PROCESSING.D")
	private String destination_car;
	@Autowired
	private Sender van_sender;
	@Value(value = "VAN.PROCESSING.D")
	private String destination_van;
	
	@GetMapping(value = "/cars/all")
	public List<Car> getAllCars(){
		return carRepository.findAll();
	}

	@PostMapping(value = "/cars/add")
	public Car addCar(@RequestBody Car car){
		carRepository.save(car);
		car_sender.send(destination_car, car);
		return car;
	}

	@PutMapping(value = "/cars/{vehiculeId}")
	public Car updateCar(@PathVariable("vehiculeId") int vehiculeId, @RequestBody Car car){
		car.setVehiculeId(vehiculeId);
		carRepository.save(car);
		return car;
	}

	@DeleteMapping(value = "/cars/{vehiculeId}")
	public List<Car> removeCar(@PathVariable("vehiculeId") int vehiculeId){
		carRepository.deleteById(vehiculeId);
		return carRepository.findAll();
	}
	
	@GetMapping(value = "/vans/all")
	public List<Van> getAllVans(){
		return vanRepository.findAll();
	}

	@PostMapping(value = "/vans/add")
	public Van addVan(@RequestBody Van van){
		vanRepository.save(van);
		van_sender.send(destination_van, van);
		return van;
	}

	@PutMapping(value = "/vans/{vehiculeId}")
	public Van updateVan(@PathVariable("vehiculeId") int vehiculeId, @RequestBody Van van){
		van.setVehiculeId(vehiculeId);
		vanRepository.save(van);
		return van;
	}

	@DeleteMapping(value = "/vans/{vehiculeId}")
	public List<Van> removeVan(@PathVariable("vehiculeId") int vehiculeId){
		vanRepository.deleteById(vehiculeId);
		return vanRepository.findAll();
	}
	
	

}
