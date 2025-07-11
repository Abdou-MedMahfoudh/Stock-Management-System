package DAO;
import java.util.ArrayList;


public interface Dao<T> {
	

		T get(String id);
		
		ArrayList<T> getAll();
		
		void save (T t);
		
		void update (T t , String [] params);
		
		void delete ( T t );
		
		
       
	}



