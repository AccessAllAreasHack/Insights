package com.barclays.insight.acquisition.web;

import com.barclays.insight.acquisition.data.GeoLocation;
import com.barclays.insight.acquisition.data.Location;
import com.barclays.insight.acquisition.data.Result;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class LocationsResource {

    @ResponseBody
    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public Location getAllLocations() {
        final GeoLocation center = new GeoLocation();
        center.setLat("53.4777339");
        center.setLng("-2.2447379");
        final List<Result> results = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                results.add(getResult(j, String.valueOf(i)));
            }
        }
        final Location locations = new Location();
        locations.setCenter(center);
        locations.setResults(results);

        return locations;
    }

    @ResponseBody
    @RequestMapping(value = "/locations/{id}", method = RequestMethod.GET)
    public Location getLocations(@PathVariable("id") final String id) {
        final GeoLocation center = new GeoLocation();
        center.setLat("53.527814");
        center.setLng("-2.4557895");
        final List<Result> results = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            results.add(getResult(i, id));
        }
        final Location locations = new Location();
        locations.setCenter(center);
        locations.setResults(results);

        return locations;
    }

    private Result getResult(final int id, final String custId) {

        final Result result = new Result();
        final GeoLocation location = new GeoLocation();
        location.setLat("53.47773" + (39 - id) * 12);
        location.setLng("-2.24473" + (79 - id) * 11);
        result.setCustid(custId);
        result.setLocation(location);
        result.setDetails("item=sandwich ,qty=" + id + ", totalspend=15.75 ,sex=f, age=45");
        return result;
    }

}
