package BBDD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Code.Billete;
import Code.Cliente;
import Code.Producto;

public class DbProducto {

	private static String bd = "XE";
	private static String login = "IT";
	private static String password = "Passw0rd";
	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;
	private static Connection connection = null;
	private static PreparedStatement pst;
	private static Statement st;
	private static ResultSet rs;

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

	public static String producto_generateId () {

		int n = 0;

		try {

			st = connection.createStatement();
			rs = st.executeQuery("select count(*) from producto");

			while(rs.next()) 
				n = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "PRD" + String.format("%06d", n+1);
	}

	public static String billete_generateId () {

		int n = 0;

		try {

			st = connection.createStatement();
			rs = st.executeQuery("select count(*) from billete");

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
	
	public void comprarBillete (Cliente c, Billete b) {

		try {

			String sql= "insert into billete (id_prod, origen, destino) values(? , ? , ?)";//ampliable a asiento y clase
			pst = connection.prepareStatement(sql);
			pst.setString(1, b.getIdProducto());
			pst.setString(2, b.getOrigenId());
			pst.setString(3, b.getDestinoId());

			pst.executeUpdate(); 

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				pst.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		try {

			String sql= "insert into producto (id_prod, id_item, importe_prod) values(? , ? , ?)";//falta meter la fecha pero hay conflicto LocalDate !- Date
			pst = connection.prepareStatement(sql);
			pst.setString(1, producto_generateId());
			pst.setString(2, b.getIdProducto());
			pst.setDouble(3, b.getImporteProducto());

			pst.executeUpdate(); 

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				pst.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void paso2 (ArrayList<Producto> carrito, ArrayList<String> codigos) {
		
		String id_res = DbReserva.reserva_generateId();
		
		try {

			String sql= "insert into reserva values(? , ? , sysdate, ?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, id_res);
			pst.setString(2, Db.getUserConnected().getUsername());
			pst.setDouble(3, Db.getUserConnected().calculaPrecioCarrito());

			pst.executeUpdate(); 

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				pst.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < carrito.size(); i++) {
			
			try {

				String sql= "insert into producto_reservado values(? , ? , ? , ?)";
				pst = connection.prepareStatement(sql);
				pst.setString(1, id_res);
				pst.setString(2, Db.getUserConnected().getUsername());
				pst.setString(3, codigos.get(i));
				pst.setString(4, carrito.get(i).getIdProducto());

				pst.executeUpdate(); 

			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				try {
					pst.close();
				} catch (SQLException e){
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	public ArrayList<String> paso1(ArrayList<Producto> carrito) {
		
		ArrayList<String> codigos = new ArrayList<String>();
		
		for(Producto p : carrito) {
			
			try {
				String id = producto_generateId();
				String sql= "insert into producto values(? , ? , ? , null , null)";
				pst = connection.prepareStatement(sql);
				pst.setString(1, id);
				pst.setString(2, p.getIdProducto());
				pst.setDouble(3, p.getImporteProducto());

				pst.executeUpdate(); 
				codigos.add(id);
			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				try {
					pst.close();
				} catch (SQLException e){
					e.printStackTrace();
				}
			}
			
		}
		return codigos;
	}
}