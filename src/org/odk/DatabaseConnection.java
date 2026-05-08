package org.odk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	// URL de la base
    private static final String URL =
            "jdbc:mysql://localhost:3306/gestion_objectifs";

    // utilisateur MySQL
    private static final String USER = "root";

    // mot de passe MySQL
    private static final String PASSWORD = "1234";

    // connexion unique
    private static Connection connection;

    // constructeur privé
    private DatabaseConnection() {
    }

    // méthode singleton
    public static Connection getConnection() {

        try {

            // si connexion n'existe pas
            if (connection == null || connection.isClosed()) {

                // charger le driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // créer connexion
                connection = DriverManager.getConnection(
                        URL,
                        USER,
                        PASSWORD
                );

                System.out.println(
                        "Connexion MySQL réussie !"
                );
            }

        } catch (ClassNotFoundException e) {

            System.out.println(
                    "Driver JDBC introuvable !"
            );

            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println(
                    "Erreur de connexion MySQL !"
            );

            e.printStackTrace();
        }

        return connection;
    }

}
