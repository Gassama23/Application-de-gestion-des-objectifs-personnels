package org.odk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static final String URL =
            "jdbc:mysql://localhost:3306/gestion_objectifs";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    // Méthode de connexion
    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Connexion à la base réussie !");

            return connection;

        } catch (ClassNotFoundException e) {

            System.out.println("Driver JDBC introuvable !");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Erreur de connexion à la base !");
            e.printStackTrace();
        }

        return null;
    }
	
}
