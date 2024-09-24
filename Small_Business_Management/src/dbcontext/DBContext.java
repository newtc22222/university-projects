package dbcontext;

import java.sql.Connection;

public class DBContext {
	public static Connection getConnection() {
		return MySQLConnection.getConnection();
	}
}
