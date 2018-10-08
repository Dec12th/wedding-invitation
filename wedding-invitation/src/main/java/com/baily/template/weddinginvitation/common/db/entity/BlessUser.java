package com.baily.template.weddinginvitation.common.db.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_BLESS_USER")
public class BlessUser {
	@Id
	@Column(name="id",nullable = false)
	private String id;

    @Column(name="create_time")
	private String createTime;

    @Column(name="open_id")
	private String openId;

    @Column(name="nick_image")
	private String nickImage;

    @Column(name="nick_name")
	private String nickName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickImage() {
        return nickImage;
    }

    public void setNickImage(String nickImage) {
        this.nickImage = nickImage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
