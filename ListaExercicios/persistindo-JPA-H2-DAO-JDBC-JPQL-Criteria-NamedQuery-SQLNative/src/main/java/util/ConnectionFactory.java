package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public static Connection getConnection(){
        try {

//            return DriverManager.getConnection("jdbc:h2:mem:test","user","");
            return DriverManager.getConnection("jdbc:postgresql://localhost/database","postgres","postgres");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

}
