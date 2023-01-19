package com.univ.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univ.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Integer>{
	
	public List<Rent> findByPersonId(int personId);
}
