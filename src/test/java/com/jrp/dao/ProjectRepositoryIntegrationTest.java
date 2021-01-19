package com.jrp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.pma.App.ManagementProjectApplication;
import com.pma.App.dao.ProjectRepo;
import com.pma.App.entities.Project;

@ContextConfiguration(classes = ManagementProjectApplication.class)
@DataJpaTest
@RunWith(SpringRunner.class)
@SqlGroup({@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql","classpath:data.sql"}),
	     @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD,scripts = {"classpath:drop.sql"})})
public class ProjectRepositoryIntegrationTest {
	@Autowired
	ProjectRepo projectRepo;
	
	@Test
	public void ifNewProjectSaved_thenSuccess() {
		Project newProject = new Project("New Test Project" ,"COMPLETE" , "Test");
		projectRepo.save(newProject);
		assertEquals(5, projectRepo.findAll().size());
	}
}
