package controller;

import model.Article;
import view.interfaceadminvue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class interfaceadmincontroller {

    private interfaceadminvue vue;
    private List<Article> articles;

    public interfaceadmincontroller() {
        this.vue = new interfaceadminvue();
        this.articles = new ArrayList<>();

        chargerArticlesDeTest();
        remplirTableau();

        vue.ajouterListenerAjouter(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = JOptionPane.showInputDialog("Nom de l'article :");
                String marque = JOptionPane.showInputDialog("Marque :");
                double prixUnitaire = Double.parseDouble(JOptionPane.showInputDialog("Prix unitaire :"));
                double prixGros = Double.parseDouble(JOptionPane.showInputDialog("Prix gros (0 si aucun) :"));
                int seuilGros = Integer.parseInt(JOptionPane.showInputDialog("Seuil pour prix gros (0 si aucun) :"));

                Article nouveau = new Article(nom, marque, prixUnitaire, prixGros, seuilGros);
                articles.add(nouveau);
                remplirTableau();
            }
        });

        vue.ajouterListenerModifier(e -> {
            int ligne = vue.getTableInventaire().getSelectedRow();
            if (ligne == -1) {
                JOptionPane.showMessageDialog(vue, "Sélectionnez un article à modifier.");
                return;
            }

            Article article = articles.get(ligne);
            String nom = JOptionPane.showInputDialog("Nouveau nom :", article.getNom());
            String marque = JOptionPane.showInputDialog("Nouvelle marque :", article.getMarque());
            double prixUnitaire = Double.parseDouble(JOptionPane.showInputDialog("Nouveau prix unitaire :", article.getPrixUnitaire()));
            double prixGros = Double.parseDouble(JOptionPane.showInputDialog("Nouveau prix gros :", article.getPrixGros()));
            int seuilGros = Integer.parseInt(JOptionPane.showInputDialog("Nouveau seuil gros :", article.getSeuilGros()));

            article.setNom(nom);
            article.setMarque(marque);
            article.setPrixUnitaire(prixUnitaire);
            article.setPrixGros(prixGros);
            article.setSeuilGros(seuilGros);

            remplirTableau();
        });

        vue.ajouterListenerSupprimer(e -> {
            int ligne = vue.getTableInventaire().getSelectedRow();
            if (ligne == -1) {
                JOptionPane.showMessageDialog(vue, "Sélectionnez un article à supprimer.");
                return;
            }
            articles.remove(ligne);
            remplirTableau();
        });

        vue.ajouterListenerRemise(e -> {
            JOptionPane.showMessageDialog(vue, "Fonction de remise à implémenter.");
        });

        vue.ajouterListenerStatistiques(e -> {
            JOptionPane.showMessageDialog(vue, "Statistiques à implémenter avec JFreeChart.");
        });
    }

    private void chargerArticlesDeTest() {
        articles.add(new Article("Briquet", "BIC", 0.5, 4.0, 10));
        articles.add(new Article("Stylo", "Pilot", 1.5, 0, 0));
    }

    private void remplirTableau() {
        DefaultTableModel model = (DefaultTableModel) vue.getTableInventaire().getModel();
        model.setRowCount(0); // reset
        for (Article a : articles) {
            model.addRow(new Object[]{
                    a.getNom(),
                    a.getMarque(),
                    a.getPrixUnitaire() + " €",
                    (a.getPrixGros() > 0 ? a.getPrixGros() + " €" : "-"),
                    (a.getSeuilGros() > 0 ? a.getSeuilGros() : "-")
            });
        }
    }
}
