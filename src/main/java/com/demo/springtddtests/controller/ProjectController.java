package com.demo.springtddtests.controller;

import java.util.List;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.demo.springtddtests.model.Project;
import com.demo.springtddtests.service.ProjectService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

  @NonNull
  ProjectService projectService;

  // Get All projects
  @GetMapping()
  public List<Project> getAllProjects() {
    return projectService.getAllProjects();
  }
  
  // Get project by id
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Project getProjectById(@PathVariable @NotNull @DecimalMin("0") Long id) {
    return projectService.getProjectById(id);
  }
  
  // TODO: will need add @PostMapping() for create, @PutMapping("/{id}") for update, @DeleteMapping("/{id}") for delete
  //       and others based on requirements.
}
