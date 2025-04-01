package DAO;

import java.sql.*;

public class StatistiqueDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/shopping_db";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";

    public int compterArticles() {
        String sql = "SELECT COUNT(*) FROM article";
        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors du comptage des articles : " + e.getMessage());
        }
        return 0;
    }

    public double prixMoyenArticles() {
        String sql = "SELECT AVG(prix_unitaire) FROM article";
        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors du calcul du prix moyen : " + e.getMessage());
        }
        return 0.0;
    }

    // Tu peux aussi ajouter : SUM(prix_gros), MAX(prix_unitaire), etc.
}
