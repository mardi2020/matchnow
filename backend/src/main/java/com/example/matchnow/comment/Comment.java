package com.example.matchnow.comment;

import com.example.matchnow.BaseTime;
import com.example.matchnow.project.Project;
import com.example.matchnow.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter @Builder
@Table(name="comments")
@AllArgsConstructor
public class Comment extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long commentId;

    @Column(name="comment_text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({
            "title", "user", "mainText", "inputImage", "wantCnt",
            "nowPeopleCnt", "createdDate", "modifiedDate", "state", "categoryList",
            "comments", "hibernateLazyInitializer"
    })
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="comment_user_id")
    @JsonIgnoreProperties({"email", "githubLink", "blogLink", "job",
            "modifiedDate", "createdDate", "modifiedDate", "skillStackList",
            "comments", "hibernateLazyInitializer", "sendMessageList", "receivedMessageList"
    })
    private User user;

    public Comment() {}

}
