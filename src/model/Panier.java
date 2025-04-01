package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;  

/**
 * Représente le panier d'un client.
 * Contient les articles ajoutés avec leurs quantités.
 */
public class Panier {
    // On utilise une HashMap pour stocker les articles et leur quantité
    private Map<Article, Integer> articles;

    public Panier() {
        this.articles = new HashMap<>();
    }

    // Ajoute un article au panier avec une certaine quantité
    public void ajouterArticle(Article article, int quantite) {
        // Si l'article est déjà présent, on augmente la quantité
        articles.put(article, articles.getOrDefault(article, 0) + quantite);
    }

    // Supprime complètement un article du panier
    public void supprimerArticle(Article article) {
        articles.remove(article);
    }

    // Vide complètement le panier
    public void vider() {
        articles.clear();
    }

    // Renvoie tous les articles présents dans le panier
    public Set<Article> getArticles() {
        return articles.keySet();
    }

    // Renvoie la quantité d’un article spécifique
    public int getQuantite(Article article) {
        return articles.getOrDefault(article, 0);
    }

    // Calcule le total du panier en tenant compte du prix de gros si applicable
    public double calculerTotal() {
        double total = 0.0;

        for (Map.Entry<Article, Integer> entry : articles.entrySet()) {
            Article article = entry.getKey();
            int quantite = entry.getValue();

            // Si on dépasse le seuil, on applique le prix de gros
            if (quantite >= article.getSeuilGros()) {
                total += article.getPrixGros() * quantite;
            } else {
                total += article.getPrixUnitaire() * quantite;
            }
        }

        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("🧺 Panier :\n");

        for (Map.Entry<Article, Integer> entry : articles.entrySet()) {
            Article article = entry.getKey();
            int quantite = entry.getValue();
            sb.append("- ").append(article.getNom())
                    .append(" x").append(quantite)
                    .append("\n");
        }

        sb.append("💰 Total : ").append(calculerTotal()).append(" €");
        return sb.toString();
    }
}
