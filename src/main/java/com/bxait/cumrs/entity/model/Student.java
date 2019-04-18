package com.bxait.cumrs.entity.model;

import javax.persistence.*;

@Entity
@Table(name = "T_STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    //姓名
    @Column(name = "NAME",length = 20)
    private String name;
    //年龄
    @Column(name = "AGE")
    private String  age;

    @Column(name = "STUID",length = 20)
    private String stuid;
    //性别
    @Column(name = "SEX",length = 10)
    private String sex;
    //院系
    @Column(name = "DEPARTMENTS",length = 100)
    private String departments;
    //电话
    @Column(name = "PHONE",length = 11)
    private String phone;
    //邮箱
    @Column(name = "EMAIL",length = 30)
    private String email;
    //特长
    @Column(name = "SPECIALITY",length = 200)
    private String speciality;
    //学历
    @Column(name = "EDUCATION",length = 10)
    private String education;
    //备注
    @Column(name = "REMARKS",length = 200)
    private String remarks;
    //是否在队伍中
    @Column(name = "OCCUPY",length = 1)
    private String occupy;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOccupy() {
        return occupy;
    }

    public void setOccupy(String occupy) {
        this.occupy = occupy;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }
}
