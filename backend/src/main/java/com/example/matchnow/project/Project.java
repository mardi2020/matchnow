package com.example.matchnow.project;

import com.example.matchnow.BaseTime;
import com.example.matchnow.comment.Comment;
import com.example.matchnow.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="PROJECTS")
@Getter
@AllArgsConstructor
@Builder
public class Project extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    private Long projectId;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // writer

    @Column(name="main_text", nullable = false)
    private String mainText;

    @Column(name = "input_image")
    private String inputImage;

    @Column(name="want_cnt", nullable = false)
    private int wantCnt;

    @Column(name="now_cnt",
            columnDefinition = "INT DEFAULT 1"
    )
    private int nowPeopleCnt;

    @Enumerated(EnumType.ORDINAL)
    private State state;

    @Enumerated(EnumType.STRING)
    private Type category;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public Project() {}

    public void setUpdateProject(String title, String mainText, String image, Type type) {
        this.title = title;
        this.mainText = mainText;
        this.inputImage = image;
        this.category = type;
    }
}
