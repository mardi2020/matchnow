package com.example.matchnow.skillStack;

import com.example.matchnow.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="SKILL_STACKS")
@Getter
@Builder
@AllArgsConstructor
public class SkillStack {

    public SkillStack() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="skill_stack_id")
    private Long skillStackId;

    @Column(name="stack_name", unique = true, nullable = false)
    private String stackName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_skill_id")
    @JsonBackReference
    private User user; //user
}
