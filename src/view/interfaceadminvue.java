package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class interfaceadminvue extends JFrame {

    private JTable tableInventaire;
    private JButton boutonAjouter;
    private JButton boutonModifier;
    private JButton boutonSupprimer;
    private JButton boutonRemise;
    private JButton boutonStatistiques;

    public interfaceadminvue() {
        setTitle("Interface Administrateur - Gestion des Articles");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Titre
        JLabel titre = new JLabel("Inventaire des articles", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        add(titre, BorderLayout.NORTH);

        // Tableau des articles
        String[] colonnes = {"Nom", "Marque", "Prix Unitaire", "Prix Gros", "Seuil Gros"};
        tableInventaire = new JTable(new DefaultTableModel(colonnes, 0));
        JScrollPane scrollPane = new JScrollPane(tableInventaire);
        add(scrollPane, BorderLayout.CENTER);

        // Boutons en bas
        JPanel panelBoutons = new JPanel(new FlowLayout());
        boutonAjouter = new JButton("Ajouter");
        boutonModifier = new JButton("Modifier");
        boutonSupprimer = new JButton("Supprimer");
        boutonRemise = new JButton("Gérer les Remises");
        boutonStatistiques = new JButton("Statistiques");

        panelBoutons.add(boutonAjouter);
        panelBoutons.add(boutonModifier);
        panelBoutons.add(boutonSupprimer);
        panelBoutons.add(boutonRemise);
        panelBoutons.add(boutonStatistiques);

        add(panelBoutons, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Accès aux composants
    public JTable getTableInventaire() {
        return tableInventaire;
    }

    public void ajouterListenerAjouter(ActionListener al) {
        boutonAjouter.addActionListener(al);
    }

    public void ajouterListenerModifier(ActionListener al) {
        boutonModifier.addActionListener(al);
    }

    public void ajouterListenerSupprimer(ActionListener al) {
        boutonSupprimer.addActionListener(al);
    }

    public void ajouterListenerRemise(ActionListener al) {
        boutonRemise.addActionListener(al);
    }

    public void ajouterListenerStatistiques(ActionListener al) {
        boutonStatistiques.addActionListener(al);
    }
    public static void main(String[] args) {
        new interfaceadminvue();
    }
}
