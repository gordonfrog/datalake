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
import com.univ.model.Rent;
import com.univ.repository.RentRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/persons/{id}/rents")
public class RentController {

	@Autowired
	private RentRepository rentRepository;
	@Autowired
	private Sender sender;
	@Value(value = "RENT.PROCESSING.D")
	private String destination;
	
	
	@GetMapping(value = "/all")
	public List<Rent> getAll(@PathVariable("id") int id){
		return rentRepository.findByPersonId(id);
	}
	
	@PostMapping(value = "/add")
	public Rent addRent(@PathVariable("id") int id, @RequestBody Rent rent){
		rentRepository.save(rent);
		sender.send(destination, rent); 
		return rent;
	}
	
	@PutMapping(value = "/{rentId}")
	public Rent updateRent(@PathVariable("id") int id, @PathVariable("rentId") int rentId, @RequestBody Rent rent){
		rent.setRentId(rentId);
		rentRepository.save(rent);
		return rent;
	}
	
	@DeleteMapping(value = "/{rentId}")
	public List<Rent> removeRent(@PathVariable("id") int id, @PathVariable("rentId") int rentId){
		rentRepository.deleteById(rentId);
		return rentRepository.findByPersonId(id);
	}


}
