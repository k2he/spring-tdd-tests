package com.demo.springtddtests.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
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
    // given
    Mockito.when(projectRepository.findAll()).thenReturn(projects);

    // when
    List<Project> result = projectService.getAllProjects();

    // then
    assertNotNull(result);
    assertEquals(2, result.size());
  }

  @Test
  public void getProjectByName_returnOneProject() {
    Long id = Long.valueOf(10000);
    Project testProject = projects.get(0);

    // given
    Mockito.when(projectRepository.findById(id)).thenReturn(Optional.ofNullable(testProject));

    // when
    Project result = projectService.getProjectById(id);

    // then
    assertNotNull(result);
    assertEquals(testProject.getId(), result.getId());
    assertEquals(testProject.getName(), result.getName());
  }
  
  @Test(expected = ResourceNotFoundException.class)
  public void getProject_notFound() {
    Long id = Long.valueOf(10000);

    // given
    Mockito.when(projectRepository.findById(id)).thenThrow(new ResourceNotFoundException(""));

    // when
    projectService.getProjectById(id);
  }

  @Test
  @DisplayName("call getProjectById() nothing found, should throw ResourceNotFoundException. This is same test as getProject_notFound(). but different way.")
  public void getProjectById_NothingFoundShould_ThrowException() {
    // given
    Mockito.when(projectRepository.findById(any())).thenReturn(Optional.empty());

    // when
    Throwable exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
      projectService.getProjectById(Long.valueOf(10000));
    });

    // then
    assertNotNull(exception);
  }
}
