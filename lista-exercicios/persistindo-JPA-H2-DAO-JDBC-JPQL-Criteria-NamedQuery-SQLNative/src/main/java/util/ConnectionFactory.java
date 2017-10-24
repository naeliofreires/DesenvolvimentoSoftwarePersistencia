package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;

public class ConnectionFactory {

    private static String URL="jdbc:postgresql://localhost/";
    private static String NAME_DB="database";
    protected static String USER_DB="postgres";
    protected static String PASSWORD_DB="postgres";

    public static Connection getConnection(){
        try {

            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl(URL + NAME_DB);
            cpds.setUser(USER_DB);
            cpds.setPassword(PASSWORD_DB);
            cpds.setMinPoolSize(3);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
//            return DriverManager.getConnection("jdbc:h2:mem:test","user","");
//            return DriverManager.getConnection(URL + NAME_DB,USER_DB,PASSWORD_DB);
            return 	cpds.getConnection();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static void resultSetClose(ResultSet rs){
        try{
            if (rs != null) rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void statementClose(Statement stmt){
        try{
            if (stmt != null) stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void connectionClose(Connection con) {
        try{
            if (con != null)
                con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void BeginTransaction(){
        try{
            getConnection().setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void Commit() {
        try {
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void rollback() {
        if(getConnection() != null){
            try {
                getConnection().rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
