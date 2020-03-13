package mypackage;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Connessione{
	
	static Connection con=null;
	
	static {
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource)context.lookup("java:comp/env/jdbc/universita2");
			con=ds.getConnection();
		}catch(NamingException e) {
			e.printStackTrace();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static Connection getCon() {
		
		return con;
		
	}

}
