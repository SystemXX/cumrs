package com.bxait.cumrs.entity.model;

import javax.persistence.*;

@Entity
@Table(name = "T_TEAM")
public class Team {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TEAMNAME",length = 50)
    private String teamName;

    @Column(name = "TEACHERID")
    private String teacherId;

    @Column(name = "STUDENTONE")
    private String  studentOne;

    @Column(name = "STUDENTTWO")
    private String studentTwo;

    @Column(name = "STUDENTTHREE")
    private String studentThree;
    //是否满员
    @Column(name = "VACANCY",length = 1)
    private String vacancy;

    //本科or专科
    @Column(name = "EDUCATION",length = 10)
    private String education;

    //队伍状态
    @Column(name = "TEAMSTATE",length = 10)
    private String teamState;

    @Column(name = "TEACHERNAME",length = 50)
    private String teacherName;
    @Column(name = "STUDENTONENAME",length = 50)
    private String  studentOneName;
    @Column(name = "STUDENTTWONAME",length = 50)
    private String studentTwoName;
    @Column(name = "STUDENTTHREENAME",length = 50)
    private String  studentThreeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStudentOne() {
        return studentOne;
    }

    public void setStudentOne(String studentOne) {
        this.studentOne = studentOne;
    }

    public String getStudentTwo() {
        return studentTwo;
    }

    public void setStudentTwo(String studentTwo) {
        this.studentTwo = studentTwo;
    }

    public String getStudentThree() {
        return studentThree;
    }

    public void setStudentThree(String studentThree) {
        this.studentThree = studentThree;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentOneName() {
        return studentOneName;
    }

    public void setStudentOneName(String studentOneName) {
        this.studentOneName = studentOneName;
    }

    public String getStudentTwoName() {
        return studentTwoName;
    }

    public void setStudentTwoName(String studentTwoName) {
        this.studentTwoName = studentTwoName;
    }

    public String getStudentThreeName() {
        return studentThreeName;
    }

    public void setStudentThreeName(String studentThreeName) {
        this.studentThreeName = studentThreeName;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTeamState() {
        return teamState;
    }

    public void setTeamState(String teamState) {
        this.teamState = teamState;
    }


}
