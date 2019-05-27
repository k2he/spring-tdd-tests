package com.demo.springtddtests.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.demo.springtddtests.model.Project;
import com.demo.springtddtests.model.exception.ResourceNotFoundException;
import com.demo.springtddtests.repository.ProjectRepository;

@RunWith(MockitoJUnitRunner.class) 
public class ProjectServiceTest {

  @Mock
  private ProjectRepository projectRepository;
  
  private ProjectService projectService;
  
  private List<Project> projects; 
  
  @Before
  public void setUp() {
    projectService = new ProjectServiceImpl(projectRepository);
        
    projects = new ArrayList<Project>();
    Project p1 = Project.builder().id(new Long(1)).name("Project 1").build();
    Project p2 = Project.builder().id(new Long(2)).name("Project 2").build();
    projects.add(p1);
    projects.add(p2);
  }
  
  @Test
  public void getProjects_ShouldReturnProjectList() throws Exception {
    Mockito.when(projectRepository.findAll()).thenReturn(projects);
    
    List<Project> result = projectService.getAllProjects();
   
    assertNotNull(result);
    assertEquals(2, result.size());
  }
  
  @Test
  public void getProjectByName_returnOneProject() throws Exception {
    Long id = new Long(1);
    Project testProject = projects.get(0);
    
    Mockito.when(projectRepository.findById(id)).thenReturn(Optional.ofNullable(testProject));
    
    Project result = projectService.getProjectById(id);
    
    assertNotNull(result);
    assertEquals(testProject.getId(), result.getId());
    assertEquals(testProject.getName(), result.getName());
  }
  
  @Test(expected = ResourceNotFoundException.class)
  public void getProject_notFound() throws Exception {
    Long id = new Long(10000);
    
    Mockito.when(projectRepository.findById(id)).thenThrow(new ResourceNotFoundException(""));
    
    projectService.getProjectById(id);
  }
}
