package view;

import javax.swing.*;
import java.awt.*;

public class Menuadminvue extends JFrame {

    public Menuadminvue() {
        super("Tableau de bord Administrateur");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton boutonArticles = new JButton("Gestion des articles");
        JButton boutonClients = new JButton("Gestion des clients");
        JButton boutonCommandes = new JButton("Commandes");
        JButton boutonStats = new JButton("Statistiques");

        boutonArticles.addActionListener(e -> new Gestionarticleview().setVisible(true));
        boutonClients.addActionListener(e -> new Gestionclientvue().setVisible(true));
        boutonCommandes.addActionListener(e -> new Gestioncommandevue().setVisible(true));
        boutonStats.addActionListener(e -> new Statsvue().setVisible(true));

        JPanel panneau = new JPanel(new GridLayout(4, 1, 10, 10));
        panneau.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panneau.add(boutonArticles);
        panneau.add(boutonClients);
        panneau.add(boutonCommandes);
        panneau.add(boutonStats);

        add(panneau);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Menuadminvue().setVisible(true));
    }
}
