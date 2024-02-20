import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDB {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create("mongodb+srv://martingoberg:root@jims.byniw4p.mongodb.net/?retryWrites=true&w=majority");
        System.out.println("MongoDB connected! Ignore error for logging.");
    }

}


