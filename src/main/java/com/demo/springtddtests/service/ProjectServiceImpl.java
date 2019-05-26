package com.demo.springtddtests.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.demo.springtddtests.model.Project;
import com.demo.springtddtests.repository.ProjectRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

  @NonNull
  ProjectRepository projectRepository;

  @Override
  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }


  @Override
  public Project getProjectByName(String name) {
    return projectRepository.findByName(name);
  }

}
