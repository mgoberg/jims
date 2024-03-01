import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Jims {
    private static MongoClient client;
    private static MongoCollection<Document> usersCollection;
    private static final DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Price", "Description", "Quantity"}, 0);

    // Live updates for co-Debugging
    public static void scheduleTableUpdate() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Run the updateTable method every 10 seconds
        scheduler.scheduleAtFixedRate(Jims::updateTableTask, 0, 10, TimeUnit.SECONDS);
    }

    private static void updateTableTask() {
        SwingUtilities.invokeLater(() -> {
            JTable table = getTable();
            DefaultTableModel updatedModel = getTableModel();
            table.setModel(updatedModel);
            table.repaint();
            System.out.println("Table updated");
        });
    }

    // Establish Database connection
    private static void connDB() {
        client = MongoClients.create("mongodb+srv://martingoberg:root@jims.byniw4p.mongodb.net/?retryWrites=true&w=majority");
    }

    // Load users table
    private static void loadUsers() {
        try {
            connDB();
            MongoDatabase database = client.getDatabase("users");
            usersCollection = database.getCollection("users");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load items table
    public static List<Document> loadItems() {
        List<Document> documents = new ArrayList<>();

        try {
            connDB();
            MongoDatabase database = client.getDatabase("items");
            MongoCollection<Document> collection = database.getCollection("items");

            documents = collection.find().into(new ArrayList<>());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return documents;
    }

    // Get tableModel
    static DefaultTableModel getTableModel() {
        List<Document> documents = loadItems();
        DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Price", "Description", "Quantity"}, 0);

        for (Document document : documents) {
            Object[] rowData = {document.get("name"), document.get("price"), document.get("desc"), document.get("qty")};
            model.addRow(rowData);
        }

        return model;
    }

    // Authenticate user
    public static boolean authenticateUser(String username, String password) {
        try {
            // Establish MongoDB connection
            loadUsers();

            // Handle
            if (usersCollection == null) {
                // Handle
                System.out.println("ERROR: MongoDB Conn authenticateUser");
                return false;
            }

            // Query for authenticating user
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
        connDB();
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

    // Retrieves the table
    public static JTable getTable() {
        JTable table = new JTable(model);
        DefaultTableModel updatedModel = getTableModel();
        table.setModel(updatedModel);
        table.repaint();
        table.setLayout(new BorderLayout());
        table.setBounds(600, 95, 570, 700);
        return table;
    }

    // Delete item from table / database
    public static void deleteItemFromMongoDB(int rowIndex) {
        try {
            connDB();
            MongoDatabase database = client.getDatabase("items");
            MongoCollection<Document> collection = database.getCollection("items");

            // Get the document ID from the selected row
            List<Document> documents = collection.find().into(new ArrayList<>());
            String itemId = documents.get(rowIndex).getObjectId("_id").toString();

            // Create a filter for the document to delete
            Bson filter = Filters.eq("_id", new ObjectId(itemId));

            // Delete the document from the collection
            collection.deleteOne(filter);

            System.out.println("Item deleted from MongoDB!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
