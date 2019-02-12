package com.magicbox.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel("")
public class Member implements Serializable {
    @ApiModelProperty("")
    private Long id;

    @ApiModelProperty("卖家ID")
    private Long sellerId;

    @ApiModelProperty("店员ID")
    private Long shopAssistantId;

    @ApiModelProperty("微信/支付宝openId")
    private String openId;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("真实姓名")
    private String realname;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("出生日期")
    private Date birthDate;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像照片")
    private String photo;

    @ApiModelProperty("注册时间")
    private Date registerTime;

    @ApiModelProperty("最近登录时间")
    private Date lastLoginTime;

    @ApiModelProperty("")
    private Date createTime;

    @ApiModelProperty("")
    private Date updateTime;

    @ApiModelProperty("")
    private String createUser;

    @ApiModelProperty("")
    private String updateUser;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getShopAssistantId() {
        return shopAssistantId;
    }

    public void setShopAssistantId(Long shopAssistantId) {
        this.shopAssistantId = shopAssistantId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", shopAssistantId=").append(shopAssistantId);
        sb.append(", openId=").append(openId);
        sb.append(", idCard=").append(idCard);
        sb.append(", realname=").append(realname);
        sb.append(", nickname=").append(nickname);
        sb.append(", gender=").append(gender);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", email=").append(email);
        sb.append(", photo=").append(photo);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}