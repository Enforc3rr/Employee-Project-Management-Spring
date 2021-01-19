package com.pma.App.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pma.App.dao.EmployeeRepo;

import com.pma.App.entities.Employee;
import com.pma.App.entities.Project;
import com.pma.App.services.ProjectServices;

@Controller
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	ProjectServices projectServices ;
	@Autowired
	EmployeeRepo empRepo;
	                                            
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project aProject = new Project();
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("allEmployees", employees);
		model.addAttribute("project", aProject);
		return "Project/new-project";
	}
	@PostMapping("/save" )
	public String createProject(Project project ) {
		projectServices.save(project);
		return "redirect:/project/new";
	}
	@GetMapping("/displayProjectTable")
	public String displayProjectTable(Model model) {
		List<Project> project =	projectServices.getAll();
		model.addAttribute("projects" , project);
		return "Project/displayTable";
	}
	
}
