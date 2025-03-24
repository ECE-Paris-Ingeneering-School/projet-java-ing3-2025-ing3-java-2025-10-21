package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class connexionvue extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel forgotPasswordLabel;
    private JLabel createAccountLabel;

    public connexionvue() {
        setTitle("Locate IA - Connexion");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Logo Locate IA
        JLabel title = new JLabel("Shopping");
        title.setBounds(130, 30, 200, 50);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(new Color(0, 200, 255)); // Dégradé simulé
        add(title);

        // Connexion label
        JLabel connexionLabel = new JLabel("Connexion");
        connexionLabel.setBounds(150, 90, 150, 30);
        connexionLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(connexionLabel);

        // Email
        emailField = new JTextField();
        emailField.setBounds(75, 150, 250, 40);
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        emailField.setBackground(new Color(255, 255, 200));
        emailField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
       // emailField.setText("quentinlcs@outlook.fr"); // Exemple
        add(emailField);

        // Password
        passwordField = new JPasswordField();
        passwordField.setBounds(75, 210, 250, 40);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setBackground(new Color(255, 255, 200));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(passwordField);

        // Se connecter button
        loginButton = new JButton("Se connecter");
        loginButton.setBounds(75, 280, 250, 45);
        loginButton.setBackground(new Color(88, 66, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(loginButton);

        // Mot de passe oublié
        forgotPasswordLabel = new JLabel("Connexion admin");
        forgotPasswordLabel.setBounds(130, 340, 200, 30);
        forgotPasswordLabel.setForeground(new Color(30, 144, 255));
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(forgotPasswordLabel);

        // Créer mon compte
        createAccountLabel = new JLabel("Première visite ? Créez mon compte");
        createAccountLabel.setBounds(85, 370, 250, 30);
        createAccountLabel.setForeground(new Color(30, 144, 255));
        createAccountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(createAccountLabel);

        // Event examples (you can link them to your controller)
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Lien connexion admin cliqué.");
            }
        });

        createAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Lien création de compte cliqué.");
            }
        });

        setVisible(true);
    }

    // Getters
    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JLabel getForgotPasswordLabel() {
        return forgotPasswordLabel;
    }

    public JLabel getCreateAccountLabel() {
        return createAccountLabel;
    }

    public static void main(String[] args) {
        new connexionvue();
    }
}
