package DAO;

import model.Commande;

import java.util.List;

public class TestCommandeDAO {
    public static void main(String[] args) {
        CommandeDAO dao = new CommandeDAO();

        // Création d'une nouvelle commande pour le client avec l'id 1
        // Tu peux changer l'id selon ce que tu as dans ta base
        Commande nouvelleCommande = new Commande(1); // idClient = 1

        // Ajout de la commande dans la base
        dao.ajouterCommande(nouvelleCommande);

        // Récupération et affichage de toutes les commandes
        List<Commande> commandes = dao.listerCommandes();
        for (Commande c : commandes) {
            System.out.println(c);
        }
    }
}
