package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {
	
	private static String bd = "XE";
	private static String login = "infinitytravel";
	private static String password = "Passw0rd";
	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;
	static Connection connection = null;
	
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
	
}