package com.example.matchnow.comment;


import com.example.matchnow.project.Project;
import com.example.matchnow.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ResponseCommentDTO {
    private Long commentId;

    private String text;

    @JsonIgnoreProperties({
            "title", "user", "mainText", "inputImage", "wantCnt",
            "nowPeopleCnt", "modifiedDate", "createdDate", "state", "categoryList",
            "comments", "hibernateLazyInitializer", "deleted"
    })
    private Project project;

    private LocalDateTime createAt;

    @JsonIgnoreProperties({"email", "githubLink", "blogLink", "job", "userId",
            "modifiedDate", "createdDate", "skillStackList", "comments", "hibernateLazyInitializer",
            "sendMessageList", "receivedMessageList"
    })
    private User user;

    public ResponseCommentDTO(Comment entity) {
        this.commentId = entity.getCommentId();
        this.createAt = entity.getCreatedDate();
        this.project = entity.getProject();
        this.text = entity.getText();
        this.user = entity.getUser();
    }
}
