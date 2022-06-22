package com.example.matchnow.mapper;

import com.example.matchnow.project.PostedProjectDTO;
import com.example.matchnow.project.Project;
import org.modelmapper.PropertyMap;

public class ProjectMapper extends PropertyMap<Project, PostedProjectDTO> {
    protected void configure() {
        map().setProjectId(source.getProjectId());
        map().setState(source.getState());
        map().setWriter(source.getUser().getUsername());
        map().setCreateAt(source.getCreatedDate());
        map().setTitle(source.getTitle());
    }
}
