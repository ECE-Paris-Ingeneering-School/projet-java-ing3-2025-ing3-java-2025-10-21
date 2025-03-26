package controller;

import view.facturevue;
import model.Article;
import model.Client;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class facturecontroller {

    private facturevue vue;

    public facturecontroller(Client client, List<Article> articles) {
        vue = new facturevue();
        vue.setNomClient(client.getNom());

        double total = 0;

        for (Article article : articles) {
            int quantite = article.getQuantite(); // Suppose que tu as getQuantite()
            double prixUnitaire = article.getPrix(); // Suppose que tu as getPrix()
            String promo = article.getRemise(); // getRemise() type texte : "10 pour 4€", "Aucune" etc.

            double sousTotal = calculerSousTotal(quantite, prixUnitaire, promo);
            total += sousTotal;

            vue.ajouterLigneFacture(article.getNom(), quantite, prixUnitaire, promo, sousTotal);
        }

        vue.setTotal(String.format("%.2f", total));

        vue.ajouterListenerRetour(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vue.dispose(); // Ferme la fenêtre
                // Redirige vers une autre interface si besoin
            }
        });

        vue.ajouterListenerValider(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(vue, "Facture validée !");
                vue.dispose(); // Ou autre action
            }
        });
    }

    private double calculerSousTotal(int quantite, double prix, String promo) {
        if (promo != null && promo.contains("pour")) {
            // Ex : "10 pour 4 €"
            try {
                String[] parts = promo.split(" ");
                int qtePromo = Integer.parseInt(parts[0]);
                double prixPromo = Double.parseDouble(parts[2].replace("€", "").replace(",", "."));

                int packs = quantite / qtePromo;
                int reste = quantite % qtePromo;

                return packs * prixPromo + reste * prix;
            } catch (Exception e) {
                return quantite * prix; // fallback si erreur
            }
        } else {
            return quantite * prix;
        }
    }
}
