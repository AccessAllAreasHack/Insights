package com.barclays.insight.acquisition.web;

import com.barclays.insight.acquisition.data.GeoLocation;
import com.barclays.insight.acquisition.data.Location;
import com.barclays.insight.acquisition.data.Result;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
                results.add(getResult(center, j, String.valueOf(i)));
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
            results.add(getResult(center, i, id));
        }
        final Location locations = new Location();
        locations.setCenter(center);
        locations.setResults(results);

        return locations;
    }

    private Result getResult(final GeoLocation centre, final int id, final String custId) {

        final Result result = new Result();
        result.setCustid(custId);
        result.setLocation(getLocation(centre, 2000));
        result.setDetails("item=sandwich ,qty=" + id + ", totalspend=15.75 ,sex=f, age=45");
        return result;
    }

    private GeoLocation getLocation(final GeoLocation centre, int radius) {
        Random random = new Random();

        // Convert radius from meters to degrees
        double radiusInDegrees = radius / 111000f;

        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        // Adjust the x-coordinate for the shrinking of the east-west distances
        double new_x = x / Math.cos(Double.valueOf(centre.getLat()));

        double foundLongitude = new_x + Double.valueOf(centre.getLng());
        double foundLatitude = y + Double.valueOf(centre.getLat());
        final GeoLocation location = new GeoLocation();
        location.setLat(String.valueOf(foundLatitude));
        location.setLng(String.valueOf(foundLongitude));
        return location;
    }

}
