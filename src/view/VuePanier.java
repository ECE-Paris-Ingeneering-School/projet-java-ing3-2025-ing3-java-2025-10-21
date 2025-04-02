package view;

import DAO.CommandeDAO;
import DAO.LigneCommandeDAO;
import model.Article;
import model.Client;
import model.Commande;
import model.LigneCommande;

import javax.swing.*;
import java.awt.*;

public class VuePanier extends JPanel {
    private JTextArea textePanier;

    public VuePanier(FenetrePrincipale parent) {
        setLayout(new BorderLayout());

        textePanier = new JTextArea();
        textePanier.setEditable(false);

        JButton commanderBtn = new JButton("Valider la commande");
        JButton retourBtn = new JButton("Retour catalogue");

        commanderBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        retourBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        commanderBtn.setPreferredSize(new Dimension(180, 30));
        retourBtn.setPreferredSize(new Dimension(180, 30));

        commanderBtn.addActionListener(e -> {
            Client client = parent.getClientConnecte();
            if (client == null || parent.getPanier().getArticles().isEmpty()) return;

            Commande commande = new Commande(client.getId());
            new CommandeDAO().ajouterCommande(commande);

            for (Article a : parent.getPanier().getArticles()) {
                int qte = parent.getPanier().getQuantite(a);
                new LigneCommandeDAO().ajouterLigneCommande(new LigneCommande(commande.getId(), a.getId(), qte));
            }

            parent.getPanier().vider();
            JOptionPane.showMessageDialog(this, "Commande validée !");
            mettreAJourPanier(parent); // vide après validation
            parent.afficherPage(FenetrePrincipale.PAGE_HISTORIQUE);
        });

        retourBtn.addActionListener(e -> parent.afficherPage(FenetrePrincipale.PAGE_CATALOGUE));

        JPanel bas = new JPanel();
        bas.add(commanderBtn);
        bas.add(retourBtn);

        add(new JScrollPane(textePanier), BorderLayout.CENTER);
        add(bas, BorderLayout.SOUTH);
    }

    public void mettreAJourPanier(FenetrePrincipale parent) {
        StringBuilder contenu = new StringBuilder();
        for (Article a : parent.getPanier().getArticles()) {
            int qte = parent.getPanier().getQuantite(a);
            contenu.append(qte).append(" x ").append(a.getNom()).append(" - ")
                    .append(a.getPrixUnitaire()).append(" €\n");
        }

        if (contenu.isEmpty()) {
            contenu.append("Votre panier est vide.");
        }

        textePanier.setText(contenu.toString());
    }
}
