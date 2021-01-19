package com.pma.App.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.pma.App.entities.Employee;
import com.pma.App.services.EmployeeServices;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeServices employeeServices;
	
	
	@GetMapping("/newemployee")
	public String createEmployee(Model model) {
		Employee employeeObj = new Employee();
		model.addAttribute("employee",employeeObj);
		return "Employee/EmployeeForm";
	}
	@PostMapping("/saveEmployee")
	public String saveEmployee(Employee employee) {
		employeeServices.save(employee);
		return "redirect:/employee/newemployee";
	}
	@GetMapping("/showEmployee")
	public String displayEmployees(Model model) {
		List<Employee> employeesTable = employeeServices.getAll();
		model.addAttribute("employee",employeesTable);
		return "Employee/employeeTable";
	}

}
