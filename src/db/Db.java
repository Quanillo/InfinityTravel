package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import Code.Cliente;

/**
 * Esta clase tiene como fin gestionar el acceso a la base de datos en lo referente a usuarios y acceso.
 */
public class Db {

	private static String bd = "XE";
	private static String login = "IT";
	private static String password = "Passw0rd";
	private static String url = "jdbc:oracle:thin:@localhost:1521:"+bd;
	private static Connection connection = null;
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static Cliente userConnected;
	/**
	 * Conecta la aplicación a la bbdd.
	 */
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
	/**
	 * Inserta el cliente en la bbdd. 
	 * @param c Cliente a insertar.
	 * @return
	 */
	public boolean registrarCliente (Cliente c) {

		boolean insertado = false;

		try {

			String sql= "insert into cliente (username, pass, mail) values(? , ? , ?)";
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
	/**
	 * Comprueba si el nombre de usuario esta ya registrado en la bbdd.
	 * @param username Nombre de usuario.
	 * @return boolean que define si es encontrado o no.
	 */
	public boolean register_usernameAlreadyExists(String username) {
		try {
			String s = "";
			st = connection.createStatement();
			rs = st.executeQuery("select username from cliente where username = '"+username+"'");
			while(rs.next())
				s = rs.getString("username");

			if(s == "")
				return false;
			else
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Comprueba si el usuario ha introducido el dni en la bbdd y esta validado.
	 * @param username Nombre de usuario.
	 * @return Boolean que define si esta validado o no.
	 */
	public boolean userValidado(String username) {
		try {
			String s = "";
			st = connection.createStatement();
			rs = st.executeQuery("select username from cliente where dni is not null and username = '"+ username +"'");
			while(rs.next())
				s = rs.getString("username");

			if(s == "")
				return false;
			else
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Introduce los datos en la bbdd del cliente que se solicitan en la pagina de usuario.
	 * @param user
	 * @return
	 */
	public boolean updateCliente (Cliente user) {

		boolean updateado = false;
		
		try {
			
			String sql= "update cliente set dni = ?, nom = ?, ape = ?, nac = ?, tel = ? where username = ?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, user.getDni());
			pst.setString(2, user.getNombre());
			pst.setString(3, user.getApellidos());
			pst.setDate(4, Date.valueOf(user.getfNacimiento()));
			pst.setInt(5, user.getTelefono());
			pst.setString(6, user.getUsername());
		
			updateado = pst.executeUpdate()>0;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				pst.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		} 

		return updateado;
	}
	/**
	 * Comprueba si la contraseña esta en la bbdd.
	 * @param username Nombre de usuario.
	 * @param pass Contraseña.
	 * @return Boolean que define si es encontrada o no.
	 */
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
			String sql="select username, pass, mail from cliente where username=?";
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
		} finally {
			try {
				pst.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}

		return cliente;

	}
	/**
	 * Devuelve el usuario conectado a la aplicación.
	 * @return
	 */
	public static Cliente getUserConnected() {
		return userConnected;
	}
	/**
	 * Setea el usuario conectado a la aplicación.
	 * @param user
	 */
	public static void setUserConnected(String user) { 
		Cliente cliente=null;							
		String username, pass, mail;

		try {
			String sql="select username, pass, mail from cliente where username=?";
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
		} finally {
			try {
				pst.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		Db.userConnected = cliente;
	}
}