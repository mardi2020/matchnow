package com.example.matchnow.user;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

@Data
public class UserJoinDTO {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String username;

    @URL
    private String githubLink;

    @URL
    private String blogLink;

    private String job;

    public User toEntity(String pw) {
        return User.builder()
                .email(email)
                .password(pw)
                .username(username)
                .blogLink(blogLink)
                .githubLink(githubLink)
                .job(job)
                .role(Role.MEMBER)
                .build();
    }
}
