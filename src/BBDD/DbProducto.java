package BBDD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbProducto {

	private static Connection connection = null;
	private static Statement st;
	private static ResultSet rs;
	
	public String producto_generateId () {

		int n = 0;

		try {

			st = connection.createStatement();
			rs = st.executeQuery("select count(*) from producto");

			while(rs.next()) 
				n = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return String.format("%03d", n+1);
	}
	
	public boolean producto_existe(String id) {

		int n = 0;
		
		try {

			st = connection.createStatement();
			rs = st.executeQuery("select count(*) from producto where reserva_id = '"+id+"'");

			while(rs.next()) 
				n = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(n>0)
			return true;
		else
			return false;

	}
}