import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

@SuppressWarnings("restriction")
@WebServlet("/databaselistinfoservlet/*")
public class DatabaseInfoServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        System.out.println("Inside doGet method if DatabaseInfoServlet");

        String mongoHost = System.getenv("MONGO_PORT_27017_TCP_ADDR");
        int mongoPort = Integer.parseInt(System.getenv("MONGO_PORT_27017_TCP_PORT"));

        
        MongoClient mongoClient = new MongoClient(mongoHost, mongoPort);
        
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<p>List of databases:</p>");
        writer.println("<ul>");
                       
        for (String s : mongoClient.listDatabaseNames()) {
            writer.println("<li>" +s + "</li>");
        }
        
        writer.println("</ul>");
        writer.println("<p><a href='"+req.getContextPath()+"/index.jsp'>Back</a></p>");
        writer.println("</body>");
        writer.println("</html>");
    }
}