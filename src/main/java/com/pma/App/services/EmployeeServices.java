package com.pma.App.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pma.App.dao.EmployeeRepo;
import com.pma.App.dto.EmployeeProject;
import com.pma.App.entities.Employee;

@Service
public class EmployeeServices {
	@Autowired
	EmployeeRepo employeeRepo;
	
	public void save(Employee employee) {
		  employeeRepo.save(employee);
	}
	
	public List<Employee> getAll(){
		return employeeRepo.findAll();
	}
	
	public List<EmployeeProject> employeeProjects(){
		return employeeRepo.employeeProject();
	}
	
	
}
