package BBDD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Code.Billete;
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

	public static String compra_generateId () {

		int n = 0;

		try {

			st = connection.createStatement();
			rs = st.executeQuery("select count(*) from compra");

			while(rs.next()) 
				n = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "PRD" + String.format("%06d", n+1);
	}

	public static String billete_generateId (String origen, String destino) {

		int n = 0;

		try {

			st = connection.createStatement();
			rs = st.executeQuery("select count(*) from billete");

			while(rs.next()) 
				n = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return String.format(origen.substring(0,3).toUpperCase()+String.format(destino.substring(0,3).toUpperCase())+"%03d", n+1);
	}
	/*
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
	 */

	@SuppressWarnings("static-access")
	public void insertBillete (Billete b) {

		b.setIdProducto(billete_generateId(b.getOrigen(), b.getDestino()));
		
		try {

			String sql= "insert into producto values(? , ?)";//ampliable a asiento y clase
			pst = connection.prepareStatement(sql);
			pst.setString(1, b.getIdProducto());
			pst.setDouble(2, b.billete_getPrecio(b.getOrigen(), b.getDestino()));

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

			String sql= "insert into billete (id_prod, id_origen, id_destino) values(? , ? , ?)";//ampliable a asiento y clase
			pst = connection.prepareStatement(sql);
			pst.setString(1, b.getIdProducto());
			pst.setString(2, b.getOrigenId());
			pst.setString(3, b.getDestinoId());

			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}

	public void checkout(ArrayList<Producto> carrito) {

		String id_res = DbReserva.reserva_generateId();
		double total = 0;

		//Se insertan los productos comprados en la tabla de productos
		for(Producto p : carrito) {

			if(p instanceof Billete) {
				Billete aux = (Billete) p;
				insertBillete(aux);
			}
			else {
				try {
					String sql= "insert into producto values(? , ?)";
					pst = connection.prepareStatement(sql);
					pst.setString(1, p.getIdProducto());
					if(p instanceof Billete)
						pst.setDouble(2, p.getImporteProducto());
					else
						pst.setDouble(2, p.getImporteProducto());
					pst.executeUpdate();
					total += p.getImporteProducto();
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

		//Se le crea una reserva al cliente conectado
		try {

			String sql= "insert into reserva values(? , ? , sysdate, ?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, id_res);
			pst.setString(2, Db.getUserConnected().getUsername());
			pst.setDouble(3, total);

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

		//Se asocia a cada uno de los productos comprados la reserva creada
		for(int i = 0; i < carrito.size(); i++) {

			try {

				String sql= "insert into compra values(? , ? , ? , ?, ?, null, null)";
				pst = connection.prepareStatement(sql);
				pst.setString(1, compra_generateId());
				pst.setString(2, id_res);
				pst.setString(3, Db.getUserConnected().getUsername());
				pst.setString(4, carrito.get(i).getIdProducto());
				pst.setDouble(5, carrito.get(i).getImporteProducto());

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
}