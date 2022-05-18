package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Code.Cliente;
import Code.Experiencia;

public class Db {

	private static String bd = "XE";
	private static String login = "IT";
	private static String password = "Passw0rd";
	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;
	private static Connection connection = null;
	private static Statement st;
	private static PreparedStatement pst;
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

	public boolean register_InsertClientes (Cliente c) {

		boolean insertado = false;

		try {

			String sql= "insert into cliente (user_id, username, pass, mail) values(? , ? , ?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, c.getUsername());
			pst.setString(2, c.getPass());
			pst.setString(3, c.getCorreo());

			insertado = pst.executeUpdate()>0; 

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				pst.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}

		return insertado;
	}

	public boolean register_usernameAlreadyExists(String username) {
		try {
			String s = "";
			st = connection.createStatement();
			rs = st.executeQuery("select user_id from cliente where username = '"+username+"'");
			while(rs.next())
				s = rs.getString("user_id");

			if(s == "")
				return false;
			else
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean login_validUser(String username, String pass) {

		try {
			String s = "";
			st = connection.createStatement();
			rs = st.executeQuery("select pass from cliente where username = '"+username+"'");
			while(rs.next())
				s = rs.getString("pass");

			if(s.equals(pass))
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public Cliente selectCliente (String user) {
		Cliente cliente=null;
		String username, pass, mail;

		try {
			String sql="select username, pass, mail from cliente where binary user=?";
			pst=connection.prepareStatement(sql);
			pst.setString(1, user);
			rs=pst.executeQuery();

			if(rs.next()) {

				username=rs.getString("username");
				pass=rs.getString("pass");
				mail=rs.getString("mail");
				cliente=new Cliente(username, pass, mail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cliente;

	}

	public double ciudad_getLatitud (String ciudad) {

		double lat = 0;
		
		try {

			st = connection.createStatement();
			rs = st.executeQuery("select lat from ciudad where nom_ciu = '"+ciudad+"'");

			while(rs.next()) 
				lat = rs.getDouble(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lat;
	}
	
	public double ciudad_getLongitud (String ciudad) {

		double lon = 0;
		
		try {
			
			st = connection.createStatement();
			rs = st.executeQuery("select lon from ciudad where nom_ciu = '"+ciudad+"'");

			while(rs.next()) 
				lon = rs.getDouble(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lon;
	}
	
	public ArrayList<String> listarCiudades () {
		
		ArrayList<String> lista = new ArrayList<String>();

		try {
			st = connection.createStatement();
			rs = st.executeQuery("select nom_ciu from ciudades");
			while(rs.next())
				lista.add(rs.getString("nom_ciu"));


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
}


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

		return String.format("%03d", n);
	}
	
	
}