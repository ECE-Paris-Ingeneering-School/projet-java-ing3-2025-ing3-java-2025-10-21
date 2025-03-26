package view;

import controller.interfaceclientcontroller;
import DAO.ConnexionBDD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class connexionvue extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel forgotPasswordLabel;
    private JLabel createAccountLabel;

    public connexionvue() {
        setTitle("Shopping - Connexion");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("Shopping");
        title.setBounds(130, 30, 200, 50);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(new Color(0, 200, 255));
        add(title);

        JLabel connexionLabel = new JLabel("Connexion");
        connexionLabel.setBounds(150, 90, 150, 30);
        connexionLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(connexionLabel);

        emailField = new JTextField();
        emailField.setBounds(75, 150, 250, 40);
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        emailField.setBackground(new Color(255, 255, 200));
        emailField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(emailField);

        passwordField = new JPasswordField();
        passwordField.setBounds(75, 210, 250, 40);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setBackground(new Color(255, 255, 200));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(passwordField);

        loginButton = new JButton("Se connecter");
        loginButton.setBounds(75, 280, 250, 45);
        loginButton.setBackground(new Color(88, 66, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(loginButton);

        forgotPasswordLabel = new JLabel("Connexion admin");
        forgotPasswordLabel.setBounds(130, 340, 200, 30);
        forgotPasswordLabel.setForeground(new Color(30, 144, 255));
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(forgotPasswordLabel);

        createAccountLabel = new JLabel("Première visite ? Créez mon compte");
        createAccountLabel.setBounds(85, 370, 250, 30);
        createAccountLabel.setForeground(new Color(30, 144, 255));
        createAccountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(createAccountLabel);

        // Actions
        loginButton.addActionListener(e -> verifierIdentifiants());

        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Connexion admin à implémenter.");
            }
        });

        createAccountLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Création de compte à implémenter.");
            }
        });

        setVisible(true);
    }

    private void verifierIdentifiants() {
        String email = emailField.getText().trim();
        String mdp = new String(passwordField.getPassword());

        if (email.isEmpty() || mdp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return;
        }

        try {
            Connection conn = ConnexionBDD.getConnexion();
            String query = "SELECT * FROM client WHERE email = ? AND mot_de_passe = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, mdp);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Connexion réussie !");
                dispose(); // Fermer la fenêtre actuelle
                new interfaceclientcontroller(); // Rediriger vers interface client
            } else {
                JOptionPane.showMessageDialog(this, "Identifiants incorrects.");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur de connexion : " + ex.getMessage());
        }
    }

    // Getters utiles (optionnels)
    public JTextField getEmailField() { return emailField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JButton getLoginButton() { return loginButton; }
    public JLabel getForgotPasswordLabel() { return forgotPasswordLabel; }
    public JLabel getCreateAccountLabel() { return createAccountLabel; }
    public static void main(String[] args) {
        new connexionvue();
    }
}
