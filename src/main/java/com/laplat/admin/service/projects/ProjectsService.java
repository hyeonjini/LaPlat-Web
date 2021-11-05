package com.laplat.admin.service.projects;

import com.laplat.admin.domain.projects.Projects;
import com.laplat.admin.domain.projects.ProjectsRepository;
import com.laplat.admin.web.dto.ProjectsListResponseDto;
import com.laplat.admin.web.dto.ProjectsResponseDto;
import com.laplat.admin.web.dto.ProjectsSaveRequestDto;
import com.laplat.admin.web.dto.ProjectsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectsService {
    private final ProjectsRepository projectsRepository;

    @Transactional
    public Long save(ProjectsSaveRequestDto requestDto){
        return projectsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ProjectsUpdateRequestDto requestDto){
        Projects projects = projectsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no project id="+id));

        projects.update(
                requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getAuthor(),
                requestDto.getStatus()
        );

        return id;
    }

    public ProjectsResponseDto findById(Long id){
        Projects entity = projectsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no proejct id="+id));

        return new ProjectsResponseDto(entity);

    }

    @Transactional
    public void delete(Long id){
        Projects projects = projectsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no project id="+id));
        projectsRepository.delete(projects);
    }

    @Transactional(readOnly = true)
    public List<ProjectsListResponseDto> findAllDesc(){
        return projectsRepository.findAllDesc().stream()
                .map(ProjectsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
