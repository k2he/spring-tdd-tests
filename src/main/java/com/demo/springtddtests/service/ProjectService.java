package com.demo.springtddtests.service;

import java.util.List;
import com.demo.springtddtests.model.Project;

public interface ProjectService {
  List<Project> getAllProjects();

  Project getProjectById(Long id);
}
