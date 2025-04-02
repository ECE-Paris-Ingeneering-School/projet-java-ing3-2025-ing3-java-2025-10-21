package view;

import DAO.CommandeDAO;
import model.Client;
import model.Commande;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import DAO.LigneCommandeDAO;
import DAO.ArticleDAO;
import model.LigneCommande;
import model.Article;

import java.util.HashMap;
import java.util.Map;

public class VueHistorique extends JPanel {
    private JTextArea historique;

    public VueHistorique(FenetrePrincipale parent) {
        setLayout(new BorderLayout());

        historique = new JTextArea();
        historique.setEditable(false);

        JButton retourBtn = new JButton("Retour Catalogue");
        retourBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        retourBtn.setPreferredSize(new Dimension(160, 30));
        retourBtn.addActionListener(e -> parent.afficherPage(FenetrePrincipale.PAGE_CATALOGUE));

        JPanel bas = new JPanel();
        bas.add(retourBtn);

        add(new JScrollPane(historique), BorderLayout.CENTER);
        add(bas, BorderLayout.SOUTH);
    }

    public void mettreAJourHistorique(FenetrePrincipale parent) {
        CommandeDAO commandeDAO = new CommandeDAO();
        LigneCommandeDAO ligneDAO = new LigneCommandeDAO();
        ArticleDAO articleDAO = new ArticleDAO();

        List<Commande> commandes = commandeDAO.listerCommandes();
        List<Article> tousLesArticles = articleDAO.listerArticles();

        // On prépare une map des articles pour accès rapide
        Map<Integer, Article> articleMap = new HashMap<>();
        for (Article a : tousLesArticles) {
            articleMap.put(a.getId(), a);
        }

        Client client = parent.getClientConnecte();
        StringBuilder sb = new StringBuilder("📜 Vos commandes :\n");

        for (Commande c : commandes) {
            if (c.getIdClient() == client.getId() && parent.getCommandesSession().contains(c.getId())) {
                List<LigneCommande> lignes = ligneDAO.listerLignesParCommande(c.getId());

                // Ne pas afficher les commandes vides
                if (lignes.isEmpty()) continue;

                sb.append("🧾 Commande #").append(c.getId())
                        .append(" - ").append(c.getDateCommande()).append("\n");

                for (LigneCommande ligne : lignes) {
                    Article article = articleMap.get(ligne.getIdArticle());
                    if (article != null) {
                        sb.append("   🛒 ").append(ligne.getQuantite())
                                .append(" x ").append(article.getNom())
                                .append(" - ").append(article.getPrixUnitaire()).append(" €\n");
                    }
                }
                sb.append("\n");
            }
        }

        historique.setText(sb.toString());
    }

}
