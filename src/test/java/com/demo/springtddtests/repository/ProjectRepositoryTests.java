package com.demo.springtddtests.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.springtddtests.model.Project;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectRepositoryTests {

  // Use H2 in memory DB to test ProjectRepository instead of adding data into real DB.
  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private ProjectRepository projectRepository;

  private List<Project> projects;

  @Before
  public void setUp() {
    projects = new ArrayList<Project>();
    Project p1 = Project.builder().name("Project 1").build();
    Project p2 = Project.builder().name("Project 2").build();
    projects.add(p1);
    projects.add(p2);
  }

  // Insert 2 projects and findAll() should return 2 projects.
  @Test
  public void get_all_success() {
    for (Project project : projects) {
      entityManager.persist(project);
    }
    entityManager.flush();
    
    List<Project> result = projectRepository.findAll();
    assertNotNull(result);
    assertEquals(2, result.size());
  }

  // Persist one project, and findByName should return the project with matching name.
  @Test
  public void find_by_name_sucess() {
    Project project = projects.get(0);
    entityManager.persist(project);
    entityManager.flush();

    Project result = projectRepository.findByName(project.getName());
    assertNotNull(result);
    assertEquals(project.getName(), result.getName());
  }

  // Persist one project, and search by name doesn't exist, should return null .
  @Test
  public void find_by_name_returnNull() {
    Project project = projects.get(0);
    entityManager.persist(project);
    entityManager.flush();

    Project result = projectRepository.findByName("dsadassa");
    assertNull(result);
  }
}
