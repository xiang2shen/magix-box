package com.magicbox.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel("")
public class Box implements Serializable {
    @ApiModelProperty("")
    private Long id;

    @ApiModelProperty("店铺编号")
    private String shopCode;

    @ApiModelProperty("框架编号")
    private String frameCode;

    @ApiModelProperty("盒子编号")
    private String boxCode;

    @ApiModelProperty("商品编号")
    private String productCode;

    @ApiModelProperty("盒子位置")
    private Integer boxPosition;

    @ApiModelProperty("盒子状态(1-正常)")
    private Integer boxStatus;

    @ApiModelProperty("盒子型号")
    private String boxModel;

    @ApiModelProperty("盒子容量")
    private Integer capacity;

    @ApiModelProperty("盒子里商品的库存")
    private Integer productStock;

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

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getBoxPosition() {
        return boxPosition;
    }

    public void setBoxPosition(Integer boxPosition) {
        this.boxPosition = boxPosition;
    }

    public Integer getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(Integer boxStatus) {
        this.boxStatus = boxStatus;
    }

    public String getBoxModel() {
        return boxModel;
    }

    public void setBoxModel(String boxModel) {
        this.boxModel = boxModel;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
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
        sb.append(", shopCode=").append(shopCode);
        sb.append(", frameCode=").append(frameCode);
        sb.append(", boxCode=").append(boxCode);
        sb.append(", productCode=").append(productCode);
        sb.append(", boxPosition=").append(boxPosition);
        sb.append(", boxStatus=").append(boxStatus);
        sb.append(", boxModel=").append(boxModel);
        sb.append(", capacity=").append(capacity);
        sb.append(", productStock=").append(productStock);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}