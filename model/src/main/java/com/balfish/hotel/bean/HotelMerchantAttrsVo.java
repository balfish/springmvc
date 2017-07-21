package com.balfish.hotel.bean;

import com.balfish.common.utils.diff.JaversBean;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class HotelMerchantAttrsVo extends MerchantAttrsVo {
    private String gradeName;
    private Integer star;
    private String starName;
    private String brand;
    private String area;
    private GeoPoint googlePoint;
    private List<String> businessDistricts;
    private String detail;
    private String fax;
    private String mail;
    private String oldName;
    private List<String> typeNames;

    public List<String> getTypeNames() {
        return typeNames;
    }

    public HotelMerchantAttrsVo setTypeNames(List<String> typeNames) {
        this.typeNames = Lists.newArrayList(typeNames);
        return this;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public GeoPoint getGooglePoint() {
        return googlePoint;
    }

    public void setGooglePoint(GeoPoint googlePoint) {
        this.googlePoint = googlePoint;
    }

    public List<String> getBusinessDistricts() {
        return businessDistricts;
    }

    public void setBusinessDistricts(List<String> businessDistricts) {
        this.businessDistricts = Lists.newArrayList(businessDistricts);
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelMerchantAttrsVo that = (HotelMerchantAttrsVo) o;

        if (gradeName != null ? !gradeName.equals(that.gradeName) : that.gradeName != null) return false;
        if (star != null ? !star.equals(that.star) : that.star != null) return false;
        if (starName != null ? !starName.equals(that.starName) : that.starName != null) return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        if (googlePoint != null ? !googlePoint.equals(that.googlePoint) : that.googlePoint != null) return false;
        if (businessDistricts != null ? !businessDistricts.equals(that.businessDistricts) : that.businessDistricts != null)
            return false;
        if (detail != null ? !detail.equals(that.detail) : that.detail != null) return false;
        if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (oldName != null ? !oldName.equals(that.oldName) : that.oldName != null) return false;
        return !(typeNames != null ? !typeNames.equals(that.typeNames) : that.typeNames != null);

    }

    @Override
    public int hashCode() {
        int result = gradeName != null ? gradeName.hashCode() : 0;
        result = 31 * result + (star != null ? star.hashCode() : 0);
        result = 31 * result + (starName != null ? starName.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (googlePoint != null ? googlePoint.hashCode() : 0);
        result = 31 * result + (businessDistricts != null ? businessDistricts.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (oldName != null ? oldName.hashCode() : 0);
        result = 31 * result + (typeNames != null ? typeNames.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


    public static void main(String[] args) {
        MerchantVo vo = new MerchantVo();
        vo.setSeqNumber("seq001");
        HotelMerchantAttrsVo attrsVo = new HotelMerchantAttrsVo();
        HashMap<String, String> extraAttr = Maps.newHashMap();
        extraAttr.put("ext1", "aaa");
        extraAttr.put("ext2", "bbbb");
        attrsVo.setExtraAttr(extraAttr);
        attrsVo.setBusinessDistricts(Arrays.asList("district1", "dis1123"));
        attrsVo.setGooglePoint(GeoPoint.fromLatLonString("1,1"));
        vo.setBaiduPoint(GeoPoint.fromLatLonString("1,2"));
        vo.setAttrs(attrsVo);
        vo.setGrade(null);
        attrsVo.setArea("area01");

        MerchantVo vo2 = new MerchantVo(vo);
        HotelMerchantAttrsVo attrsVo2 = new HotelMerchantAttrsVo();
        attrsVo2.setBusinessDistricts(Arrays.asList("ccss", "asdf"));
        attrsVo2.setArea("area02");
        Map<String, String> extraAttr2 = (Map<String, String>) extraAttr.clone();
        extraAttr2.put("ext3", "aaa");
        attrsVo2.setExtraAttr(extraAttr2);
        attrsVo2.setGooglePoint(null);
        vo2.setAttrs(attrsVo2);
        vo2.setSeqNumber("seq002");
        vo2.setGrade(2);

        JaversBean javersBean = new JaversBean();
        System.out.println(javersBean.diff(vo, vo2));
    }
}
