package model;

public class Client {
    private int id;
    private String nom;
    private String email;
    private String motDePasse;
    private String typeClient;

    public Client(int id, String nom, String email, String motDePasse, String typeClient) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.typeClient = typeClient;
    }

    public Client(String nom, String email, String motDePasse, String typeClient) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.typeClient = typeClient;
    }

    // ✅ Constructeur simplifié pour la gestion des clients (vue admin)
    public Client(String nom, String email) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = "";
        this.typeClient = "standard";
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public String getMotDePasse() { return motDePasse; }
    public String getTypeClient() { return typeClient; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setEmail(String email) { this.email = email; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public void setTypeClient(String typeClient) { this.typeClient = typeClient; }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", typeClient='" + typeClient + '\'' +
                '}';
    }
}
