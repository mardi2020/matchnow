package com.example.matchnow.skillStack;

import com.example.matchnow.user.User;
import lombok.Data;

@Data
public class AddSkillStackDTO {

    private String stackName;

    public SkillStack toEntity(User user) {
        return SkillStack.builder()
                .stackName(stackName)
                .user(user)
                .build();
    }

}
