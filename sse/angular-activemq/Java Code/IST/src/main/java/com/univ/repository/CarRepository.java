package com.univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univ.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{

}
