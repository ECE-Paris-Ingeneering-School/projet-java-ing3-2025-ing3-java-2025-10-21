import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class CatalogueFenetre extends JFrame {
    private JTable tableProduits;
    private JButton btnAjouterPanier, btnVoirPanier, btnHistorique, btnDeconnexion;

    public CatalogueFenetre() {
        setTitle("Catalogue des produits");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        // Modèle de la table avec colonnes
        String[] colonnes = {"ID", "Nom", "Prix", "Stock"};
        DefaultTableModel model = new DefaultTableModel(colonnes, 0);

        // Remplissage fictif (les DAO remplaceront ces données)
        model.addRow(new Object[]{1, "Produit A", 10.0, 100});
        model.addRow(new Object[]{2, "Produit B", 20.0, 50});
        model.addRow(new Object[]{3, "Produit C", 15.0, 75});

        tableProduits = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableProduits);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel des boutons
        JPanel panelButtons = new JPanel();
        btnAjouterPanier = new JButton("Ajouter au panier");
        btnVoirPanier = new JButton("Voir Panier");
        btnHistorique = new JButton("Historique Commandes");
        btnDeconnexion = new JButton("Déconnexion");

        panelButtons.add(btnAjouterPanier);
        panelButtons.add(btnVoirPanier);
        panelButtons.add(btnHistorique);
        panelButtons.add(btnDeconnexion);
        panel.add(panelButtons, BorderLayout.SOUTH);

        // Listener pour ajouter au panier
        btnAjouterPanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableProduits.getSelectedRow();
                if (selectedRow != -1) {
                    // Récupérer l'ID du produit et appeler le DAO pour ajouter au panier
                    JOptionPane.showMessageDialog(CatalogueFenetre.this, "Produit ajouté au panier !");
                } else {
                    JOptionPane.showMessageDialog(CatalogueFenetre.this, "Veuillez sélectionner un produit.");
                }
            }
        });

        // Navigation vers la fenêtre Panier
        btnVoirPanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PanierFenetre().setVisible(true);
                dispose();
            }
        });

        // Navigation vers la fenêtre Historique
        btnHistorique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistoriqueFenetre().setVisible(true);
                dispose();
            }
        });

        // Déconnexion : retour à la page de connexion
        btnDeconnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConnexionFenetre().setVisible(true);
                dispose();
            }
        });

        add(panel);
    }
}
