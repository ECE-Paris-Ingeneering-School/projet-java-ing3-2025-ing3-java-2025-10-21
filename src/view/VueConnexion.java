package view;

import DAO.ClientDAO;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;

public class VueConnexion extends JPanel {
    public VueConnexion(FenetrePrincipale parent) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel emailLabel = new JLabel("Email :");
        JTextField emailField = new JTextField(15);
        JLabel mdpLabel = new JLabel("Mot de passe :");
        JPasswordField mdpField = new JPasswordField(15);
        JButton connexionButton = new JButton("Connexion");

        connexionButton.setFont(new Font("Arial", Font.PLAIN, 12));
        connexionButton.setPreferredSize(new Dimension(150, 30));

        connexionButton.addActionListener((ActionEvent e) -> {
            String email = emailField.getText().trim();
            String mdp = new String(mdpField.getPassword()).trim();

            System.out.println("Tentative de connexion avec : " + email + " / " + mdp);

            ClientDAO dao = new ClientDAO();
            List<Client> clients = dao.listerClients();

            System.out.println("Clients trouvés : " + clients.size());
            for (Client c : clients) {
                System.out.println(" > " + c.getEmail() + " / " + c.getMotDePasse());
            }

            for (Client c : clients) {
                if (c.getEmail().equalsIgnoreCase(email) && c.getMotDePasse().equals(mdp)) {
                    System.out.println("✅ Connexion réussie !");
                    parent.setClientConnecte(c);
                    parent.afficherPage(FenetrePrincipale.PAGE_CATALOGUE);
                    return;
                }
            }

            System.out.println("❌ Identifiants incorrects.");
            JOptionPane.showMessageDialog(this, "Identifiants incorrects.");
        });


        gbc.gridx = 0; gbc.gridy = 0; add(emailLabel, gbc);
        gbc.gridx = 1; add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(mdpLabel, gbc);
        gbc.gridx = 1; add(mdpField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(connexionButton, gbc);
    }
}
