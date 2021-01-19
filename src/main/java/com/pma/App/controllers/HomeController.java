package com.pma.App.controllers;

//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pma.App.dao.EmployeeRepo;
import com.pma.App.dao.ProjectRepo;
import com.pma.App.dto.EmployeeProject;
import com.pma.App.dto.statusCount;
import com.pma.App.entities.Project;


@Controller
public class HomeController {
	@Value("${version}")
	private String ver;
	
	@Autowired
	ProjectRepo projectRepo;
	@Autowired
	EmployeeRepo empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
//	Map<String, Object> map = new HashMap<>();	
	
	model.addAttribute("versionNumber", ver);
		
	List<Project> project =	projectRepo.findAll();
	model.addAttribute("projects" , project);

	
	List<statusCount> projectData= projectRepo.StatusCount();
	ObjectMapper objectMapper = new ObjectMapper();
	String jsonString = objectMapper.writeValueAsString(projectData);
	model.addAttribute("statusCount",jsonString);
	
	
	List<EmployeeProject> employeesTable = empRepo.employeeProject();
	model.addAttribute("employee",employeesTable);
	return "Home/Home";
	}
	
	
}
