package com.demo.springtddtests;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.springtddtests.model.Project;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RunWith(SpringRunner.class)
@SpringBootTest
@RequiredArgsConstructor
public class SpringTddTestsApplicationTests {

//	@Test
//	public void contextLoads() {
//	}
  @NonNull
  private TestRestTemplate restTemplate;
  
  @Test
  public void test() throws Exception {
    //arrange 
    
    //act
//    ResponseEntity<List<Project>> = restTemplate.getForEntity("/projects", responseType)
    ResponseEntity<List<Project>> response = restTemplate.exchange("/projects", HttpMethod.GET, null, new ParameterizedTypeReference<List<Project>>() {
    });
    List<Project> projects = response!=null ? response.getBody() : null;
      
    //assert
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//    assertThat(response.getSt)
  }
}
