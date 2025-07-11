package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Metier.*;
import ConnectionBD.DatabaseConnection;

public class DetailCommandeDao {
    private Connection connection;

    // Constructor to initialize the connection
    public DetailCommandeDao() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE DetailCommande
    public void createDetailCommande(DetailCommande detailCommande) throws SQLException {
        String query = "INSERT INTO DetailCommande (idCommande, idProduit, quantite) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, detailCommande.getIdCommande());
            stmt.setInt(2, detailCommande.getIdProduit());
            stmt.setInt(3, detailCommande.getQuantite());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ DetailCommande by id
    public DetailCommande getDetailCommandeById(int idDetail) throws SQLException {
        String query = "SELECT * FROM DetailCommande WHERE idDetail = ?";
        DetailCommande detailCommande = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idDetail);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idCommande = rs.getInt("idCommande");
                int idProduit = rs.getInt("idProduit");
                int quantite = rs.getInt("quantite");
                detailCommande = new DetailCommande(idDetail, idCommande, idProduit, quantite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detailCommande;
    }

    // UPDATE DetailCommande
    public void updateDetailCommande(DetailCommande detailCommande) throws SQLException {
        String query = "UPDATE DetailCommande SET idCommande = ?, idProduit = ?, quantite = ? WHERE idDetail = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, detailCommande.getIdCommande());
            stmt.setInt(2, detailCommande.getIdProduit());
            stmt.setInt(3, detailCommande.getQuantite());
            stmt.setInt(4, detailCommande.getIdDetail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE DetailCommande by id
    public void deleteDetailCommande(int idDetail) throws SQLException {
        String query = "DELETE FROM DetailCommande WHERE idDetail = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idDetail);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // GET all DetailCommandes for a specific Commande
    public List<DetailCommande> getDetailCommandesByCommandeId(int idCommande) throws SQLException {
        List<DetailCommande> details = new ArrayList<>();
        String query = "SELECT * FROM DetailCommande WHERE idCommande = ? ";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCommande);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DetailCommande detail = new DetailCommande(
                    rs.getInt("idDetail"),
                    rs.getInt("idCommande"),
                    rs.getInt("idProduit"),
                    rs.getInt("quantite")
                );
                details.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return details;
    }

    // GET all DetailCommandes (across all Commandes)
    public List<DetailCommande> getAllDetailCommandes() throws SQLException {
        List<DetailCommande> details = new ArrayList<>();
        String query = "SELECT * FROM DetailCommande";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                DetailCommande detail = new DetailCommande(
                    rs.getInt("idDetail"),
                    rs.getInt("idCommande"),
                    rs.getInt("idProduit"),
                    rs.getInt("quantite")
                );
                details.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return details;
    }
}