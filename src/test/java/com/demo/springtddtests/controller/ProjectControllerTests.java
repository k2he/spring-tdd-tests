package com.demo.springtddtests.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.demo.springtddtests.model.Project;
import com.demo.springtddtests.model.exception.ResourceNotFoundException;
import com.demo.springtddtests.service.ProjectService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class) // This will not start up entire application
public class ProjectControllerTests {
  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private ProjectService projectService;
  
  private List<Project> projects; 
  
  @Before
  public void setUp() {
    projects = new ArrayList<Project>();
    Project p1 = Project.builder().id(Long.valueOf(1)).name("Project 1").build();
    Project p2 = Project.builder().id(Long.valueOf(2)).name("Project 2").build();
    projects.add(p1);
    projects.add(p2);
  }
  
  @Test
  public void getProjects_ShouldReturnProjectList() throws Exception {
    // given
    Mockito.when(projectService.getAllProjects()).thenReturn(projects);

    // when, then
    mockMvc.perform(MockMvcRequestBuilders.get("/projects"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()", is(2)));
  }
  
  @Test
  public void getProjectByName_ShouldReturnOneProject() throws Exception {
    // given
    Mockito.when(projectService.getProjectById(Long.valueOf(1))).thenReturn(projects.get(0));

    // when, then
    mockMvc.perform(MockMvcRequestBuilders.get("/projects/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").value(1));
  }
   
  @Test
  public void getProject_notFound() throws Exception {
    // given
    String id = "10000";
    Mockito.when(projectService.getProjectById(Long.valueOf(id))).thenThrow(new ResourceNotFoundException(""));

    // when, then
    mockMvc.perform(MockMvcRequestBuilders.get("/projects/" + id))
            .andExpect(status().isNotFound());
  }
}
