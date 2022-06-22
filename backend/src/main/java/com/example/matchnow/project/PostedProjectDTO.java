package com.example.matchnow.project;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostedProjectDTO {

    private Long projectId;

    private String title;

    private LocalDateTime createAt;

    private State state;

    private String writer;

    private Type category;

    private int nowPeopleCnt;

    private int wantCnt;

    public PostedProjectDTO(Project entity) {
        this.projectId = entity.getProjectId();
        this.title = entity.getTitle();
        this.createAt = entity.getCreatedDate();
        this.state = entity.getState();
        this.writer = entity.getUser().getUsername();
        this.category = entity.getCategory();
        this.nowPeopleCnt = entity.getNowPeopleCnt();
        this.wantCnt = entity.getWantCnt();
    }
}
