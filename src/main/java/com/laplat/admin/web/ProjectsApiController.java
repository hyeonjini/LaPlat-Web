package com.laplat.admin.web;

import com.laplat.admin.service.projects.ProjectsService;
import com.laplat.admin.web.dto.ProjectsListResponseDto;
import com.laplat.admin.web.dto.ProjectsResponseDto;
import com.laplat.admin.web.dto.ProjectsSaveRequestDto;
import com.laplat.admin.web.dto.ProjectsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProjectsApiController {

    private final ProjectsService projectsService;

    @PostMapping("/api/v1/projects")
    public Long save(@RequestBody ProjectsSaveRequestDto requestDto){
        return projectsService.save(requestDto);
    }

    @PutMapping("/api/v1/projects/{id}")
    public Long update(@PathVariable Long id, @RequestBody ProjectsUpdateRequestDto requestDto){
        return projectsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/projects/{id}")
    public ProjectsResponseDto findById(@PathVariable Long id){
        return projectsService.findById(id);
    }

    @DeleteMapping("/api/v1/projects/{id}")
    public Long delete(@PathVariable Long id){
        projectsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/projects/findAllDesc/{author}")
    public List<ProjectsListResponseDto> findAllDesc(@PathVariable String author){ return projectsService.findAllDesc();}
}
