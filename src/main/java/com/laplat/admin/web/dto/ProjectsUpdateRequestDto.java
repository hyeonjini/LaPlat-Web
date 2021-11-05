package com.laplat.admin.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectsUpdateRequestDto {
    private String name;
    private String description;
    private String author;
    private Boolean status;

    @Builder
    public ProjectsUpdateRequestDto(String name, String description, String author, Boolean status){
        this.name = name;
        this.description = description;
        this.author = author;
        this.status = status;

    }
}
