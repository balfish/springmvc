package com.balfish.hotel.bean;

import java.sql.Timestamp;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class MerchantVo {

    private Integer id;

    private String serialNumber;

    private String relatedSerialNumber;

    private String seqNumber;

    private String name;

    private String address;

    private String telephone;

    private String cityCode;


    private GeoPoint baiduPoint;


    private Integer grade;

    private MerchantAttrsVo attrs;


    private String invalidReason;


    private Timestamp createTime;

    private String creatorName;

    private Timestamp updateTime;

    public MerchantVo() {
    }

    public MerchantVo(MerchantVo other) {
        this.id = other.id;
        this.serialNumber = other.serialNumber;
        this.relatedSerialNumber = other.relatedSerialNumber;
        this.seqNumber = other.seqNumber;
        this.name = other.name;
        this.address = other.address;
        this.telephone = other.telephone;
        this.cityCode = other.cityCode;
        this.baiduPoint = other.baiduPoint;
        this.grade = other.grade;
        this.attrs = other.attrs;
        this.invalidReason = other.invalidReason;
        this.createTime = other.createTime;
        this.creatorName = other.creatorName;
        this.updateTime = other.updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getRelatedSerialNumber() {
        return relatedSerialNumber;
    }

    public void setRelatedSerialNumber(String relatedSerialNumber) {
        this.relatedSerialNumber = relatedSerialNumber;
    }

    public String getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(String seqNumber) {
        this.seqNumber = seqNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public GeoPoint getBaiduPoint() {
        return baiduPoint;
    }

    public void setBaiduPoint(GeoPoint baiduPoint) {
        this.baiduPoint = baiduPoint;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public MerchantAttrsVo getAttrs() {
        return attrs;
    }

    public void setAttrs(MerchantAttrsVo attrs) {
        this.attrs = attrs;
    }

    public String getInvalidReason() {
        return invalidReason;
    }

    public void setInvalidReason(String invalidReason) {
        this.invalidReason = invalidReason;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}

