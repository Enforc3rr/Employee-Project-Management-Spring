package com.pma.App.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.pma.App.dao.ProjectRepo;

import com.pma.App.entities.Project;

@Service
public class ProjectServices {
	@Autowired
	ProjectRepo projectRepo;
	
	public void save(Project project) {
		  projectRepo.save(project);
	}
	
	public List<Project> getAll(){
		return projectRepo.findAll();
	}
}