package com.pma.App.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pma.App.dto.statusCount;
import com.pma.App.entities.Project;
import java.util.List;

public interface ProjectRepo extends PagingAndSortingRepository<Project, Long> {
	@Override
	public List<Project> findAll();
	@Query(nativeQuery = true , value = "SELECT stage as label , COUNT(*) as value " + "FROM project " + "GROUP BY stage")
	public List<statusCount> StatusCount();
}
