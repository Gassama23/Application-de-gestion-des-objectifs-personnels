package src.org.odk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	

	private static final String URL = "jdbc:mysql://localhost:3306/gestion_objectifs";


    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static Connection connection = null;


    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

               //Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                System.out.println("Connexion à la base de données réussie !");

            } 
        }catch (SQLException e) {

            System.err.println("Erreur de connexion à la base de données !");

            e.printStackTrace();

        }

        return connection;

    }

    

    public static void closeConnection() {

        try {

            if (connection != null && !connection.isClosed()) {

                connection.close();

                System.out.println("Connexion fermée.");

            }

        } catch (SQLException e) {

            System.err.println("Erreur lors de la fermeture de la connexion !");

            e.printStackTrace();

        }

    }
	
}
