package com.univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univ.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
