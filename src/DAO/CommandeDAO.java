package DAO;

import model.Commande;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CommandeDAO {


    private static final String URL = "jdbc:mysql://localhost:3306/shopping_db";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";

    public void ajouterCommande(Commande commande) {
        String sql = "INSERT INTO commande(id_client, date_commande) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);

             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {//on ajoute RETURN_GENERATED_KEYS pour pouvoir récupérer l'id créé par MySQL

            stmt.setInt(1, commande.getIdClient());
            stmt.setTimestamp(2, Timestamp.valueOf(commande.getDateCommande()));

            stmt.executeUpdate();

            // Récupération de l'id généré automatiquement par la base
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idGenere = rs.getInt(1);
                commande.setId(idGenere);   // on met à jour notre objet Java avec ce nouvel ID
                System.out.println("✅ Commande ajoutée avec l'id : " + idGenere);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de l'ajout de la commande : " + e.getMessage());
        }
    }



    public List<Commande> listerCommandes() {
        List<Commande> liste = new ArrayList<>();
        String sql = "SELECT * FROM commande";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int idClient = rs.getInt("id_client");

                LocalDateTime dateCommande = rs.getTimestamp("date_commande").toLocalDateTime();

                Commande commande = new Commande(id, idClient, dateCommande);
                liste.add(commande);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la récupération des commandes : " + e.getMessage());
        }

        return liste;
    }
}
