package com.magicbox.model;

import java.io.Serializable;
import java.util.Date;

public class Shop implements Serializable {
    private Long id;

    private String shopCode;

    private String shopName;

    private String shopDesc;

    private Integer shopStatus;

    private Long sellerId;

    private String shopProvinceCode;

    private String shopProvinceName;

    private String shopCityCode;

    private String shopCityName;

    private String shopDistrictCode;

    private String shopDistrictName;

    private String shopCountyCode;

    private String shopCountyName;

    private String shopAddress;

    private String shopLongitude;

    private String shopLatitude;

    private String shopPhoto;

    private String shopPhone;

    private String shopCategories;

    private String shopProperties;

    private Integer shopOpenTime;

    private Integer shopCloseTime;

    private Date createTime;

    private Date updateTime;

    private String createUser;

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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public Integer getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getShopProvinceCode() {
        return shopProvinceCode;
    }

    public void setShopProvinceCode(String shopProvinceCode) {
        this.shopProvinceCode = shopProvinceCode;
    }

    public String getShopProvinceName() {
        return shopProvinceName;
    }

    public void setShopProvinceName(String shopProvinceName) {
        this.shopProvinceName = shopProvinceName;
    }

    public String getShopCityCode() {
        return shopCityCode;
    }

    public void setShopCityCode(String shopCityCode) {
        this.shopCityCode = shopCityCode;
    }

    public String getShopCityName() {
        return shopCityName;
    }

    public void setShopCityName(String shopCityName) {
        this.shopCityName = shopCityName;
    }

    public String getShopDistrictCode() {
        return shopDistrictCode;
    }

    public void setShopDistrictCode(String shopDistrictCode) {
        this.shopDistrictCode = shopDistrictCode;
    }

    public String getShopDistrictName() {
        return shopDistrictName;
    }

    public void setShopDistrictName(String shopDistrictName) {
        this.shopDistrictName = shopDistrictName;
    }

    public String getShopCountyCode() {
        return shopCountyCode;
    }

    public void setShopCountyCode(String shopCountyCode) {
        this.shopCountyCode = shopCountyCode;
    }

    public String getShopCountyName() {
        return shopCountyName;
    }

    public void setShopCountyName(String shopCountyName) {
        this.shopCountyName = shopCountyName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopLongitude() {
        return shopLongitude;
    }

    public void setShopLongitude(String shopLongitude) {
        this.shopLongitude = shopLongitude;
    }

    public String getShopLatitude() {
        return shopLatitude;
    }

    public void setShopLatitude(String shopLatitude) {
        this.shopLatitude = shopLatitude;
    }

    public String getShopPhoto() {
        return shopPhoto;
    }

    public void setShopPhoto(String shopPhoto) {
        this.shopPhoto = shopPhoto;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopCategories() {
        return shopCategories;
    }

    public void setShopCategories(String shopCategories) {
        this.shopCategories = shopCategories;
    }

    public String getShopProperties() {
        return shopProperties;
    }

    public void setShopProperties(String shopProperties) {
        this.shopProperties = shopProperties;
    }

    public Integer getShopOpenTime() {
        return shopOpenTime;
    }

    public void setShopOpenTime(Integer shopOpenTime) {
        this.shopOpenTime = shopOpenTime;
    }

    public Integer getShopCloseTime() {
        return shopCloseTime;
    }

    public void setShopCloseTime(Integer shopCloseTime) {
        this.shopCloseTime = shopCloseTime;
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
        sb.append(", shopName=").append(shopName);
        sb.append(", shopDesc=").append(shopDesc);
        sb.append(", shopStatus=").append(shopStatus);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", shopProvinceCode=").append(shopProvinceCode);
        sb.append(", shopProvinceName=").append(shopProvinceName);
        sb.append(", shopCityCode=").append(shopCityCode);
        sb.append(", shopCityName=").append(shopCityName);
        sb.append(", shopDistrictCode=").append(shopDistrictCode);
        sb.append(", shopDistrictName=").append(shopDistrictName);
        sb.append(", shopCountyCode=").append(shopCountyCode);
        sb.append(", shopCountyName=").append(shopCountyName);
        sb.append(", shopAddress=").append(shopAddress);
        sb.append(", shopLongitude=").append(shopLongitude);
        sb.append(", shopLatitude=").append(shopLatitude);
        sb.append(", shopPhoto=").append(shopPhoto);
        sb.append(", shopPhone=").append(shopPhone);
        sb.append(", shopCategories=").append(shopCategories);
        sb.append(", shopProperties=").append(shopProperties);
        sb.append(", shopOpenTime=").append(shopOpenTime);
        sb.append(", shopCloseTime=").append(shopCloseTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}