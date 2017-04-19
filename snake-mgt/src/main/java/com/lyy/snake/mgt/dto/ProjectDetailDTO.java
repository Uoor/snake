package com.lyy.snake.mgt.dto;

import java.io.Serializable;

/**
 * Created by fanshuai on 17/4/19.
 */
public class ProjectDetailDTO implements Serializable{
    public String projectGroup;
    public String projectName;
    public String projectDesc;

    public ProjectDetailDTO(){}
    public ProjectDetailDTO(String projectGroup, String projectName, String projectDesc) {
        this.projectGroup = projectGroup;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }
}
