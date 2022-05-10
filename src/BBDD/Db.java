package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Code.Cliente;

public class Db {
	
	private static String bd = "XE";
	private static String login = "infinitytravel";
	private static String password = "Passw0rd";
	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;
	static Connection connection = null;
	PreparedStatement pst=null;
	
	
	public static void connect() {
		
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
	
	public boolean insertClientes (Cliente c) {
		boolean insertado=false;

		try {
			String sql= "insert into cliente (username, pass) values(?,?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1,c.getUsername());
			pst.setString(2, c.getPass());

			insertado=pst.executeUpdate()>0;        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertado;
	}

	public Cliente selectCliente (String user) {
		Cliente cliente=null;
		ResultSet rs;
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


}