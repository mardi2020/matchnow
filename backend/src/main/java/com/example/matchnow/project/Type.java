package com.example.matchnow.project;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {

    NONE("분류 없음"),
    CONTEST("공모전"),
    PROJECT("팀 프로젝트"),
    STUDY("스터디");

    private String value;
}
