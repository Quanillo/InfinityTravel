package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Code.Cliente;

public class Db {

	private static String bd = "XE";
	private static String login = "IT";
	private static String password = "Passw0rd";
	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;
	private static Connection connection = null;
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	//private static String username;
	private static Cliente userConnected; //He tenido que hacer un cliente estatico para que se guarde la lista de productos, creandolo cada vez con el string del username no 
	//funcionaba. De esta forma cada vez que querramos acceder a algo del usuario conectado, solo hay que poner Db.getUserConnected(); ni siquiera es necesario crear un objeto
	//de la clase Db.  :)

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
		}finally {
			try {
				pst.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}

		return cliente;

	}

	public static Cliente getUserConnected() {
		return userConnected;
	}

	public static void setUserConnected(String user) { //este metodo esta copiado de select cliente, para utilizarlo me pedia hacerlo static, asi que he hecho este igual
		Cliente cliente=null;							//por si acaso, ya lo miraremos en clase.
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
		}finally {
			try {
				pst.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		Db.userConnected = cliente;
	}
	
	
}

/*public static String getUsername() {
return username;
}

public static void setUsername(String userConnected) {
Db.username = userConnected;
}*/