package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Connection.ConnectionManager;
import Model.Manager;

/**
 * Data Access Object Class for Manager
 * 
 * @author
 *
 */
public class ManagerDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps;

	/**
	 * Login Authentication
	 * 
	 * @param acc
	 * @return
	 */
	public static Manager login(Manager acc) {
		Statement stmt = null;

		String email = acc.getEmail();
		String password = acc.getPassword();

		String searchQuery = "select * from Managers where email='" + email + "' AND password='" + password + "'";

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			if (!more) {
				System.out.println("Sorry, you are not a registered user! Please sign up first");
				acc.setValid(false);
			} else if (more) {
				String name = rs.getString("name");
				System.out.println("Welcome " + name);
				acc.setName(name);
				acc.setValid(true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Log in failed: An Exception has occurred!" + ex);
		} finally {
			closeSockets();
		}
		return acc;
	}

	/**
	 * Close Any open Socket
	 */
	private static void closeSockets() {
		if (currentCon != null) {
			try {
				currentCon.close();
			} catch (Exception e) {
			}
			currentCon = null;
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {

			}
			ps = null;
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {

			}
			rs = null;
		}

	}

}