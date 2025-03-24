package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
    private static final String URL = "jdbc:mysql://localhost:3306/shopping_db";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";

    public static void main(String[] args) {
        try (Connection connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE)) {
            System.out.println("✅ La Connexion à la base de données EST REUSSIEEEEEEEEEE !✅ !✅!✅ !✅ ! ✅!");
        } catch (SQLException e) {
            System.out.println("❌ ❌ ❌LA CONNEXION A ECHOUERRRRRRR " + e.getMessage());
        }
    }
}
