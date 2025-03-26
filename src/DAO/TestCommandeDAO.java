package DAO;

import model.Commande;

import java.util.List;

public class TestCommandeDAO {
    public static void main(String[] args) {
        CommandeDAO dao = new CommandeDAO();

        // Création d'une nouvelle commande pour le client avec l'id 1
        Commande nouvelleCommande = new Commande(1); 

        // On ajoute la Commande
        dao.ajouterCommande(nouvelleCommande);
        List<Commande> commandes = dao.listerCommandes();
        for (Commande c : commandes) {
            System.out.println(c);
        }
    }
}
