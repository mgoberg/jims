
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
                new JimsGUI().setVisible(true);
                new OverviewGUI().setVisible(false);
                new InventoryGUI().setVisible(false);
                // TODO DB Loader må her, vi må skrive en metode i Jims som heter loadDatabase, som "spawner" databasen på et table som ikke brukes.
                new PreLoader(Jims.getTable()); // TODO Byttes ut med Jims.getTable() så fort vi har skrevet en metode som spawner db
                PreLoader.worker.execute();

            }
        });

    }

}
