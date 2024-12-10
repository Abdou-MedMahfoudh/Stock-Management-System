package DAO;
import java.util.List;

public interface Dao<T> {
	
	T get(String id);//select * from t where id id ;
	
	List<T> getAll();//select * from t
	
	void save (T t);// insert into T values (t);
	
	void update (T t , String [] params);//insert into T values ;
	
	void delete ( T t );// delete ;
	
	
	

}
