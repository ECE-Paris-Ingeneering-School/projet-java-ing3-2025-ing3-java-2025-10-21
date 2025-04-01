package DAO;

import model.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/shopping_db";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = ""; // Mets ton mot de passe ici si besoin

    public void ajouterArticle(Article article) {
        String sql = "INSERT INTO article(nom, marque, prix_unitaire, prix_gros, seuil_gros) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, article.getNom());
            stmt.setString(2, article.getMarque());
            stmt.setDouble(3, article.getPrixUnitaire());
            stmt.setDouble(4, article.getPrixGros());
            stmt.setInt(5, article.getSeuilGros());

            stmt.executeUpdate();
            System.out.println("✅ Article ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de l'ajout : " + e.getMessage());
        }
    }

    public void supprimerArticle(int id) {
        String sql = "DELETE FROM article WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("✅ Article supprimé avec succès !");
            } else {
                System.out.println("⚠️ Aucun article trouvé avec l’ID donné.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la suppression : " + e.getMessage());
        }
    }

    public void modifierArticle(Article article) {
        String sql = "UPDATE article SET nom = ?, marque = ?, prix_unitaire = ?, prix_gros = ?, seuil_gros = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, article.getNom());
            stmt.setString(2, article.getMarque());
            stmt.setDouble(3, article.getPrixUnitaire());
            stmt.setDouble(4, article.getPrixGros());
            stmt.setInt(5, article.getSeuilGros());
            stmt.setInt(6, article.getId());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("✅ Article modifié avec succès !");
            } else {
                System.out.println("⚠️ Aucun article trouvé avec l’ID donné.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la modification : " + e.getMessage());
        }
    }

    public List<Article> listerArticles() {
        List<Article> liste = new ArrayList<>();
        String sql = "SELECT * FROM article";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Article a = new Article(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("marque"),
                        rs.getDouble("prix_unitaire"),
                        rs.getDouble("prix_gros"),
                        rs.getInt("seuil_gros")
                );
                liste.add(a);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la récupération : " + e.getMessage());
        }

        return liste;
    }
}
