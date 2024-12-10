package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import BD.DatabaseConnection;
import metier.Departement;

public class DepartementDao implements Dao<Departement> {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet resultSet = null;

    public DepartementDao() {
        try {
            con = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Departement get(String id) {
        Departement departement = null;
        String requet = "SELECT * FROM Departement WHERE codeDpt = ?";
        try {
            pstmt = con.prepareStatement(requet);
            pstmt.setString(1, id);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                departement = new Departement(resultSet.getString("codeDpt"), resultSet.getString("titre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departement;
    }

    @Override
    public List<Departement> getAll() {
        List<Departement> departements = new ArrayList<>();
        String requet = "SELECT * FROM Departement";
        try {
            pstmt = con.prepareStatement(requet);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                departements.add(new Departement(resultSet.getString("codeDpt"), resultSet.getString("titre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departements;
    }

    @Override
    public void save(Departement t) {
        String requet = "INSERT INTO Departement (codeDpt, titre) VALUES (?, ?)";
        try {
            pstmt = con.prepareStatement(requet);
            pstmt.setString(1, t.getCodeDpt());
            pstmt.setString(2, t.getTitre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Departement t, String[] params) {
        String requet = "UPDATE Departement SET titre = ? WHERE codeDpt = ?";
        try {
            pstmt = con.prepareStatement(requet);
            pstmt.setString(1, params[0]);
            pstmt.setString(2, t.getCodeDpt());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Departement t) {
        String requet = "DELETE FROM Departement WHERE codeDpt = ?";
        try {
            pstmt = con.prepareStatement(requet);
            pstmt.setString(1, t.getCodeDpt());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}