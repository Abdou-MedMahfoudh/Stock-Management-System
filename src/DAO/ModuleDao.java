package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import BD.DatabaseConnection;
import metier.Module;

public class ModuleDao implements Dao<Module> {
    private Connection con = null;
    private String requet = null;
    private Statement stm = null;
    ResultSet resultset = null;

    public ModuleDao() {
        try {
            con = DatabaseConnection.getConnection();
            stm = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Module get(String id) {
        Module module = null;
        requet = "SELECT * FROM module_ WHERE code = '" + id + "'";
        try {
            resultset = stm.executeQuery(requet);
            if (resultset.next()) {
                String code = resultset.getString(1);
                int credit = resultset.getInt(2);
                double coefficient = resultset.getDouble(3);
                module = new Module(code, credit, coefficient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return module;
    }

    @Override
    public List<Module> getAll() {
        List<Module> modules = new ArrayList<>();
        requet = "SELECT * FROM module_";
        try {
            resultset = stm.executeQuery(requet);
            while (resultset.next()) {
                String code = resultset.getString(1);
                int credit = resultset.getInt(2);
                double coefficient = resultset.getDouble(3);
                modules.add(new Module(code, credit, coefficient));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modules;
    }

    @Override
    public void save(Module t) {
        requet = "INSERT INTO module_ (code, credit, coefficient) VALUES ('" + t.getCode() + "', " + t.getCredit() + ", " + t.getCoefficient() + ")";
        try {
            stm.executeUpdate(requet);
            System.out.println("Module saved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Module t, String[] params) {
        requet = "UPDATE module_ SET credit = " + params[0] + ", coefficient = " + params[1] + " WHERE code = '" + t.getCode() + "'";
        try {
            stm.executeUpdate(requet);
            System.out.println("Module updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Module t) {
        requet = "DELETE FROM module_ WHERE code = '" + t.getCode() + "'";
        try {
            stm.executeUpdate(requet);
            System.out.println("Module deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
