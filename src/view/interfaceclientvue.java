package view;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class interfaceclientvue extends JFrame {

    // Composants principaux
    private JTable tableauArticles;
    private JButton boutonAjouterPanier;
    private JButton boutonVoirPanier;
    private JButton boutonCommander;
    private JButton boutonHistorique;

    public interfaceclientvue() {
        setTitle("Interface Client - Catalogue");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Titre ---
        JLabel labelTitre = new JLabel("Catalogue des articles", SwingConstants.CENTER);
        labelTitre.setFont(new Font("Arial", Font.BOLD, 20));
        add(labelTitre, BorderLayout.NORTH);

        // --- Tableau des articles ---
        String[] colonnes = {"Nom", "Marque", "Prix Unitaire", "Prix Gros", "Seuil Gros"};
        tableauArticles = new JTable(new DefaultTableModel(colonnes, 0));
        JScrollPane scrollPane = new JScrollPane(tableauArticles);
        add(scrollPane, BorderLayout.CENTER);

        // --- Boutons en bas ---
        JPanel panelBoutons = new JPanel(new FlowLayout());

        boutonAjouterPanier = new JButton("Ajouter au panier");
        boutonVoirPanier = new JButton("Voir le panier");
        boutonCommander = new JButton("Commander");
        boutonHistorique = new JButton("Historique commandes");

        panelBoutons.add(boutonAjouterPanier);
        panelBoutons.add(boutonVoirPanier);
        panelBoutons.add(boutonCommander);
        panelBoutons.add(boutonHistorique);

        add(panelBoutons, BorderLayout.SOUTH);

        setVisible(true);
    }

    // --- Méthodes d'accès aux composants ---

    public JTable getTableauArticles() {
        return tableauArticles;
    }

    public void ajouterListenerAjouterPanier(ActionListener al) {
        boutonAjouterPanier.addActionListener(al);
    }

    public void ajouterListenerVoirPanier(ActionListener al) {
        boutonVoirPanier.addActionListener(al);
    }

    public void ajouterListenerCommander(ActionListener al) {
        boutonCommander.addActionListener(al);
    }

    public void ajouterListenerHistorique(ActionListener al) {
        boutonHistorique.addActionListener(al);
    }
    public static void main(String[] args) {
        new interfaceclientvue();
    }
}
