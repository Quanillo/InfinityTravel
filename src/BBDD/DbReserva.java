package BBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;

//import Code.Cliente;
//import Code.Producto;

public class DbReserva {

	private static Connection connection = null;
	private static Statement st;
	private static ResultSet rs;
	
	private final double porcentajeDevolucion = 90;
	
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
	/*
	public ArrayList<Producto> listarProductosReservados(Cliente c) {
		
		ArrayList<Producto> lista = new ArrayList<Producto>();
		
		try {

			st = connection.createStatement();
			rs = st.executeQuery("select // from cliente natural join reserva natural join reserva_producto natural join producto where username = '"+c.getUsername()+"'");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return lista;
	}
	*/
}