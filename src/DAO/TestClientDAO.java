package DAO;

import DAO.ClientDAO;
import model.Client;

import java.util.List;

public class TestClientDAO {
    public static void main(String[] args) {
        ClientDAO dao = new ClientDAO();

        // Ajout d'un nouveau client
        Client nouveauClient = new Client("Hugo", "hugo@mail.com", "motdepasse", "NOUVEAU");
        dao.ajouterClient(nouveauClient);

        // Liste des clients
        List<Client> clients = dao.listerClients();
        for (Client c : clients) {
            System.out.println(c);
        }
    }
}
