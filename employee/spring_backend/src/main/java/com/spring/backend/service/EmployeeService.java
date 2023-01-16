package com.spring.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.backend.entity.Employee;
import com.spring.backend.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee registerStudent(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> getEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}

	public void deleteEmployee(Integer id) {
		employeeRepository.deleteById(id);
	}

	public Employee updateEmployee(Employee employee) {
		Integer id = employee.getId();
		Employee emp = employeeRepository.findById(id).get();
		emp.setName(employee.getName());
		emp.setSurname(employee.getSurname());
		emp.setDob(employee.getDob());
		emp.setJoiningDate(employee.getJoiningDate());
		emp.setState(employee.getState());
		emp.setPincode(employee.getPincode());
		return employeeRepository.save(emp);

	}
}
