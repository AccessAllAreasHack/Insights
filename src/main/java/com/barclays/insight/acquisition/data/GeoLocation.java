package com.barclays.insight.acquisition.data;

public class GeoLocation {

    private double lat;
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = Double.valueOf(lat);
    }

    public double getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = Double.valueOf(lng);
    }
}
