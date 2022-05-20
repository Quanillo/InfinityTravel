package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;

//import Code.Cliente;
//import Code.Producto;

public class DbReserva {

	private static String bd = "XE";
	private static String login = "IT";
	private static String password = "Passw0rd";
	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;
	private static Connection connection = null;
	private static Statement st;
	private static ResultSet rs;
	
	private final double porcentajeDevolucion = 90;
	
	public void connect() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection=DriverManager.getConnection(url, login, password);
			if(connection != null) {
				System.out.println("connected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public double anularReserva(String id) {

		try {

			st = connection.createStatement();
			st.executeQuery("delete from reserva_producto where id_res = '"+id+"'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return getImporteReserva(id) * porcentajeDevolucion / 100;
	}
	
	public double getImporteReserva(String id) {
		
		double n = 0;
		
		try {

			st = connection.createStatement();
			rs = st.executeQuery("select importe_total from reserca where id_res = '"+id+"'");
			n = rs.getDouble(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	public String reserva_generateId () {

		int n = 0;

		try {

			st = connection.createStatement();
			rs = st.executeQuery("select count(*) from reserva");

			while(rs.next()) 
				n = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "RES" + String.format("%06d", n+1);
	}
}