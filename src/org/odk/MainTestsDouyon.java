package org.odk;

import java.sql.Connection;

public class MainTestsDouyon {

    public static void main(String[] args) {

        Connection conn = DatabaseDouyon.getConnection();

        if (conn != null) {
            System.out.println("✔ Connexion OK !");
        } else {
            System.out.println("❌ Connexion échouée !");
        }
    }
}