package com.magicbox.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel("")
public class Frame implements Serializable {
    @ApiModelProperty("")
    private Long id;

    @ApiModelProperty("店铺编号")
    private String shopCode;

    @ApiModelProperty("框架编号")
    private String frameCode;

    @ApiModelProperty("框架状态(1-正常)")
    private Integer frameStatus;

    @ApiModelProperty("框架型号")
    private String frameModel;

    @ApiModelProperty("框架卡号")
    private String cardCode;

    @ApiModelProperty("网卡流量")
    private String internetFlow;

    @ApiModelProperty("出厂时间")
    private Date productTime;

    @ApiModelProperty("")
    private Date createTime;

    @ApiModelProperty("")
    private Date updateTime;

    @ApiModelProperty("")
    private String createUser;

    @ApiModelProperty("")
    private String updateUser;

    @ApiModelProperty("运营商")
    private String networkOperator;

    @ApiModelProperty("价格(单位：分)")
    private Integer price;

    @ApiModelProperty("时限(单位：秒)")
    private Integer timeLimit;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("开通时间")
    private Date openTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getFrameCode() {
        return frameCode;
    }

    public void setFrameCode(String frameCode) {
        this.frameCode = frameCode;
    }

    public Integer getFrameStatus() {
        return frameStatus;
    }

    public void setFrameStatus(Integer frameStatus) {
        this.frameStatus = frameStatus;
    }

    public String getFrameModel() {
        return frameModel;
    }

    public void setFrameModel(String frameModel) {
        this.frameModel = frameModel;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getInternetFlow() {
        return internetFlow;
    }

    public void setInternetFlow(String internetFlow) {
        this.internetFlow = internetFlow;
    }

    public Date getProductTime() {
        return productTime;
    }

    public void setProductTime(Date productTime) {
        this.productTime = productTime;
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

    public String getNetworkOperator() {
        return networkOperator;
    }

    public void setNetworkOperator(String networkOperator) {
        this.networkOperator = networkOperator;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopCode=").append(shopCode);
        sb.append(", frameCode=").append(frameCode);
        sb.append(", frameStatus=").append(frameStatus);
        sb.append(", frameModel=").append(frameModel);
        sb.append(", cardCode=").append(cardCode);
        sb.append(", internetFlow=").append(internetFlow);
        sb.append(", productTime=").append(productTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", networkOperator=").append(networkOperator);
        sb.append(", price=").append(price);
        sb.append(", timeLimit=").append(timeLimit);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", openTime=").append(openTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}