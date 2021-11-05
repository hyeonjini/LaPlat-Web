package com.laplat.admin.web;

import com.laplat.admin.domain.projects.Projects;
import com.laplat.admin.domain.projects.ProjectsRepository;
import com.laplat.admin.web.dto.ProjectsResponseDto;
import com.laplat.admin.web.dto.ProjectsSaveRequestDto;
import com.laplat.admin.web.dto.ProjectsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProjectsRepository projectsRepository;

    @After
    public void tearDown() throws Exception{
        projectsRepository.deleteAll();
    }

    // 생성 Test
    @Test
    public void saveProjects() throws Exception{
        String name = "name";
        String description = "description";
        String author = "author";
        Boolean status = false;

        ProjectsSaveRequestDto requestDto = ProjectsSaveRequestDto.builder()
                .name(name)
                .description(description)
                .author(author)
                .status(status)
                .build();

        String url = "http://localhost:" + port + "/api/v1/projects";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Projects> all = projectsRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getDescription()).isEqualTo(description);
        assertThat(all.get(0).getAuthor()).isEqualTo(author);
        assertThat(all.get(0).getStatus()).isEqualTo(status);
    }
    // 수정 Test
    @Test
    public void updateProjects() throws Exception{
        Projects savedProjects = projectsRepository.save(Projects.builder()
                .name("name")
                .description("description")
                .author("author")
                .status(false)
                .build());

        Long updateId = savedProjects.getId();
        String expectedName = "name2";
        String expectedDescription = "description2";
        String expectedAuthor = "author2";
        Boolean expectedStatus = true;

        ProjectsUpdateRequestDto requestDto = ProjectsUpdateRequestDto.builder()
                .name(expectedName)
                .description(expectedDescription)
                .author(expectedAuthor)
                .status(expectedStatus)
                .build();

        String url = "http://localhost:" + port + "/api/v1/projects/" + updateId;

        HttpEntity<ProjectsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Projects> all = projectsRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(expectedName);
        assertThat(all.get(0).getDescription()).isEqualTo(expectedDescription);
        assertThat(all.get(0).getAuthor()).isEqualTo(expectedAuthor);
        assertThat(all.get(0).getStatus()).isEqualTo(expectedStatus);
    }
    // 조회 Test
    // 삭제 test

}
