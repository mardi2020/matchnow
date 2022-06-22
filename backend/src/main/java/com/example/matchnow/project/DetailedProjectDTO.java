package com.example.matchnow.project;

import com.example.matchnow.comment.Comment;
import com.example.matchnow.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class DetailedProjectDTO {

    private Long projectId;

    private String title;

    private String mainText;

    private String writer;

    private String inputImage;

    private int wantCnt;

    private int nowPeopleCnt;

    private State state;

    private Type category;

    public void setUser(User source){
        writer = source.getUsername();
    }

    public DetailedProjectDTO(Project entity) {
        this.projectId = entity.getProjectId();
        this.title = entity.getTitle();
        this.mainText = entity.getMainText();
        this.writer = entity.getUser().getUsername();
        this.inputImage = entity.getInputImage();
        this.wantCnt = entity.getWantCnt();
        this.nowPeopleCnt = entity.getNowPeopleCnt();
        this.state = entity.getState();
        this.category = entity.getCategory();
    }
}
