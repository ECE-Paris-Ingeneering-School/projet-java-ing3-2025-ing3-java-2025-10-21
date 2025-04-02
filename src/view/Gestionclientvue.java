package view;

import DAO.ClientDAO;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Gestionclientvue extends JFrame {
    private JTable tableClients;
    private DefaultTableModel modeleTable;
    private JTextField champNom, champEmail, champMotDePasse, champTypeClient;
    private ClientDAO clientDAO;

    public Gestionclientvue() {
        super("Gestion des clients");
        clientDAO = new ClientDAO();

        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tableau clients
        modeleTable = new DefaultTableModel(new String[]{"ID", "Nom", "Email", "Type"}, 0);
        tableClients = new JTable(modeleTable);
        JScrollPane scrollPane = new JScrollPane(tableClients);

        // Formulaire
        JPanel panneauFormulaire = new JPanel(new GridLayout(2, 4));
        champNom = new JTextField();
        champEmail = new JTextField();
        champMotDePasse = new JTextField();
        champTypeClient = new JTextField();

        panneauFormulaire.add(new JLabel("Nom"));
        panneauFormulaire.add(new JLabel("Email"));
        panneauFormulaire.add(new JLabel("Mot de passe"));
        panneauFormulaire.add(new JLabel("Type client"));

        panneauFormulaire.add(champNom);
        panneauFormulaire.add(champEmail);
        panneauFormulaire.add(champMotDePasse);
        panneauFormulaire.add(champTypeClient);

        // Boutons
        JPanel panneauBoutons = new JPanel();
        JButton boutonAjouter = new JButton("Ajouter");
        JButton boutonModifier = new JButton("Modifier");
        JButton boutonSupprimer = new JButton("Supprimer");
        JButton boutonRafraichir = new JButton("Rafraîchir");
        JButton boutonRetour = new JButton("↩ Retour au menu");
        boutonRetour.addActionListener(e -> {
            dispose();
            new Menuadminvue().setVisible(true);
        });


        panneauBoutons.add(boutonAjouter);
        panneauBoutons.add(boutonModifier);
        panneauBoutons.add(boutonSupprimer);
        panneauBoutons.add(boutonRafraichir);

        boutonAjouter.addActionListener(e -> ajouterClient());
        boutonModifier.addActionListener(e -> modifierClient());
        boutonSupprimer.addActionListener(e -> supprimerClient());
        boutonRafraichir.addActionListener(e -> chargerClients());
        panneauBoutons.add(boutonRetour);

        // Layout principal
        getContentPane().add(scrollPane, BorderLayout.NORTH);
        getContentPane().add(panneauFormulaire, BorderLayout.CENTER);
        getContentPane().add(panneauBoutons, BorderLayout.SOUTH);

        chargerClients();
    }

    private void chargerClients() {
        modeleTable.setRowCount(0);
        List<Client> clients = clientDAO.listerClients();
        for (Client c : clients) {
            modeleTable.addRow(new Object[]{c.getId(), c.getNom(), c.getEmail(), c.getTypeClient()});
        }
    }

    private void ajouterClient() {
        String nom = champNom.getText();
        String email = champEmail.getText();
        String mdp = champMotDePasse.getText();
        String type = champTypeClient.getText();

        Client client = new Client(nom, email, mdp, type);
        clientDAO.ajouterClient(client);
        chargerClients();
    }

    private void modifierClient() {
        int ligne = tableClients.getSelectedRow();
        if (ligne == -1) return;

        int id = (int) modeleTable.getValueAt(ligne, 0);
        String nom = champNom.getText();
        String email = champEmail.getText();
        String mdp = champMotDePasse.getText();
        String type = champTypeClient.getText();

        Client client = new Client(nom, email, mdp, type);
        client.setId(id);
        clientDAO.modifierClient(client);
        chargerClients();
    }

    private void supprimerClient() {
        int ligne = tableClients.getSelectedRow();
        if (ligne == -1) return;
        int id = (int) modeleTable.getValueAt(ligne, 0);
        clientDAO.supprimerClient(id);
        chargerClients();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Gestionclientvue().setVisible(true));
    }
}