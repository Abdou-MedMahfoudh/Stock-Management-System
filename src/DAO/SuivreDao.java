package DAO;
import metier.Suivre;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import BD.DatabaseConnection;

public class SuivreDao implements Dao<Suivre> {

    private Connection connection;
    private Connection con=null;
    private String requet=null ;
    private PreparedStatement pstmt = null ;
    ResultSet resultset = null ;
     


    public SuivreDao() {
    	try {
            con = DatabaseConnection.getConnection();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    


	@Override
	public Suivre get(String id) {
		// creation de l'objet de type suivre pour rendre 
		Suivre suivre = null ;
		// la requette 
		
		String sql = "select * from suivre where matricule = ? ";
		try {
		//l'execution de la requette 
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			
			String matricule = rs.getString("matricule");
			String code      = rs.getString("code");
			String semester  = rs.getString("semester");
			Date date        =  rs.getDate("date");
			int heures       = rs.getInt("heures");
			double note      = rs.getDouble("note");
			
			suivre = new Suivre (matricule,code,semester,date,heures,note);
			
			
			
			
		}
		
		
		System.out.println(suivre.toString());
		return suivre ;
		
		
		}
		
		catch(SQLException e ) {
			e.printStackTrace();
			
			
		}
		
		
		return suivre;
	}


	@Override
	public List<Suivre> getAll() {
		 List<Suivre> list = new ArrayList<>();
	        String query = "SELECT * FROM suivre";

	        try (PreparedStatement pstmt = con.prepareStatement(query)) {
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	String matricule = rs.getString("matricule");
	    			String code      = rs.getString("code");
	    			String semester  = rs.getString("semester");
	    			Date date        =  rs.getDate("date");
	    			int heures       = rs.getInt("heures");
	    			double note      = rs.getDouble("note");
	    			
	    			Suivre s = new Suivre (matricule,code,semester,date,heures,note);
	    			
	                list.add(s);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        for(Suivre s : list) {
	        	System.out.println(s.toString());
	        }

	        return list;
	    }

	


	@Override
	public void save(Suivre t) {
		// TODO Auto-generated method stub
		
		String sql = " insert into suivre (matricule , code , semester, date , heures , note ) values ( ?,?,?,?,?,?)";
	    try { pstmt = con.prepareStatement(sql);
	          pstmt.setString(1,t.getMatricule() );
	          pstmt.setString(2,t.getCode() );
	          pstmt.setString(3,t.getSemester() );
	          pstmt.setDate(4, (Date) t.getDate());
	          pstmt.setInt(5, t.getHeures());
	          pstmt.setDouble(6, t.getNote());
	          
	          pstmt.executeUpdate();
	    System.out.println("saisis");
	    	
	    }
	    catch (SQLException e ) {
	    	e.printStackTrace();
	    	
	    }
		
	}


	@Override
	public void update(Suivre t, String[] params) {
		
		// TODO Auto-generated method stub
		String sql = "update Suivre set matricule = ? , code = ? , semester = ?, date  = ?, heures  = ?, note  = ? where matricule = ?";
		
		try{
			
			pstmt = con.prepareStatement(sql);
	          pstmt.setString(1,params[0] );
	          pstmt.setString(2,params[1] );
	          pstmt.setString(3,params[2] );
	          pstmt.setDate(4, java.sql.Date.valueOf(params[3]));
	          pstmt.setInt(5, Integer.parseInt(params[4]));
	          pstmt.setDouble(6,Double.parseDouble(params[5]));
	          pstmt.setString(7,t.getMatricule() );
	          pstmt.executeUpdate();
	    System.out.println("saisis");
			
			
		
		}
	catch (SQLException e) {
		e.printStackTrace();
	}
		
	}


	@Override
	public void delete(Suivre t) {
		String sql = "delete from suivre where matricule = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,t.getMatricule());
			pstmt.executeUpdate();
			 System.out.println("supprimer avec success");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
			
		
	}
    
    
    
    


public static void main(String[] args) {
	SuivreDao sdao = new SuivreDao ();
	sdao.getAll();
	
}

}


    