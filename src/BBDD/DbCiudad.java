package BBDD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbCiudad {

	private static String bd = "XE";
	private static String login = "IT";
	private static String password = "Passw0rd";
	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;
	private static Connection connection = null;
	private static Statement st = null;
	private static ResultSet rs = null;

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

	//-------------------------------------------------------------
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

	//-------------------------------------------------------------
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

	//-------------------------------------------------------------
	public ArrayList<String> listarCiudades () {

		ArrayList<String> lista = new ArrayList<String>();

		try {
			st = connection.createStatement();
			rs = st.executeQuery("select nom_ciu from ciudad");
			while(rs.next())
				lista.add(rs.getString("nom_ciu"));


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	//-------------------------------------------------------------
	public ArrayList<String> listarCiudadesConAlojamiento () {

		ArrayList<String> lista = new ArrayList<String>();

		try {
			st = connection.createStatement();
			rs = st.executeQuery("select distinct nom_ciu from alojamiento natural join ciudad");
			while(rs.next()) {
				lista.add(rs.getString("nom_ciu"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	//-------------------------------------------------------------
	public ArrayList<String> listarCiudadesConExperiencia () {

		ArrayList<String> lista = new ArrayList<String>();

		try {
			st = connection.createStatement();
			rs = st.executeQuery("select distinct nom_ciu from experiencia natural join ciudad");
			while(rs.next()) {
				lista.add(rs.getString("nom_ciu"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
}