package ma.enset.javafxwithdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDBSingleton {
    private static Connection connection;
    static {
        /*THIS One will be called only 1 time when the class is loaded*/
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_STOCK","root","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }


}
