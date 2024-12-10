package DAO;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import BD.DatabaseConnection;
import metier.Etudiant;
import metier.Suivre;

public class EtudiantDao implements Dao<Etudiant> {
    private Connection con=null;
    private String requet=null ;
    private Statement stm = null ;
    private PreparedStatement pstmt = null ;
    ResultSet resultset = null ;
     
    public EtudiantDao() {
        try {
            con = DatabaseConnection.getConnection();
            stm = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Etudiant get(String id) {
        Etudiant etudiant = null;
        requet = "select * from etudiant where matricule = " +  id;
        try {
           
            resultset = stm.executeQuery(requet);
            while (resultset.next()) {
                int matricule = resultset.getInt(1);
                String nom = resultset.getString(2);
                String prenom= resultset.getString(3);
                etudiant = new Etudiant(matricule, nom,prenom);
                System.out.println(etudiant.toString());
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (etudiant==null) {
        	System.out.println("l'id n'existe pas ");
        }
        return etudiant;
    }

    @Override
    public List<Etudiant> getAll() {
    	List<Etudiant> Etudiants = new ArrayList<Etudiant>();
    	requet = "select * from Etudiant ";
    	try {
    	stm.executeQuery(requet);
    	resultset = stm.executeQuery(requet);
    	  while (resultset.next()) {
              int matricule = resultset.getInt(1);
              String nom = resultset.getString(2);
              String prenom = resultset.getString(3);
              Etudiants.add(new Etudiant (matricule,nom,prenom));
              
    	}
    	  for(Etudiant s : Etudiants) {
	        	System.out.println(s.toString());
    	  
    	}}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
        return Etudiants;
    }

    @Override
    public void save(Etudiant t) {
    	
    
    	 requet = "insert into Etudiant (matricule , nom , prenom) values ( ?,?,?)";
         try {
            
             stm.executeUpdate(requet);
             System.out.println("saisis");
    	
    }
    	 catch(SQLException e ) {
    		 e.printStackTrace();
    	 }
    }
    
    
    @Override
   
    public void update(Etudiant t, String[] params) {
        requet = "UPDATE Etudiant SET matricule = ?, nom = ? , prenom = ? WHERE matricule = ?";
        try {
            pstmt = con.prepareStatement(requet);

            
            pstmt.setInt(1, Integer.parseInt(params[0])); 
            pstmt.setString(2, params[1]); 
            pstmt.setString(3, params[2]);  
            pstmt.setInt(4, t.getMatricule());           

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Etudiant updated: " + t.toString());
            } else {
                System.out.println("No matching record found for update.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Etudiant t) {
    	 requet = "delete from  Etudiant where matricule ="+t.getMatricule();
         try {
            
             stm.executeUpdate(requet);
             System.out.println("supprimer");
    	
    }
    	 catch(SQLException e ) {
    		 e.printStackTrace();
    	 }
    }
    	
    	
    	
    
    
    
    
    
    
    

    public static void main(String[] args) {
        EtudiantDao edao = new EtudiantDao();
     
        
        
        
    }
}