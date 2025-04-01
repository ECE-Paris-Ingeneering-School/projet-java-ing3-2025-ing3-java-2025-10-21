package DAO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StatscommandeDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/shopping_db";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";

    // Total des ventes € (prix * quantite)
    public double calculerVentesTotales() {
        String sql = "SELECT SUM(a.prix_unitaire * lc.quantite) AS total FROM ligne_commande lc " +
                "JOIN article a ON lc.id_article = a.id";
        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) return rs.getDouble("total");
        } catch (SQLException e) {
            System.out.println("Erreur total ventes : " + e.getMessage());
        }
        return 0.0;
    }

    // Produits les plus vendus (quantite totale)
    public Map<String, Integer> getQuantitesParArticle() {
        Map<String, Integer> stats = new HashMap<>();
        String sql = "SELECT a.nom, SUM(lc.quantite) AS total FROM ligne_commande lc " +
                "JOIN article a ON lc.id_article = a.id GROUP BY a.nom ORDER BY total DESC";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                stats.put(rs.getString("nom"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur stats article : " + e.getMessage());
        }
        return stats;
    }

    // Répartition des ventes par client (total € par client)
    public Map<String, Double> getVentesParClient() {
        Map<String, Double> stats = new HashMap<>();
        String sql = "SELECT c.nom, SUM(a.prix_unitaire * lc.quantite) AS total " +
                "FROM client c " +
                "JOIN commande co ON c.id = co.id_client " +
                "JOIN ligne_commande lc ON co.id = lc.id_commande " +
                "JOIN article a ON lc.id_article = a.id " +
                "GROUP BY c.nom ORDER BY total DESC";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                stats.put(rs.getString("nom"), rs.getDouble("total"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur stats client : " + e.getMessage());
        }
        return stats;
    }
}
