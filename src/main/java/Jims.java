import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public static void insertItemIntoMongoDB(String itemName, double price, String desc, int qty) {
        MongoClient client = MongoClients.create("mongodb+srv://martingoberg:root@jims.byniw4p.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = client.getDatabase("items");
        MongoCollection<Document> collection = database.getCollection("items");

        // Create a new document for the item
        Document document = new Document("name", itemName)
                .append("price", price)
                .append("desc", desc)
                .append("qty", qty);

        // Insert the document into the collection
        collection.insertOne(document);

        System.out.println("Item inserted successfully!");
    }
    public static void updateJTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        MongoClient client = MongoClients.create("mongodb+srv://martingoberg:root@jims.byniw4p.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase database = client.getDatabase("items");
        MongoCollection<Document> collection = database.getCollection("items");

        collection.find().forEach(document -> {
            Object[] rowData = {document.get("name"), document.get("price"), document.get("desc"), document.get("qty")};
            model.addRow(rowData);
        });

        System.out.println("JTable updated successfully!");
    }
}



