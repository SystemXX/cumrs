package com.bxait.cumrs.entity.model;

import javax.persistence.*;

@Entity
@Table(name = "T_USER")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME",length = 20)
    private String userName;

    @Column(name = "PASSWORD",length = 20)
    private String passWord;

    //用户身份
    @Column(name = "IDENTITY",length = 20)
    private String identity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
