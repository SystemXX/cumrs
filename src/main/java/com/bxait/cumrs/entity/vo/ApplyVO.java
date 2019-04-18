package com.bxait.cumrs.entity.vo;

import com.bxait.cumrs.entity.model.Student;

import java.util.List;

public class ApplyVO {

    private String applyType;

    private String username;

    private String teamName;

    private Student[] students;

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
}
