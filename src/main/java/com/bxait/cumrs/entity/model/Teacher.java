package com.bxait.cumrs.entity.model;

import javax.persistence.*;

@Entity
@Table(name = "T_TEACHER")
public class Teacher {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    //工号
    @Column(name = "TEAID",length = 20)
    private String teaId;

    @Column(name = "NAME",length = 20)
    private String name;
    //院系
    @Column(name = "DEPARTMENTS",length = 100)
    private String departments;

    //性别
    @Column(name = "SEX",length = 10)
    private String sex;

    //职称
    @Column(name = "TITLE",length = 100)
    private String title;

    //电话
    @Column(name = "PHONE",length = 11)
    private String phone;

    //邮箱
    @Column(name = "EMAIL",length = 30)
    private String email;
    //是否在团队
    @Column(name = "OCCUPY",length = 200)
    private String occupy;
    //老师类型
    @Column(name = "TYPE",length = 1)
    private String type;

    //是否锁定
    @Column(name = "LOCKED",length = 1)
    private String locked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOccupy() {
        return occupy;
    }

    public void setOccupy(String occupy) {
        this.occupy = occupy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }
}
