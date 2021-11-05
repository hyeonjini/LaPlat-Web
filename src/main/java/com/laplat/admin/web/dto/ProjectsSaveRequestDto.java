package com.laplat.admin.web.dto;

import com.laplat.admin.domain.projects.Projects;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectsSaveRequestDto {
    private String name;
    private String description;
    private String author;
    private Boolean status;

    @Builder
    public ProjectsSaveRequestDto(String name, String description, String author, Boolean status){
        this.name = name;
        this.description = description;
        this.author = author;
        this.status = status;
    }

    public Projects toEntity(){
        return Projects.builder()
                .name(name)
                .description(description)
                .author(author)
                .status(status)
                .build();
    }
}
