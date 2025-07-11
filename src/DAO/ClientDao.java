package DAO;
import Metier.* ;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ConnectionBD.DatabaseConnection;

public class ClientDao {
    private Connection connection;

    public ClientDao() {
        try {
             connection = DatabaseConnection.getConnection();
            
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addClient(Client client) throws SQLException {
        String query = "INSERT INTO Client (nom, email, telephone) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, client.getNom());
        stmt.setString(2, client.getEmail());
        stmt.setString(3, client.getTelephone());
        stmt.executeUpdate();
    }

    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Client";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Client client = new Client(
                rs.getInt("idClient"),
                rs.getString("nom"),
                rs.getString("email"),
                rs.getString("telephone")
            );
            clients.add(client);
        }
        return clients;
    }

    public void updateClient(Client client) throws SQLException {
        String query = "UPDATE Client SET nom = ?, email = ?, telephone = ? WHERE idClient = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, client.getNom());
        stmt.setString(2, client.getEmail());
        stmt.setString(3, client.getTelephone());
        stmt.setInt(4, client.getIdClient());
        stmt.executeUpdate();
    }

    public void deleteClient(int idClient) throws SQLException {
        String query = "DELETE FROM Client WHERE idClient = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, idClient);
        stmt.executeUpdate();
    }
}
