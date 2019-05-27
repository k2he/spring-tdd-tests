package com.demo.springtddtests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.springtddtests.model.Project;

// Integration Tests
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectIntegrationTests {

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  public void test_get_AllProjects() throws Exception {
    ResponseEntity<List<Project>> response = restTemplate.exchange("/projects", HttpMethod.GET,
        null, new ParameterizedTypeReference<List<Project>>() {});
    List<Project> projects = response != null ? response.getBody() : null;
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    
    // TODO: since I didn't setup data and DB, it can't perform more checks.
    // assertTrue(projects.size() > 0);
  }

  @Test
  public void test_get_ProjectById() throws Exception {
    ResponseEntity<Project> response = restTemplate.getForEntity("/projects/1", Project.class);
    
    //TODO: 
    //    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    //    assertThat(response.getBody().getId()).isEqualTo("1");
  }
}
