package com.bxait.cumrs.entity.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_APPLY")
public class Apply {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    //申请人
    @Column(name = "INVITER",length = 50)
    private String inviter;
    //接受人
    @Column(name = "INVITED",length = 50)
    private String Invited;

    //申请人姓名
    @Column(name = "INVITERNAME",length = 50)
    private String inviterName;

    //接受人姓名
    @Column(name = "INVITEDNAME",length = 50)
    private String InvitedName;

    //申请队伍名称
    @Column(name = "TEAMNAME",length = 50)
    private String teamName;

    //申请时间
    @Column(name = "APPLYDATE")
    private Date applyDate;

    //申请类型 0邀请入队 1申请报名
    @Column(name = "APPLYTYPE",length = 1)
    private String applyType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInviter() {
        return inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter;
    }

    public String getInvited() {
        return Invited;
    }

    public void setInvited(String invited) {
        Invited = invited;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getInviterName() {
        return inviterName;
    }

    public void setInviterName(String inviterName) {
        this.inviterName = inviterName;
    }

    public String getInvitedName() {
        return InvitedName;
    }

    public void setInvitedName(String invitedName) {
        InvitedName = invitedName;
    }
}
