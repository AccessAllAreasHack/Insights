package com.barclays.insight.acquisition.data;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationsDataExtractor {

    private final String mongoHost = System.getProperty("spring.data.mongodb.host", "ec2-52-212-3-182.eu-west-1.compute.amazonaws.com");
    private final int mongoPort = Integer.parseInt(System.getProperty("spring.data.mongodb.port", "27017"));

    public Location getLocations() {
        final Location location = new Location();
        try (MongoClient mongo = new MongoClient(mongoHost, mongoPort)) {
            final MongoDatabase database = mongo.getDatabase("insights");
            final List<Result> results = new ArrayList<>();
            final MongoCollection<Document> collection = database.getCollection("customerTransaction");
            for (Document doc : collection.find()) {
                final String customer = doc.getString("customer");
                final String custLong = doc.getString("custLong");
                final String custLat = doc.getString("custLat");
                final String ageRange = doc.getString("ageRange");
                final String custSex = doc.getString("CustSex");
                final String salePrice = doc.getString("salePrice");
                final String prodDesc = doc.getString("prodDesc");
                final String quantity = doc.getString("quantity");
                final String merchLong = doc.getString("merchLong");
                final String merchLat = doc.getString("merchLat");

                final GeoLocation center = new GeoLocation();
                center.setLat(merchLat);
                center.setLng(merchLong);
                if (location.getCenter() != null) {
                    location.setCenter(center);
                }
                final Result result = new Result();

                final GeoLocation custLocation = new GeoLocation();
                custLocation.setLat(custLat);
                custLocation.setLng(custLong);
                result.setLocation(custLocation);
                result.setCustid(customer);
                result.setDetails(getDetails(ageRange, custSex, salePrice, prodDesc, quantity));

            }
            location.setResults(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    private String getDetails(final String ageRange, final String custSex, final String salePrice, final String prodDesc, final String quantity) {
        StringBuilder builder = new StringBuilder();
        final Map<String, String> details = new HashMap<>();
        details.put("age", ageRange);
        details.put("gender", custSex);
        details.put("totalspend", salePrice);
        details.put("item", prodDesc);
        details.put("qty", quantity);

        String separator = "";

        for (final Map.Entry<String, String> entry : details.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();

            builder = builder.append(separator).append(key).append("=").append(value);

            separator = ",";
        }
        return builder.toString();
    }

}
