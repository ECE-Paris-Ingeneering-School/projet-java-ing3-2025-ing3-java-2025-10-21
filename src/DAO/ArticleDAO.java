package DAO;

import model.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// acces aux Articles
public class ArticleDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/shopping_db";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = ""; // à adapter si tu mets un mot de passe

    public void ajouterArticle(Article article) {
        String sql = "INSERT INTO article(nom, marque, prix_unitaire, prix_gros, seuil_gros) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Remplacement des ? par les valeurs réelles
            stmt.setString(1, article.getNom());
            stmt.setString(2, article.getMarque());
            stmt.setDouble(3, article.getPrixUnitaire());
            stmt.setDouble(4, article.getPrixGros());
            stmt.setInt(5, article.getSeuilGros());

            stmt.executeUpdate();
            System.out.println("✅ Article à bien été ajoutéé  ! ! ! ");
        } catch (SQLException e) {
            System.out.println("❌❌❌ Erreur lors de l'ajout " + e.getMessage());
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
            System.out.println("❌❌❌ Erreurrrrr La récupération n'a pas été effectué " + e.getMessage());
        }

        return liste;
    }
}
