package com.demo.springtddtests.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.springtddtests.model.Project;
import com.demo.springtddtests.repository.ProjectRepository;

@RunWith(SpringRunner.class)
public class ProjectServiceTest {

  @MockBean
  private static ProjectRepository projectRepository;
  
  @Autowired
  private ProjectService projectService;
  
  private List<Project> projects; 
  
  @Before
  public void setUp() {
    projects = new ArrayList<Project>();
    Project p1 = Project.builder().name("Project 1").build();
    Project p2 = Project.builder().name("Project 2").build();
    projects.add(p1);
    projects.add(p2);
  }
  
  @Test
  public void testGetAllProjects() {
    
  }
}
