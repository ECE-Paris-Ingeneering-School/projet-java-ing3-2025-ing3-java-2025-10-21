package DAO;

import model.LigneCommande;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


 //Permet d'ajouter et de lire les lignes de commande dans la base
 //Une ligne de commande correspond à un article acheté dans une commande

public class LigneCommandeDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/shopping_db";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = ""; // adapter si besoin

    public void ajouterLigneCommande(LigneCommande ligne) {
        String sql = "INSERT INTO ligne_commande(id_commande, id_article, quantite) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ligne.getIdCommande());
            stmt.setInt(2, ligne.getIdArticle());
            stmt.setInt(3, ligne.getQuantite());

            stmt.executeUpdate();
            System.out.println("✅ Ligne de commande ajoutée avec succès !");
        } catch (SQLException e) {
            System.out.println("❌❌❌ Erreur lors de l'ajout de la ligne de commande : " + e.getMessage());
        }
    }

    /**
     * Liste toutes les lignes de commande d'une commande donnée
     */
    public List<LigneCommande> listerLignesParCommande(int idCommande) {
        List<LigneCommande> liste = new ArrayList<>();
        String sql = "SELECT * FROM ligne_commande WHERE id_commande = ?";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCommande);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LigneCommande ligne = new LigneCommande(
                        rs.getInt("id"),
                        rs.getInt("id_commande"),
                        rs.getInt("id_article"),
                        rs.getInt("quantite")
                );
                liste.add(ligne);
            }

        } catch (SQLException e) {
            System.out.println("❌❌❌ Erreur lors de la récupération : " + e.getMessage());
        }

        return liste;
    }
}
