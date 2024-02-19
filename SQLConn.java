
import java.sql.*;
import java.sql.DriverManager;

public class SQLConn {
    public static void main(String[] args) {

        try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:443/jims", "root", "root");
        System.out.println("tHe BlUeToOtH dEvIcE iS cOnNeCtEd SuCceSsfUlLY!!");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from user");

        while (resultSet.next()){
            System.out.println(resultSet.getString("username"));
        }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Connection failed");
        }
    }
}