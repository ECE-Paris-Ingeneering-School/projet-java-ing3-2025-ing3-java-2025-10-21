package view;

import DAO.ClientDAO;
import DAO.CommandeDAO;
import DAO.LigneCommandeDAO;
import model.Client;
import model.Commande;
import model.LigneCommande;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Gestioncommandevue extends JFrame {
    private JTable tableCommandes, tableLignes;
    private DefaultTableModel modeleCommandes, modeleLignes;
    private JLabel labelClient;

    private CommandeDAO commandeDAO;
    private LigneCommandeDAO ligneCommandeDAO;
    private ClientDAO clientDAO;

    public Gestioncommandevue() {
        super("Affichage des commandes");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        commandeDAO = new CommandeDAO();
        ligneCommandeDAO = new LigneCommandeDAO();
        clientDAO = new ClientDAO();

        // Table des commandes
        modeleCommandes = new DefaultTableModel(new String[]{"ID", "ID Client", "Date"}, 0);
        tableCommandes = new JTable(modeleCommandes);
        JScrollPane scrollCommandes = new JScrollPane(tableCommandes);

        // Table des lignes de commande
        modeleLignes = new DefaultTableModel(new String[]{"ID", "Article", "Quantité"}, 0);
        tableLignes = new JTable(modeleLignes);
        JScrollPane scrollLignes = new JScrollPane(tableLignes);

        // Label d'infos client
        labelClient = new JLabel("Client :");

        // Layout principal
        setLayout(new BorderLayout());
        add(scrollCommandes, BorderLayout.NORTH);
        add(scrollLignes, BorderLayout.CENTER);
        add(labelClient, BorderLayout.SOUTH);

        // Action : cliquer sur une commande => charger détails
        tableCommandes.getSelectionModel().addListSelectionListener(e -> chargerDetailsCommande());

        chargerCommandes();
    }

    private void chargerCommandes() {
        modeleCommandes.setRowCount(0);
        List<Commande> commandes = commandeDAO.listerCommandes();
        for (Commande c : commandes) {
            modeleCommandes.addRow(new Object[]{c.getId(), c.getIdClient(), c.getDateCommande()});
        }
    }

    private void chargerDetailsCommande() {
        int ligne = tableCommandes.getSelectedRow();
        if (ligne == -1) return;

        int idCommande = (int) modeleCommandes.getValueAt(ligne, 0);
        int idClient = (int) modeleCommandes.getValueAt(ligne, 1);

        // Charger lignes de commande
        modeleLignes.setRowCount(0);
        List<LigneCommande> lignes = ligneCommandeDAO.listerLignesParCommande(idCommande);
        for (LigneCommande l : lignes) {
            modeleLignes.addRow(new Object[]{l.getId(), l.getIdArticle(), l.getQuantite()});
        }

        // Charger infos client
        List<Client> clients = clientDAO.listerClients();
        for (Client c : clients) {
            if (c.getId() == idClient) {
                labelClient.setText("Client : " + c.getNom() + " (" + c.getEmail() + ")");
                break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Gestioncommandevue().setVisible(true));
    }
}