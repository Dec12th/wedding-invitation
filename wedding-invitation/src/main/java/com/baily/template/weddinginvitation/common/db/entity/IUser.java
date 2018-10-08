package com.baily.template.weddinginvitation.common.db.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 注册用户表实体
 *
 */
@Entity
@Table(name = "T_USER")
@Data
public class IUser {
    @Id
    @Column(name = "id", nullable = false)
    private String id;//用户id

    @Column(name = "open_Id")
    private String openId;

    @Column(name = "avatar_Url")
    private String avatarUrl;

    @Column(name = "city")
    private String city;

    @Column(name = "nick_Name")
    private String nickName;

    @Column(name = "province")
    private String province;

    @Column(name = "create_Time")
    private String createTime;

    @Column(name = "update_Time")
    private String updateTime;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "phone")
    private String phone;
}