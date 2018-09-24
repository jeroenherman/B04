package be.leerstad.jdbc;

import java.sql.*;

public class HelloJDBC {

    public static void main(String[] args) throws SQLException {
        // Step 1: Define driver ==> see vendor META-INF folder
        // Step 2: Define Connection String
        String connURL = "jdbc:mysql://172.16.1.11/cvo"; // jdbc:mysql://<host>/<dBaseName>
        //String connURL = "jdbc:mysql://localhost/cvo"; // jdbc:mysql://<host>/<dBaseName>
        // Step 3: Open Connection
        Connection myConnection = DriverManager.getConnection(connURL, "cvo", "123");
        // Step 4: Statement
        Statement stmt = myConnection.createStatement();
        // Step 5: Queries
        ResultSet rs = stmt.executeQuery("SELECT * FROM persons");
        // Step 6: Process results
        while (rs.next()) {
            System.out.println(rs.getString("name"));

        }
        // Step 7: close connection
        rs.close();
        stmt.close();
        myConnection.close();
        System.out.println("Bye bye");
    }

}

