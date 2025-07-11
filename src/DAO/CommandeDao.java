package DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import Metier.*;
import ConnectionBD.DatabaseConnection;

public class CommandeDao {
    private Connection connection;

    // Constructor to initialize the connection
    public CommandeDao() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) 
        
        {
        	
            e.printStackTrace();
            
        }
    }

    // CREATE Commande with details
    public void creationCommandeavecdetail(Commande commande, ArrayList<DetailCommande> details) throws SQLException {
        String query = "INSERT INTO Commande (idClient, dateCommande, statut) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, commande.getIdClient());
            stmt.setTimestamp(2, Timestamp.valueOf(commande.getDateCommande()));
            stmt.setString(3, commande.getStatut());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int idCommande = 0;
            if (rs.next()) {
                idCommande = rs.getInt(1);
            }

            // Insert details
            String detailQuery = "INSERT INTO DetailCommande (idCommande, idProduit, quantite) VALUES (?, ?, ?)";
            try (PreparedStatement detailStmt = connection.prepareStatement(detailQuery)) {
                ProduitDao produitDAO = new ProduitDao();

                for (DetailCommande detail : details) {
                    detailStmt.setInt(1, idCommande);
                    detailStmt.setInt(2, detail.getIdProduit());
                    detailStmt.setInt(3, detail.getQuantite());
                    detailStmt.executeUpdate();

                    // Decrementation stock
                    produitDAO.decrementStock(detail.getIdProduit(), detail.getQuantite());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE Commande without details
    public void creationCommandesansDetails(Commande commande) throws SQLException {
        String query = "INSERT INTO Commande (idClient, dateCommande, statut) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, commande.getIdClient());
            stmt.setTimestamp(2, Timestamp.valueOf(commande.getDateCommande()));
            stmt.setString(3, commande.getStatut());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idCommande = rs.getInt(1);
                System.out.println("Commande created with id: " + idCommande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ Commande by id
    public Commande getCommandeById(int idCommande) throws SQLException {
        String query = "SELECT * FROM Commande WHERE idCommande = ?";
        Commande commande = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCommande);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idClient = rs.getInt("idClient");
                LocalDateTime dateCommande = rs.getTimestamp("dateCommande").toLocalDateTime();
                String statut = rs.getString("statut");
                commande = new Commande(idCommande, idClient, dateCommande, statut);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commande;
    }

    // UPDATE Commande's statut
    public void updateCommandeStatut(int idCommande, String newStatut) throws SQLException {
        String query = "UPDATE Commande SET statut = ? WHERE idCommande = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newStatut);
            stmt.setInt(2, idCommande);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE Commande by id
    public void deleteCommande(int idCommande) throws SQLException {
        String query = "DELETE FROM Commande WHERE idCommande = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCommande);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all Commandes
    public List<Commande> getAllCommandes() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String query = "SELECT * FROM Commande";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Commande commande = new Commande(
                        rs.getInt("idCommande"),
                        rs.getInt("idClient"),
                        rs.getTimestamp("dateCommande").toLocalDateTime(),
                        rs.getString("statut")
                );
                commandes.add(commande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commandes;
    }
}