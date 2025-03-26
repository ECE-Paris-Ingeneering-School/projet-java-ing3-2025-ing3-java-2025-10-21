package controller;

import model.Article;
import view.interfaceclientvue;
import view.facturevue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class interfaceclientcontroller {

    private interfaceclientvue vue;
    private List<Article> catalogue;
    private List<Article> panier;

    public interfaceclientcontroller() {
        this.vue = new interfaceclientvue();
        this.catalogue = chargerCatalogue();
        this.panier = new ArrayList<>();

        remplirTableauCatalogue();

        vue.ajouterListenerAjouterPanier(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ligne = vue.getTableauArticles().getSelectedRow();
                if (ligne == -1) {
                    JOptionPane.showMessageDialog(vue, "Sélectionnez un article !");
                    return;
                }

                String nom = (String) vue.getTableauArticles().getValueAt(ligne, 0);
                Article article = trouverArticleParNom(nom);

                if (article != null) {
                    String qteStr = JOptionPane.showInputDialog("Quantité ?");
                    try {
                        int quantite = Integer.parseInt(qteStr);
                        if (quantite <= 0) throw new Exception();
                        Article copie = copierArticle(article);
                        copie.setQuantite(quantite);
                        panier.add(copie);
                        JOptionPane.showMessageDialog(vue, "Ajouté au panier !");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vue, "Quantité invalide !");
                    }
                }
            }
        });

        vue.ajouterListenerVoirPanier(e -> {
            StringBuilder contenu = new StringBuilder();
            for (Article a : panier) {
                contenu.append(a.getNom()).append(" x").append(a.getQuantite()).append("\n");
            }
            if (panier.isEmpty()) contenu.append("Panier vide.");
            JOptionPane.showMessageDialog(vue, contenu.toString(), "Panier", JOptionPane.INFORMATION_MESSAGE);
        });

        vue.ajouterListenerCommander(e -> {
            if (panier.isEmpty()) {
                JOptionPane.showMessageDialog(vue, "Le panier est vide !");
                return;
            }
            vue.dispose(); // Fermer interface client
            new facturevueController("Client Exemple", panier);
        });

        vue.ajouterListenerHistorique(e -> {
            JOptionPane.showMessageDialog(vue, "Historique de commandes (à implémenter)");
        });
    }

    private List<Article> chargerCatalogue() {
        List<Article> liste = new ArrayList<>();
        liste.add(new Article("Briquet", "BIC", 0.5, 4.0, 10));
        liste.add(new Article("Stylo", "Pilot", 1.5, 0, 0));
        liste.add(new Article("Carnet", "Oxford", 3.0, 25.0, 10));
        return liste;
    }

    private void remplirTableauCatalogue() {
        DefaultTableModel model = (DefaultTableModel) vue.getTableauArticles().getModel();
        for (Article a : catalogue) {
            model.addRow(new Object[]{
                    a.getNom(),
                    a.getMarque(),
                    a.getPrixUnitaire() + " €",
                    (a.getPrixGros() > 0 ? a.getPrixGros() + " €" : "-"),
                    (a.getSeuilGros() > 0 ? a.getSeuilGros() : "-")
            });
        }
    }

    private Article trouverArticleParNom(String nom) {
        for (Article a : catalogue) {
            if (a.getNom().equals(nom)) return a;
        }
        return null;
    }

    private Article copierArticle(Article a) {
        return new Article(a.getNom(), a.getMarque(), a.getPrixUnitaire(), a.getPrixGros(), a.getSeuilGros());
    }

    // Contrôleur intégré de facture pour appel direct
    private class facturevueController {
        public facturevueController(String nomClient, List<Article> articles) {
            facturevue vue = new facturevue();
            vue.setNomClient(nomClient);

            double total = 0;

            for (Article article : articles) {
                int quantite = article.getQuantite();
                double prix = article.getPrixUnitaire();
                String remise = article.getRemise();

                double sousTotal = (quantite >= article.getSeuilGros() && article.getSeuilGros() > 0)
                        ? ((quantite / article.getSeuilGros()) * article.getPrixGros() + (quantite % article.getSeuilGros()) * prix)
                        : quantite * prix;

                total += sousTotal;
                vue.ajouterLigneFacture(article.getNom(), quantite, prix, remise, sousTotal);
            }

            vue.setTotal(String.format("%.2f", total));
        }
    }
}
