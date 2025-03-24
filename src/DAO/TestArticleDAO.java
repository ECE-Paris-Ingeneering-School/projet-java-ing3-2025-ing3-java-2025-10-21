package DAO;

import model.Article;

import java.util.List;

public class TestArticleDAO {
    public static void main(String[] args) {
        ArticleDAO dao = new ArticleDAO();

        // Création d'un nouvel article
        Article article = new Article("Briquet", "Bic", 0.50, 4.00, 10);
        dao.ajouterArticle(article);

        // Affichage des articles en base
        List<Article> articles = dao.listerArticles();
        for (Article a : articles) {
            System.out.println(a);
        }
    }
}
