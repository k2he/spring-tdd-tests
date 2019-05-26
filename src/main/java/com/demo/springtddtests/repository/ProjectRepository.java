package com.demo.springtddtests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.springtddtests.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
  public Project findByName(String name);
}
