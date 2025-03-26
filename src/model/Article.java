package model;

// le Catalogue en gros
public class Article {
    private int id;
    private String nom;
    private String marque;
    private double prixUnitaire;
    private double prixGros;
    private int seuilGros;

    // Champs nécessaires pour le contrôle de la facture
    private int quantite = 0;
    private String remise = "Aucune";

    // Constructeur avec ID (utilisé quand l'article vient de la base)
    public Article(int id, String nom, String marque, double prixUnitaire, double prixGros, int seuilGros) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.prixUnitaire = prixUnitaire;
        this.prixGros = prixGros;
        this.seuilGros = seuilGros;
    }

    // Constructeur sans ID (utilisé pour insérer un nouvel article)
    public Article(String nom, String marque, double prixUnitaire, double prixGros, int seuilGros) {
        this.nom = nom;
        this.marque = marque;
        this.prixUnitaire = prixUnitaire;
        this.prixGros = prixGros;
        this.seuilGros = seuilGros;
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getMarque() { return marque; }
    public double getPrixUnitaire() { return prixUnitaire; }
    public double getPrixGros() { return prixGros; }
    public int getSeuilGros() { return seuilGros; }

    public int getQuantite() { return quantite; }

    public double getPrix() { return prixUnitaire; } // utilisé par le contrôleur

    public String getRemise() {
        if (seuilGros > 0 && prixGros > 0) {
            return seuilGros + " pour " + prixGros + " €";
        } else {
            return "Aucune";
        }
    }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setMarque(String marque) { this.marque = marque; }
    public void setPrixUnitaire(double prixUnitaire) { this.prixUnitaire = prixUnitaire; }
    public void setPrixGros(double prixGros) { this.prixGros = prixGros; }
    public void setSeuilGros(int seuilGros) { this.seuilGros = seuilGros; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public void setRemise(String remise) { this.remise = remise; }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", marque='" + marque + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", prixGros=" + prixGros +
                ", seuilGros=" + seuilGros +
                ", quantite=" + quantite +
                ", remise='" + remise + '\'' +
                '}';
    }
}
