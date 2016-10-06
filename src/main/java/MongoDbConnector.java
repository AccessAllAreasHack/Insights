import com.mongodb.MongoClient;

/**
 * Created by stefletcher on 08/07/2016.
 */
public class MongoDbConnector {
  public void listDatabaseNames() {
    // To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
    // if it's a member of a replica set:

    // Get env variables from the docker container
    String mongoHost = System.getenv("MONGO_PORT_27017_TCP_ADDR");
    int mongoPort = Integer.parseInt(System.getenv("MONGO_PORT_27017_TCP_PORT"));

    MongoClient mongoClient = new MongoClient(mongoHost, mongoPort);

    for (String s : mongoClient.listDatabaseNames()) {
      System.out.println("#### " + s);
    }
    mongoClient.close();
  }
}
