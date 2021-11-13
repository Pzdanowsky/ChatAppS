package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {
    private static Connection conn;

    private static final String urlDatabase = "jdbc:mysql://plan-psk.cba.pl/zdanek312";
    private static final String userName = "chatapp";
    private static final String password = "Qwerty312";

   // private static DatabaseMetaData DriverManager = null;

   // private static DriverManager driverManager;

    static
    {
        try{
            // Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(urlDatabase,userName,password);

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Connection Error");
        }

    }

    public static Connection getConn(){
        return conn;
    }

}
