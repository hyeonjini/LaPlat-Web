package com.laplat.admin.domain.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {

    @Query("SELECT p FROM Projects p ORDER BY p.id DESC")
    List<Projects> findAllDesc();
}
