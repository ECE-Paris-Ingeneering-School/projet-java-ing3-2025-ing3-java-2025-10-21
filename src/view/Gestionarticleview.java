package view;

import DAO.ArticleDAO;
import model.Article;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Gestionarticleview extends JFrame {
    private JTable tableArticles;
    private DefaultTableModel modeleTable;
    private JTextField champNom, champMarque, champPrixUnitaire, champPrixGros, champSeuilGros;
    private ArticleDAO articleDAO;

    public Gestionarticleview() {
        super("Gestion des articles");
        articleDAO = new ArticleDAO();

        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Haut de la fenêtre : tableau
        modeleTable = new DefaultTableModel(new String[]{"ID", "Nom", "Marque", "Prix unit.", "Prix gros", "Seuil"}, 0);
        tableArticles = new JTable(modeleTable);
        JScrollPane scrollPane = new JScrollPane(tableArticles);

        // Milieu : formulaire
        JPanel panneauFormulaire = new JPanel(new GridLayout(2, 5));
        champNom = new JTextField();
        champMarque = new JTextField();
        champPrixUnitaire = new JTextField();
        champPrixGros = new JTextField();
        champSeuilGros = new JTextField();

        panneauFormulaire.add(new JLabel("Nom"));
        panneauFormulaire.add(new JLabel("Marque"));
        panneauFormulaire.add(new JLabel("Prix unitaire"));
        panneauFormulaire.add(new JLabel("Prix gros"));
        panneauFormulaire.add(new JLabel("Seuil gros"));

        panneauFormulaire.add(champNom);
        panneauFormulaire.add(champMarque);
        panneauFormulaire.add(champPrixUnitaire);
        panneauFormulaire.add(champPrixGros);
        panneauFormulaire.add(champSeuilGros);

        // Bas : boutons
        JPanel panneauBoutons = new JPanel();
        JButton boutonAjouter = new JButton("Ajouter");
        JButton boutonModifier = new JButton("Modifier");
        JButton boutonSupprimer = new JButton("Supprimer");
        JButton boutonRafraichir = new JButton("Rafraîchir");
        JButton boutonRetour = new JButton("↩ Retour au menu");
        boutonRetour.addActionListener(e -> {
            dispose(); // ferme la fenêtre actuelle
            new Menuadminvue().setVisible(true); // ouvre le menu principal
        });


        panneauBoutons.add(boutonAjouter);
        panneauBoutons.add(boutonModifier);
        panneauBoutons.add(boutonSupprimer);
        panneauBoutons.add(boutonRafraichir);
        panneauBoutons.add(boutonRetour);


        // Actions
        boutonAjouter.addActionListener(e -> ajouterArticle());
        boutonModifier.addActionListener(e -> modifierArticle());
        boutonSupprimer.addActionListener(e -> supprimerArticle());
        boutonRafraichir.addActionListener(e -> chargerArticles());

        // Layout principal
        getContentPane().add(scrollPane, BorderLayout.NORTH);
        getContentPane().add(panneauFormulaire, BorderLayout.CENTER);
        getContentPane().add(panneauBoutons, BorderLayout.SOUTH);

        chargerArticles();
    }

    private void chargerArticles() {
        modeleTable.setRowCount(0);
        List<Article> articles = articleDAO.listerArticles();
        for (Article a : articles) {
            modeleTable.addRow(new Object[]{a.getId(), a.getNom(), a.getMarque(), a.getPrixUnitaire(), a.getPrixGros(), a.getSeuilGros()});
        }
    }

    private void ajouterArticle() {
        String nom = champNom.getText();
        String marque = champMarque.getText();
        double prixUnitaire = Double.parseDouble(champPrixUnitaire.getText());
        double prixGros = Double.parseDouble(champPrixGros.getText());
        int seuil = Integer.parseInt(champSeuilGros.getText());

        Article a = new Article(nom, marque, prixUnitaire, prixGros, seuil);
        articleDAO.ajouterArticle(a);
        chargerArticles();
    }

    private void modifierArticle() {
        int ligne = tableArticles.getSelectedRow();
        if (ligne == -1) return;

        int id = (int) modeleTable.getValueAt(ligne, 0);
        String nom = champNom.getText();
        String marque = champMarque.getText();
        double prixUnitaire = Double.parseDouble(champPrixUnitaire.getText());
        double prixGros = Double.parseDouble(champPrixGros.getText());
        int seuil = Integer.parseInt(champSeuilGros.getText());

        Article a = new Article(nom, marque, prixUnitaire, prixGros, seuil);
        a.setId(id);
        articleDAO.modifierArticle(a);
        chargerArticles();
    }

    private void supprimerArticle() {
        int ligne = tableArticles.getSelectedRow();
        if (ligne == -1) return;
        int id = (int) modeleTable.getValueAt(ligne, 0);
        articleDAO.supprimerArticle(id);
        chargerArticles();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Gestionarticleview().setVisible(true));
    }
}
