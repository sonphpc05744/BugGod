/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PeachCoffee.utils;

import java.sql.*;

/**
 *
 * @author nguye
 */
public class JdbcHelper {
        static  String user = "sa";
        static String password = "huuphuc2004";
        static String url = "jdbc:sqlserver://localhost:1433;databaseName=Peach_Coffee;encrypt=true;trustServerCertificate=true;";
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement getStatement(String sql, Object... args) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = connection.prepareCall(sql); //Proc
        } else {
            pstmt = connection.prepareStatement(sql); // Sql
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }
    
    public static int update(String sql, Object... args){
        try {
            PreparedStatement stmt = getStatement(sql, args);
            try {
                return  stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static ResultSet query (String sql, Object... args){
        try {
            PreparedStatement stmt = getStatement(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Object value(String sql, Object... args){
        try {
            ResultSet rs = query(sql, args);
            if (rs.next()) {
                return rs.getObject(1);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //return null;
    }
}
