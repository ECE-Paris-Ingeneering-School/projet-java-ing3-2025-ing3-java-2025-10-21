package DAO;

import model.Commande;
import model.LigneCommande;

import java.util.List;

public class TestCommandeComplete {
    public static void main(String[] args) {
        CommandeDAO commandeDAO = new CommandeDAO();
        LigneCommandeDAO ligneDAO = new LigneCommandeDAO();

        // Création d'une nouvelle commande
        Commande commande = new Commande(1);
        commandeDAO.ajouterCommande(commande);

        int idCommandeCreee = commande.getId(); /// ou manuellement avec int idCommandeCreee = 2;

        ///ajout de plusieurs articles dans la commande
        LigneCommande ligne1 = new LigneCommande(idCommandeCreee, 1, 2); // 2 exemplaires de l'article 1
        LigneCommande ligne2 = new LigneCommande(idCommandeCreee, 2, 1); // 1 exemplaire de l'article 2

        ligneDAO.ajouterLigneCommande(ligne1);
        ligneDAO.ajouterLigneCommande(ligne2);

        /// Pour finir on affiche le tout
        List<LigneCommande> lignes = ligneDAO.listerLignesParCommande(idCommandeCreee);
        for (LigneCommande l : lignes) {
            System.out.println(l);
        }
    }
}
