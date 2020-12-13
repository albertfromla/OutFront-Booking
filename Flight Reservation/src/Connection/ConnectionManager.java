package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connection class
 * 
 * @author
 *
 */
public class ConnectionManager {
	static Connection con;
	static String url;

	public static Connection getConnection() {
		try {
			// Database Url String
			String url = "jdbc:mysql://127.0.0.1/flightreservation";
			Class.forName("com.mysql.jdbc.Driver");

			try {
				// Database connention with id : root and pass : (empty)
				con = DriverManager.getConnection(url, "root", "");

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

}