package com.example.matchnow.message;

import com.example.matchnow.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name="MESSAGES")
@Getter @Builder
@AllArgsConstructor
public class Message {

    public Message() {}

    @Id
    @Column(name="message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Column(nullable = false)
    private String title;

    @Column(name="main_text", nullable = false)
    private String mainText;

    @JsonIgnoreProperties({"email", "githubLink", "blogLink", "job",
            "lastLoginAt", "createdDate", "modifiedDate", "skillStackList",
            "comments", "hibernateLazyInitializer", "sendMessageList", "receivedMessageList"
    })
    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

    @JsonIgnoreProperties({"email", "githubLink", "blogLink", "job",
            "lastLoginAt", "createdDate", "modifiedDate", "skillStackList",
            "comments", "hibernateLazyInitializer", "sendMessageList", "receivedMessageList"
    })
    @ManyToOne(fetch = FetchType.LAZY)
    private User recevier;

    @Column(nullable = false, name="send_date",
            insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    private String sendDate;
}
