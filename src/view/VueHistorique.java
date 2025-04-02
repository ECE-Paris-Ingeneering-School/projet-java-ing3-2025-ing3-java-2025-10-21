package view;

import DAO.CommandeDAO;
import model.Client;
import model.Commande;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VueHistorique extends JPanel {
    public VueHistorique(FenetrePrincipale parent) {
        setLayout(new BorderLayout());
        JTextArea historique = new JTextArea();
        JButton retourBtn = new JButton("Retour Catalogue");

        retourBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        retourBtn.setPreferredSize(new Dimension(160, 30));
        retourBtn.addActionListener(e -> parent.afficherPage(FenetrePrincipale.PAGE_CATALOGUE));

        JPanel bas = new JPanel();
        bas.add(retourBtn);

        add(new JScrollPane(historique), BorderLayout.CENTER);
        add(bas, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(() -> {
            List<Commande> commandes = new CommandeDAO().listerCommandes();
            Client client = parent.getClientConnecte();
            StringBuilder sb = new StringBuilder("📜 Vos commandes :\n");
            for (Commande c : commandes) {
                if (c.getIdClient() == client.getId()) {
                    sb.append(c).append("\n");
                }
            }
            historique.setText(sb.toString());
        });
    }
}
