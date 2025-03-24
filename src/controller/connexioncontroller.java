package controller;

import DAO.ClientDAO;
import model.Client;
import view.connexionvue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class connexioncontroller {

    private connexionvue loginView;
    private ClientDAO clientDAO;

    public connexioncontroller(connexionvue loginView, ClientDAO clientDAO) {
        this.loginView = loginView;
        this.clientDAO = clientDAO;

        // Ajouter ActionListener au bouton
        this.loginView.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierConnexion();
            }
        });
    }

    private void verifierConnexion() {
        String email = loginView.getEmailField().getText();
        String motDePasse = String.valueOf(loginView.getPasswordField().getPassword());

        // Vérification via DAO
        Client client = clientDAO.verifierConnexion(email, motDePasse);

        if (client != null) {
            JOptionPane.showMessageDialog(null, "✅ Connexion réussie, bienvenue " + client.getNom());

            // Selon type_client, redirige :
            if (client.getTypeClient().equalsIgnoreCase("admin")) {
                System.out.println("Ouvrir AdminDashboardView");
                // new AdminDashboardView(client);
            } else {
                System.out.println("Ouvrir ClientDashboardView");
                // new ClientDashboardView(client);
            }

            loginView.dispose(); // Ferme la fenêtre de login

        } else {
            JOptionPane.showMessageDialog(null, "❌ Email ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
