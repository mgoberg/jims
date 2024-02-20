import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jims {
    public static boolean authenticateUser(String username, String password) {
        try {
            // Establish MongoDB connection
            MongoClient client = MongoClients.create("mongodb+srv://martingoberg:root@jims.byniw4p.mongodb.net/?retryWrites=true&w=majority");
            System.out.println("MongoDB connected! Ignore error for logging.");
            MongoDatabase database = client.getDatabase("users");
            MongoCollection<Document> usersCollection = database.getCollection("users");

            // Query for user authentication
            Document userQuery = new Document("username", username)
                    .append("password", password);

            // Check if the user exists in the database
            long userCount = usersCollection.countDocuments(userQuery);

            // Close MongoDB connection
            client.close();

            return userCount > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

