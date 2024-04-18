package com.qywk.community.pojo.dto;

import lombok.Data;

/**
 * @author: cxq
 * @introduction: 用户教务系统的信息实体类
 * @date: 2023/3/27
 */
@Data
public class SchoolUserInfoDTO {

    /**
     * 学号/工号
     * */
    private String user_id;

    /**
     * 姓名
     * */
    private String name;

    /**
     * 性别
     * */
    private String gender;

    /**
     * 校区
     * */
    private String campus;

    /**
     * 身份证号
     * */
    private String id_no;

    /**
     * 年级
     * */
    private String grade;

    /**
     * 专业
     * */
    private String major;

    /**
     * 班级
     * */
    private String classes;

    /**
     * 辅导员的id
     * */
    private String instructor_id;

    /**
     * 辅导员的姓名
     * */
    private String instructor_name;

    /**
     * 部门/学院
     * */
    private String department;

    /**
     * 状态信息(很奇怪)
     * */
    private String state;

    /**
     * 学生的信息(是否在籍)
     * */
    private String student_state;

}
