package DAO;
import Metier.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ConnectionBD.DatabaseConnection;

public class ProduitDao {
    private Connection connection;

    public ProduitDao() {
        try {
             connection = DatabaseConnection.getConnection();
            
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addProduit(Produit produit) throws SQLException {
        String query = "INSERT INTO Produit (nom, prix, quantiteStock) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, produit.getNom());
        stmt.setDouble(2, produit.getPrix());
        stmt.setInt(3, produit.getQuantiteStock());
        stmt.executeUpdate();
    }

    public List<Produit> getAllProduits() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT * FROM Produit";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Produit produit = new Produit(
                rs.getInt("idProduit"),
                rs.getString("nom"),
                rs.getDouble("prix"),
                rs.getInt("quantiteStock")
            );
            produits.add(produit);
        }
        return produits;
    }
    
    
    public Produit getProduitbyid(int idProduit) throws SQLException {
        Produit produit = null ;
        String query = "SELECT * FROM Produit where idProduit = "+ idProduit;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
             produit = new Produit(
                rs.getInt("idProduit"),
                rs.getString("nom"),
                rs.getDouble("prix"),
                rs.getInt("quantiteStock")
            );
           
        }
        
		return produit ;
    }

    public void updateProduit(Produit produit) throws SQLException {
        String query = "UPDATE Produit SET nom = ?, prix = ?, quantiteStock = ? WHERE idProduit = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, produit.getNom());
        stmt.setDouble(2, produit.getPrix());
        stmt.setInt(3, produit.getQuantiteStock());
        stmt.setInt(4, produit.getIdProduit());
        stmt.executeUpdate();
    }

    public void deleteProduit(int idProduit) throws SQLException {
        String query = "DELETE FROM Produit WHERE idProduit = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, idProduit);
        stmt.executeUpdate();
    }

    public void decrementStock(int idProduit, int quantity) throws SQLException {
        String query = "UPDATE Produit SET quantiteStock = quantiteStock - ? WHERE idProduit = ? AND quantiteStock >= ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, quantity);
        stmt.setInt(2, idProduit);
        stmt.setInt(3, quantity); //  sufficient stock
        int rows = stmt.executeUpdate();
        if (rows == 0) {
            System.out.println(" stock Insufficient pour produit ID : " + idProduit);
        }
    }
    public void updateStock(int idProduit, int quantity) throws SQLException {
        String query = "UPDATE Produit SET quantiteStock =  ? WHERE idProduit = ? ";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, quantity);
        stmt.setInt(2, idProduit);
        
        stmt.executeUpdate();
       
    }
   
}