package com.balfish.hotel.bean;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.primitives.Doubles;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class GeoPoint implements Serializable {

    private static final long serialVersionUID = 449059562419587749L;
    private static final Splitter COMMA_AS_SEPARATOR_SPLITTER = Splitter.on(",").omitEmptyStrings().trimResults();

    /**
     * 纬度
     */
    private double latitude;
    /**
     * 经度
     */
    private double longitude;

    public GeoPoint() {
    }

    public GeoPoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * 根据 经度,纬度 字符串构造GeoPoint
     *
     * @param lonLatString
     * @return
     */
    public static GeoPoint fromLonLatString(String lonLatString) {
        if (StringUtils.isBlank(lonLatString)) {
            return null;
        }

        List<String> lonLat = COMMA_AS_SEPARATOR_SPLITTER.splitToList(lonLatString);
        Preconditions.checkArgument(lonLat.size() == 2, "无效的经度纬度数据" + lonLatString);
        return new GeoPoint(Double.parseDouble(lonLat.get(1)), Double.parseDouble(lonLat.get(0)));
    }

    /**
     * 根据 纬度,经度 字符串构造GeoPoint
     *
     * @param latLonString
     * @return
     */
    public static GeoPoint fromLatLonString(String latLonString) {
        if (StringUtils.isBlank(latLonString)) {
            return null;
        }

        List<String> latLon = COMMA_AS_SEPARATOR_SPLITTER.splitToList(latLonString);
        Preconditions.checkArgument(latLon.size() == 2, "无效的纬度经度数据" + latLonString);
        return new GeoPoint(Double.parseDouble(latLon.get(0)), Double.parseDouble(latLon.get(1)));
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        GeoPoint that = (GeoPoint) other;

        return Objects.equal(getLatitude(), that.getLatitude())
                && Objects.equal(getLongitude(), that.getLongitude());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getLatitude(), getLongitude());
    }

    @Override
    public String toString() {
        return latLonString();
    }

    /**
     * 经度,纬度
     *
     * @return
     */
    public String lonLatString() {
        return Doubles.join(",", longitude, latitude);
    }

    /**
     * 纬度,经度
     *
     * @return
     */
    public String latLonString() {
        return Doubles.join(",", latitude, longitude);
    }
}
