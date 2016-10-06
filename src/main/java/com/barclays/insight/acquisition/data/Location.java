package com.barclays.insight.acquisition.data;

import java.util.List;

public class Location {

    private GeoLocation center;
    private List<Result> results;

    public GeoLocation getCenter() {
        return center;
    }

    public void setCenter(GeoLocation center) {
        this.center = center;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
