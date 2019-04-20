package com.bxait.cumrs.entity;

public class Const {
    /**
     * 用户身份
     */
    //学生
    public static final String USER_IDENTITY_STU = "0";
    //老师
    public static final String USER_IDENTITY_TEA_B = "1";
    //教务
    public static final String USER_IDENTITY_TEA_C = "2";
    //管理员
    public static final String USER_IDENTITY_ADMIN = "3";

    /**
     * 是否在团队
     */
    //在
    public static final  String IN_THE_TEAM = "1";
    //不在
    public static final  String NOT_IN_THE_TEAM = "0";

    /**
     * 登陆权限
     */
    //学生
    public static final  String POWER_STU = "0";
    //老师
    public static final  String POWER_TEA = "1";
    //管理员
    public static final  String POWER_ADMIN = "2";

    /**
     * 学历
     */
    //专科
    public static final  String EDUCATION_SPECIALTY = "0";
    //本科
    public static final  String EDUCATION_COLLEGE = "1";

    /**
     * 是否锁定
     */
    //锁定
    public static final  String LOCKED = "1";
    //未锁定
    public static final  String NOT_LOCKED = "0";


    /**
     * 老师类型
     */
    //A类
    public static final String TEA_TYPE_B = "B";
    //B类
    public static final String TEA_TYPE_C = "C";

    /**
     * 队伍状态
     */
    //已创建
    public static final String TEAM_STATE_CREATE = "0";
    //待报名
    public static final String TEAM_STATE_VERIFY = "1";
    //报名成功
    public static final String TEAM_STATE_VERIFY_SUCCESS = "2";
    /**
     * 团队是否满员
     */
    //为满员
    public static final String VACANCY_NO = "0";
    //满员
    public static final String VACANCY_YES = "1";


    /**
     * 申请类型
     */
    //邀请入队
    public static final String VACANCY_INVITE = "0";
    //申请报名
    public static final String APPLYTYPE_ENROLL = "1";



    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
}
