package model;

import java.time.LocalDateTime;

public class Commande {
    private int id;
    private int idClient;
    private LocalDateTime dateCommande;

    // Constructeur complet
    public Commande(int id, int idClient, LocalDateTime dateCommande) {
        this.id = id;
        this.idClient = idClient;
        this.dateCommande = dateCommande;
    }

    // Constructeur pour créer une nouvelle commande
    public Commande(int idClient) {
        this.idClient = idClient;
        this.dateCommande = LocalDateTime.now(); // date actuelle
    }

    // Getters
    public int getId() { return id; }
    public int getIdClient() { return idClient; }
    public LocalDateTime getDateCommande() { return dateCommande; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setIdClient(int idClient) { this.idClient = idClient; }
    public void setDateCommande(LocalDateTime dateCommande) { this.dateCommande = dateCommande; }

    @Override // On utilise @Override car on modifie la version de base de la méthode toString
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", dateCommande=" + dateCommande +
                '}';
    }
}
