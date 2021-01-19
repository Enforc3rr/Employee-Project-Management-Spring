package com.pma.App.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pma.App.dto.EmployeeProject;
import com.pma.App.entities.Employee;

@RepositoryRestResource(collectionResourceRel = "apiemployees" , path = "apiemployees")
public interface EmployeeRepo extends PagingAndSortingRepository<Employee, Long> {
	@Override
	List<Employee> findAll();
	
	@Query(nativeQuery = true , value = "SELECT  e.first_name  as firstName, e.last_name as lastName ,COUNT(pe.employee_id) as projectCount " + 
			"FROM employee e RIGHT JOIN project_employee pe ON e.employee_id =pe.employee_id " + 
			"GROUP BY e.first_name , e.last_name ORDER BY 3 DESC ")
	public List<EmployeeProject> employeeProject();
	
	
	public Employee findByEmail(String value);
	
}
