package main;

import view.facturevue;
import model.Article;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            facturevue vue = new facturevue();
            vue.setNomClient("Jean Dupont");

            // Création d'articles avec quantité/remise
            List<Article> articles = new ArrayList<>();

            Article a1 = new Article("Briquet", "BIC", 0.5, 4.0, 10);
            a1.setQuantite(12);
            articles.add(a1);

            Article a2 = new Article("Stylo", "Pilot", 1.5, 0, 0);
            a2.setQuantite(2);
            articles.add(a2);

            double total = 0;

            for (Article a : articles) {
                int q = a.getQuantite();
                double prix = a.getPrix();
                String remise = a.getRemise();
                double sousTotal = (q >= a.getSeuilGros() && a.getSeuilGros() > 0)
                        ? ((q / a.getSeuilGros()) * a.getPrixGros() + (q % a.getSeuilGros()) * prix)
                        : q * prix;

                total += sousTotal;

                vue.ajouterLigneFacture(a.getNom(), q, prix, remise, sousTotal);
            }

            vue.setTotal(String.format("%.2f", total));
        });
    }
}
