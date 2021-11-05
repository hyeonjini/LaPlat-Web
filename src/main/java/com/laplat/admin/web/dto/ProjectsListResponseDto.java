package com.laplat.admin.web.dto;

import com.laplat.admin.domain.projects.Projects;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProjectsListResponseDto {
    private Long id;
    private String name;
    private String description;
    private String author;
    private Boolean status;
    private LocalDateTime modifiedDate;

    public ProjectsListResponseDto(Projects entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.author = entity.getAuthor();
        this.status = entity.getStatus();
        this.modifiedDate = entity.getModifiedDate();
    }
}
