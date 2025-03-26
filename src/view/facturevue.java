package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class facturevue extends JFrame {

    private JTable tableFacture;
    private JLabel labelClient;
    private JLabel labelTotal;
    private JButton boutonValider;
    private JButton boutonRetour;

    public facturevue() {
        setTitle("Facture Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Haut : nom du client
        labelClient = new JLabel("Client : ");
        labelClient.setFont(new Font("Arial", Font.BOLD, 16));
        add(labelClient, BorderLayout.NORTH);

        // Centre : tableau des articles
        String[] colonnes = {"Article", "Quantité", "Prix unitaire", "Remise", "Sous-total"};
        tableFacture = new JTable(new DefaultTableModel(colonnes, 0));
        JScrollPane scrollPane = new JScrollPane(tableFacture);
        add(scrollPane, BorderLayout.CENTER);

        // Bas : total + boutons
        JPanel basPanel = new JPanel(new BorderLayout());

        labelTotal = new JLabel("Total : 0.00 €");
        labelTotal.setFont(new Font("Arial", Font.BOLD, 14));
        basPanel.add(labelTotal, BorderLayout.WEST);

        JPanel boutonPanel = new JPanel();
        boutonValider = new JButton("Valider");
        boutonRetour = new JButton("Retour");
        boutonPanel.add(boutonValider);
        boutonPanel.add(boutonRetour);
        basPanel.add(boutonPanel, BorderLayout.EAST);

        add(basPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Méthodes d’interaction
    public void setNomClient(String nom) {
        labelClient.setText("Client : " + nom);
    }

    public void setTotal(String total) {
        labelTotal.setText("Total : " + total + " €");
    }

    public void ajouterLigneFacture(String article, int quantite, double prix, String remise, double sousTotal) {
        DefaultTableModel model = (DefaultTableModel) tableFacture.getModel();
        model.addRow(new Object[]{article, quantite, prix + " €", remise, sousTotal + " €"});
    }

    public void ajouterListenerValider(ActionListener al) {
        boutonValider.addActionListener(al);
    }

    public void ajouterListenerRetour(ActionListener al) {
        boutonRetour.addActionListener(al);
    }
    public static void main(String[] args) {
        new facturevue();
    }
}
