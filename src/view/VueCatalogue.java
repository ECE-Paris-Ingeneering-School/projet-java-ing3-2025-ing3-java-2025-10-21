package view;

import DAO.ArticleDAO;
import model.Article;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VueCatalogue extends JPanel {
    private FenetrePrincipale fenetrePrincipale;
    public VueCatalogue(FenetrePrincipale fenetrePrincipale) {
        this.fenetrePrincipale = fenetrePrincipale;
        this.setLayout(new BorderLayout());

        JPanel panelArticles = new JPanel();
        panelArticles.setLayout(new GridLayout(0, 2, 10, 10)); // 2 colonnes

        List<Article> articles = new ArticleDAO().listerArticles(); // méthode existante

        for (Article a : articles) {
            JButton bouton = new JButton("<html><b>" + a.getNom() + "</b><br/>" + a.getPrix() + " €</html>");
            bouton.addActionListener(e -> {
                // ajouter au panier ?
            });
            panelArticles.add(bouton);
        }

        JScrollPane scroll = new JScrollPane(panelArticles);
        this.add(scroll, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        JPanel produitsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        produitsPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        ArticleDAO dao = new ArticleDAO();
        for (Article article : dao.listerArticles()) {
            JButton bouton = new JButton(article.getNom() + " - " + article.getPrixUnitaire() + "€");
            bouton.setFont(new Font("Arial", Font.PLAIN, 12));
            bouton.setPreferredSize(new Dimension(300, 30));
            bouton.addActionListener(e -> {
                fenetrePrincipale.getPanier().ajouterArticle(article, 1);
                JOptionPane.showMessageDialog(this, "Article ajouté au panier.");
            });
            produitsPanel.add(bouton);
        }

        JButton panierBtn = new JButton("Voir Panier");
        panierBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        panierBtn.setPreferredSize(new Dimension(150, 30));
        panierBtn.addActionListener(e -> fenetrePrincipale.afficherPage(FenetrePrincipale.PAGE_PANIER));

        JPanel bas = new JPanel();
        bas.add(panierBtn);

        add(new JScrollPane(produitsPanel), BorderLayout.CENTER);
        add(bas, BorderLayout.SOUTH);
    }
}
