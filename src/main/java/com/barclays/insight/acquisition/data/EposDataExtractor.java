package com.barclays.insight.acquisition.data;

import com.mongodb.BasicDBList;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Initial code to fetch raw data from EposNow and place into a Mongo DB.
 *
 * We would separate out fetch and write concerns when taking this code forward to allow for fungeability.
 */
@Service
public class EposDataExtractor {

    private final String mongoHost = System.getProperty("spring.data.mongodb.host", "ec2-52-212-3-182.eu-west-1.compute.amazonaws.com");
    private final int mongoPort = Integer.parseInt(System.getProperty("spring.data.mongodb.port", "27017"));

//    public static void main(String[] args) {
//
//        new EposDataExtractor().extract();
//
//    }

    public void extract() {

        try (MongoClient mongoClient = new MongoClient(mongoHost, mongoPort))
        {

            final MongoDatabase insightDatabase = mongoClient.getDatabase("insights");

            final MongoCollection<Document> transactionsCollection = insightDatabase.getCollection("transactions");
            final MongoCollection<Document> transactionItemsCollection = insightDatabase.getCollection("transactionItems");
            final MongoCollection<Document> productsCollection = insightDatabase.getCollection("products");
            final MongoCollection<Document> categoryCollection = insightDatabase.getCollection("category");
            final MongoCollection<Document> deviceCollection = insightDatabase.getCollection("device");
            final MongoCollection<Document> locationCollection = insightDatabase.getCollection("location");

            transactionsCollection.drop();
            insertData(transactionsCollection, fetchFromEpos("https://api.eposnowhq.com/api/V2/Transaction/"));

            transactionItemsCollection.drop();
            insertData(transactionItemsCollection, fetchFromEpos("https://api.eposnowhq.com/api/V2/TransactionItem/"));

            productsCollection.drop();
            insertData(productsCollection, fetchFromEpos("https://api.eposnowhq.com/api/V2/Product/"));

            categoryCollection.drop();
            insertData(categoryCollection, fetchFromEpos("https://api.eposnowhq.com/api/V2/Category/"));

            deviceCollection.drop();
            insertData(deviceCollection, fetchFromEpos("https://api.eposnowhq.com/api/V2/Device/"));

            locationCollection.drop();
            insertData(locationCollection, fetchFromEpos("https://api.eposnowhq.com/api/V2/Location/"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void insertData(MongoCollection<Document> collection, String items) {

        BasicDBList output = (BasicDBList) JSON.parse(items);
        output.stream().map(item -> Document.parse(item.toString())).forEach(doc -> collection.insertOne(doc));

    }

    private String fetchFromEpos(String uri) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization","Basic SkpCQk85OU5aODhMRlZHTEdNWDVNN040TUFJRlQxNFo6SVI5S0NHSldWUElWT0VKWjVQMVIzU0NFU1lUUVpFQ04=");
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        return result.getBody();
    }


}
