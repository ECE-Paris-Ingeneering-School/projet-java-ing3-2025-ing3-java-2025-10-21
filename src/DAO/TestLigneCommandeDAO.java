package DAO;

import model.LigneCommande;

import java.util.List;

public class TestLigneCommandeDAO {
    public static void main(String[] args) {
        LigneCommandeDAO dao = new LigneCommandeDAO();
        // pour que ça fonctionne il faut qu'il existe une commande et un article
        LigneCommande ligne = new LigneCommande(1, 1, 3);

        // ajout d'une ligne à la commande
        dao.ajouterLigneCommande(ligne);

        // Affichage
        List<LigneCommande> lignes = dao.listerLignesParCommande(1);
        for (LigneCommande l : lignes) {
            System.out.println(l);
        }
    }
}
