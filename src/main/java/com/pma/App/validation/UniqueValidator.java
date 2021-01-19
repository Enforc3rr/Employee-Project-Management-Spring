package com.pma.App.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.pma.App.dao.EmployeeRepo;
import com.pma.App.entities.Employee;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String>{
	@Autowired
	EmployeeRepo empRepo;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		System.out.println("Entered Validation Method...");
		Employee employee = empRepo.findByEmail(value);
		if(employee!=null)
			return false;
		else {
			return true;
		}
	}

}
