
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Display program for debugging purposes
                new JimsGUI().setVisible(false);
                new OverviewGUI().setVisible(false);
                new InventoryGUI().setVisible(true);
            }
        });

    }

}
