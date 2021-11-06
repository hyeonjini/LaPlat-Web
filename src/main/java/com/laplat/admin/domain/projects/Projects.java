package com.laplat.admin.domain.projects;

import com.laplat.admin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Projects extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=50, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

//    @OneToMany
//    @JoinColumn(name="user_id")
//    private List<Users> users;
//    @ManyToOne
    private String author;
    private Boolean status;

    @Builder
    public Projects(String name, String description, String author, Boolean status){
        this.name = name;
        this.description = description;
        this.author = author;
        this.status = status;
    }

    public void update(String name, String description, String author, Boolean status){
        this.name = name;
        this.description = description;
        this.author = author;
        this.status = status;
    }

}
