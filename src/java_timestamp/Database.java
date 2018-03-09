
package java_timestamp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.control.Label;


public class Database {
    
    private static final String USERNAME = "access";
    private static final String PASSWORD = "Illusion16";
    private static final String CONN_STRING = 
            "jdbc:mysql://localhost/java_timestamp";
    
    public static void main(String[] args) throws SQLException {
       
    }
    public static String getLast() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String last = null;
        
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM files WHERE id=(SELECT MAX(id) FROM files)");
            
            int id = rs.getInt("id");
            String filename = rs.getString("filename");
            
            last = id + ": " + filename;
            
        } catch (SQLException ex) {
            System.err.println(ex);
            
        } finally {
            if (conn != null) {
                conn.close();  
            }
      
        }
        return last;
    }
    public static ArrayList<String> getAll() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
     
        ArrayList<String> allRows = new ArrayList<String>();
   
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM files");

            while(rs.next()) {
                
                int id = rs.getInt("id");
                String name = rs.getString("filename");
                
                String row = id + ": " + name;
                
                allRows.add(row);
                
            }
           
        } catch (SQLException ex) {
            System.err.println(ex);
            
        } finally {
            if (conn != null) {
                conn.close();  
            }
      
        }
        System.out.println(allRows);
        return allRows;
    }
    
    public static void addFile(String filename) throws SQLException {
        
        Connection conn = null;
        Statement stmt = null;
        String sql =         
            "INSERT INTO files (filename) " +
            "VALUES ('" + filename + "')";
        
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql); 
            
        } catch (SQLException ex) {
            System.err.println(ex);
            
        } finally {
            if (conn != null) {
                conn.close();
            }
            
        }
        
    }
    
}

