package com.pma.App.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pma.App.dao.EmployeeRepo;
import com.pma.App.entities.Employee;



@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@GetMapping
	public List<Employee> getEmployees(){
		return employeeRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeId(@PathVariable("id") Long id) {
		return employeeRepo.findById(id).get();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@RequestBody @Valid Employee employee) {
		return employeeRepo.save(employee);
	}
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee update(@RequestBody Employee employee) {
		return employeeRepo.save(employee);
	}
	
	@PatchMapping(path = "/{id}", consumes = "application/json")
	public Employee partialUpdate(@PathVariable("id") long id , @RequestBody @Valid Employee patchEmployee) {
		Employee emp = employeeRepo.findById(id).get();
		if(patchEmployee.getEmail() !=null) {
			emp.setEmail(patchEmployee.getEmail());
		}
		if(patchEmployee.getFirstName() !=null) {
			emp.setEmail(patchEmployee.getFirstName());
		}
		if(patchEmployee.getLastName() !=null) {
			emp.setEmail(patchEmployee.getLastName());
		}
		
		return employeeRepo.save(emp);
		
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmp(@PathVariable("id") Long id) {
		try {
			employeeRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
		
	}
	@GetMapping(params = {"page","size"})
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Employee> page(@RequestParam("page") int page , @RequestParam("size") int size){
		Pageable ps = PageRequest.of(page, size);
		return employeeRepo.findAll(ps);
	}
	
}
