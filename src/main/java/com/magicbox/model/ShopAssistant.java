package com.magicbox.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel("")
public class ShopAssistant implements Serializable {
    @ApiModelProperty("")
    private Long id;

    @ApiModelProperty("手机号")
    private String assistantMobile;

    @ApiModelProperty("店铺ID")
    private Long shopId;

    @ApiModelProperty("卖家ID")
    private Long sellerId;

    @ApiModelProperty("店员编号")
    private String assistantCode;

    @ApiModelProperty("状态(1-待审核,5-审核驳回,10-审核通过)")
    private Integer assistantStatus;

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

    public String getAssistantMobile() {
        return assistantMobile;
    }

    public void setAssistantMobile(String assistantMobile) {
        this.assistantMobile = assistantMobile;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getAssistantCode() {
        return assistantCode;
    }

    public void setAssistantCode(String assistantCode) {
        this.assistantCode = assistantCode;
    }

    public Integer getAssistantStatus() {
        return assistantStatus;
    }

    public void setAssistantStatus(Integer assistantStatus) {
        this.assistantStatus = assistantStatus;
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
        sb.append(", assistantMobile=").append(assistantMobile);
        sb.append(", shopId=").append(shopId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", assistantCode=").append(assistantCode);
        sb.append(", assistantStatus=").append(assistantStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}