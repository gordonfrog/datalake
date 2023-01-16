package com.spring.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.backend.entity.Employee;
import com.spring.backend.service.EmployeeService;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/registerEmployee")
	public Employee registerEmployee(@RequestBody Employee employee) {
		return employeeService.registerStudent(employee);
	}
	@GetMapping("/getEmployees")
	public List<Employee> getEmployees(){
		return employeeService.getEmployees();
	}
	@DeleteMapping("/deleteEmployee")
	public void deleteEmployee(@RequestParam Integer id) {
		employeeService.deleteEmployee(id);
	}
	@PutMapping("/updateEmployee")
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}
}
