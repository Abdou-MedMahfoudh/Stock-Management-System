package ConnectionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
	private static Connection con = null;

	static
	{
		String url = "jdbc:mysql:// localhost:3306/BoutiqueDB";
		String user = "root";
		String pass = "8906mysql";
		try {
			
			con = DriverManager.getConnection(url, user, pass);
			
		    }
		catch ( SQLException e) {
			e.printStackTrace();
		} 
	}
	public static Connection getConnection() throws SQLException
	{
		
		return con;
	}
}

