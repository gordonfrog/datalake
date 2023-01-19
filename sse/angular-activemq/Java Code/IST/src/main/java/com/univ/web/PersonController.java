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
import com.univ.model.Person;
import com.univ.repository.PersonRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/persons")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;	
	@Autowired
	private Sender sender;
	@Value(value = "PERSON.PROCESSING.D")
	private String destination;
	
	@GetMapping (value = "/all")
	public List<Person> getAll(){
		List<Person> result =  personRepository.findAll();
		return result;
	}
	
	@PostMapping(value = "/add")
	public Person addPerson(@RequestBody Person person){
		personRepository.save(person);
		sender.send(destination, person);
		return person;
	}
	
	@PutMapping(value = "/{id}")
	public Person updatePerson(@PathVariable("id") int id, @RequestBody Person person){
		person.setId(id); 
		personRepository.save(person);
		return person;
	}
	
	@DeleteMapping(value = "/{id}")
	public List<Person> removePerson(@PathVariable("id") int id){
		personRepository.deleteById(id);
		return personRepository.findAll();
	}
}
