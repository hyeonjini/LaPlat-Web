package com.laplat.admin.web.domain.projects;

import com.laplat.admin.domain.projects.Projects;
import com.laplat.admin.domain.projects.ProjectsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectsRepositoryTest {

    @Autowired
    ProjectsRepository projectsRepository;

    @After
    public void cleanup(){
        projectsRepository.deleteAll();
    }

    @Test
    public void saveProjectsAndLoad(){
        String name = "테스트 이름";
        String description = "테스트 개요";
        String author = "테스트 담당자";
        Boolean status = false;

        projectsRepository.save(Projects.builder()
                .name(name)
                .description(description)
                .author(author)
                .status(status)
                .build());

        List<Projects> projectsList = projectsRepository.findAll();

        Projects projects = projectsList.get(0);
        assertThat(projects.getName()).isEqualTo(name);
        assertThat(projects.getDescription()).isEqualTo(description);
        assertThat(projects.getAuthor()).isEqualTo(author);
        assertThat(projects.getStatus()).isEqualTo(status);

    }
}
