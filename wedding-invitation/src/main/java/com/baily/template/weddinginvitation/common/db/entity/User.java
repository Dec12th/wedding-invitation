package com.baily.template.weddinginvitation.common.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @ClassName: User
 * @Description:
 * @author:大贝
 * @date:2018年10月07日 20:42
 */
@Entity
public class User {

    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column
    private String name;

    @Column
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
