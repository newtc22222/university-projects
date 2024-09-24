package dbcontext;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
	private static String DRIVER_STRING = "com.mysql.cj.jdbc.Driver";
	//private static String DRIVER_STRING = "com.mysql.jdbc.Driver";

	private static String USERNAME = Config.USERNAME;
	private static String PASSWORD = Config.PASSWORD;
	private static String DATABASE_NAME = Config.DB_NAME;
	private static String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;
	//private static String URL = "jdbc:mysql://127.0.0.1:3306/" + DATABASE_NAME;
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER_STRING);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
}
