package DAO;

import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/shopping_db";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = ""; // mot de passe vide si aucun n’est défini

    public void ajouterClient(Client client) {
        String sql = "INSERT INTO client(nom, email, mot_de_passe, type_client) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getMotDePasse());
            stmt.setString(4, client.getTypeClient());

            stmt.executeUpdate();
            System.out.println("✅ Client ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de l'ajout du client : " + e.getMessage());
        }
    }

    public void supprimerClient(int id) {
        String sql = "DELETE FROM client WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("✅ Client supprimé avec succès !");
            } else {
                System.out.println("⚠️ Aucun client trouvé avec l’ID donné.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la suppression du client : " + e.getMessage());
        }
    }

    public void modifierClient(Client client) {
        String sql = "UPDATE client SET nom = ?, email = ?, mot_de_passe = ?, type_client = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getMotDePasse());
            stmt.setString(4, client.getTypeClient());
            stmt.setInt(5, client.getId());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("✅ Client modifié avec succès !");
            } else {
                System.out.println("⚠️ Aucun client trouvé avec l’ID donné.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la modification du client : " + e.getMessage());
        }
    }

    public List<Client> listerClients() {
        List<Client> liste = new ArrayList<>();
        String sql = "SELECT * FROM client";

        try (Connection conn = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Client c = new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("type_client")
                );
                liste.add(c);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la récupération des clients : " + e.getMessage());
        }

        return liste;
    }
}
