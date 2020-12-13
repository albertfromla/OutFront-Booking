package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionManager;
import Model.Customer;
import Model.Reservation;

/**
 * Data Access Object for Customer Related Tasks
 * 
 * @author
 *
 */
public class CustomerDAO {
	static private Connection currentCon = null;
	static private ResultSet rs, rs2;
	static private PreparedStatement ps, ps2;

	/**
	 * Registers new Customers/Passengers
	 * 
	 * @param c
	 * @return
	 */
	public static int register(Customer c) {
		Statement stmt2 = null;

		// INser String
		String insertQuery = "INSERT INTO passengers"
				+ "(`fname`, `lname`, `password`, `Address`, `state`, `City`, `zipCode`, `Phone`, `Email`, `cardNo`)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?)";

		String searchQueryEmail = "select * from Passengers where email = '" + c.getEmail().trim() + "'";
		try {
			currentCon = ConnectionManager.getConnection();
			stmt2 = currentCon.createStatement();
			rs2 = stmt2.executeQuery(searchQueryEmail);

			boolean emailExists = rs2.next();

			// Creating Prepare Statement
			if (!emailExists) {
				ps = currentCon.prepareStatement(insertQuery);
				ps.setString(1, c.getFname());
				ps.setString(2, c.getLname());
				ps.setString(3, c.getPassword());
				ps.setString(4, c.getAddress());
				ps.setString(5, c.getState());
				ps.setString(6, c.getCity());
				ps.setString(7, c.getZipCode());
				ps.setString(8, c.getPhone());
				ps.setString(9, c.getEmail());
				ps.setString(10, c.getCardNo());

				ps.executeUpdate();

				System.out.println("Successfully inserted");
				ps.close();
				currentCon.close();
				return 1;
			} else {
				System.out.println("Email already associated to another account!");
				currentCon.close();
				return 2;
			}
		} catch (Exception ex) {
			System.out.println("Log in failed: An Exception has occurred!" + ex);
			return 0;
		} finally {
			closeSockets();
		}

	}

	/**
	 * Login based on Email (Unique) and Password
	 * 
	 * @param acc
	 * @return
	 */
	public static Customer login(Customer acc) {
		Statement stmt = null;

		String email = acc.getEmail();
		String password = acc.getPassword();

		// Data Access through email and pass
		String searchQuery = "select * from Passengers where email='" + email + "' AND password='" + password + "'";
		System.out.println("Your email is " + email);
		System.out.println("Your password is " + password);

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			if (!more) {
				System.out.println("Sorry, you are not a registered user! Please sign up first");
				acc.setValid(false);
			} else if (more) {
				String name = rs.getString("fname");
				System.out.println("Welcome " + name);
				acc.setFname(name);
				acc.setId(rs.getInt("id"));
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
	 * Updates an existing customer
	 * 
	 * @param u
	 * @return
	 */
	public static int update(Customer u) {
		int status = 0;
		try {
			currentCon = ConnectionManager.getConnection();
			String query = "UPDATE passengers SET fname=?, lname=?, Address=?, state=?, city=?, zipCode=?, Phone=?,  cardNo=? WHERE id = ?";

			ps = currentCon.prepareStatement(query);

			ps.setString(1, u.getFname());
			ps.setString(2, u.getLname());
			ps.setString(3, u.getAddress());
			ps.setString(4, u.getState());
			ps.setString(5, u.getCity());
			ps.setString(6, u.getZipCode());
			ps.setString(7, u.getPhone());
			ps.setString(8, u.getCardNo());

			ps.setInt(9, u.getId());
			status = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSockets();
		}
		return status;
	}

	/**
	 * Delete an existing customer
	 * 
	 * @param u
	 * @return
	 */
	public static int delete(Customer u) {
		int status = 0;
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("delete from passengers where id=?");
			ps.setInt(1, u.getId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeSockets();
		}
		return status;
	}

	/**
	 * Return records of all Customer/Passengers
	 * 
	 * @return
	 */
	public static List<Customer> getAllRecords() {
		List<Customer> list = new ArrayList<>();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from passengers");
			rs = ps.executeQuery();
			while (rs.next()) {
				Customer u = new Customer();
				u.setId(rs.getInt("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setAddress(rs.getString("address"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZipCode(rs.getString("zipCode"));
				u.setPhone(rs.getString("phone"));
				u.setEmail(rs.getString("email"));
				u.setCardNo(rs.getString("cardno"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeSockets();
		}
		return list;
	}

	/**
	 * Retrieves a Customer Record based on it's Id (PK)
	 * 
	 * @param id
	 * @return
	 */
	public static Customer getRecordById(int id) {
		Customer u = null;
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from passengers where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				u = new Customer();
				u.setId(rs.getInt("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setAddress(rs.getString("address"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZipCode(rs.getString("zipCode"));
				u.setPhone(rs.getString("phone"));
				u.setEmail(rs.getString("email"));
				u.setCardNo(rs.getString("cardno"));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeSockets();
		}

		return u;

	}

	/**
	 * Return All Reservations of a Customer based on its First Name
	 * 
	 * @param c
	 * @return
	 */
	public static List<Reservation> getReservations(Customer c) {

		List<Reservation> res = new ArrayList<>();

		// For proper data
		String query = " SELECT distinct l.ResrId, r.seatNo, r.meal, r.class , l.FlightId, "
				+ "l.AirlineId, r.departure, r.arrival, r.PassengerId " + "from reservation r, passengers p, legs l "
				+ "where r.PassengerId = p.id " + "And p.fname = ? " + "And r.Id = l.ResrId";

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(query);

			ps.setString(1, c.getFname());

			rs = ps.executeQuery();

			while (rs.next()) {
				Reservation r = new Reservation();
				r.setResrId(rs.getInt(1));
				r.setSeatNo(rs.getString(2));
				r.setMeal(rs.getString(3));
				r.setClassType(rs.getString(4));
				r.setFlightNo(rs.getInt(5));
				r.setAirlineId(rs.getString(6));
				r.setDept(rs.getString(7));
				r.setArri(rs.getString(8));
				r.setPassId(rs.getInt(9));

				// To see if Flight is domestic or international
				ps2 = currentCon.prepareStatement("SELECt a.country from airports a where a.id = ? "
						+ "and a.country = ( select b.country from airports b where b.id = ?)");
				ps2.setString(1, r.getDept());
				ps2.setString(2, r.getArri());

				rs2 = ps2.executeQuery();
				if (rs2.next())
					r.setDomestic("Domestic");
				else
					r.setDomestic("International");

				res.add(r);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSockets();
		}

		return res;
	}

	/**
	 * Return All Active Reservations of a Customer based on its First Name
	 * 
	 * @param c
	 * @return
	 */
	public static List<Reservation> getCurrentReservations(Customer c) {

		List<Reservation> res = new ArrayList<>();

		String query = " SELECT distinct l.ResrId, r.seatNo, r.meal, r.class , l.FlightId, "
				+ "l.AirlineId, r.departure, r.arrival, r.PassengerId, l.deptDate "
				+ "from reservation r, passengers p, legs l " + "where r.PassengerId = p.id " + "And p.fname = ? "
				+ "And r.Id = l.ResrId";

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(query);

			ps.setString(1, c.getFname());

			rs = ps.executeQuery();

			while (rs.next()) {
				Reservation r = new Reservation();
				r.setResrId(rs.getInt(1));
				r.setSeatNo(rs.getString(2));
				r.setMeal(rs.getString(3));
				r.setClassType(rs.getString(4));
				r.setFlightNo(rs.getInt(5));
				r.setAirlineId(rs.getString(6));
				r.setDept(rs.getString(7));
				r.setArri(rs.getString(8));
				r.setPassId(rs.getInt(9));
				r.setDeptDate(rs.getTimestamp(10));

				// To see if Flight is domestic or international
				ps2 = currentCon.prepareStatement("SELECt a.country from airports a where a.id = ? "
						+ "and a.country = ( select b.country from airports b where b.id = ?)");
				ps2.setString(1, r.getDept());
				ps2.setString(2, r.getArri());

				rs2 = ps2.executeQuery();
				if (rs2.next())
					r.setDomestic("Domestic");
				else
					r.setDomestic("International");

				res.add(r);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSockets();
		}

		return res;
	}

	/**
	 * A utility function.Closes Any Open Socket
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

		if (ps2 != null) {
			try {
				ps2.close();
			} catch (Exception e) {

			}
			ps2 = null;
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {

			}
			rs = null;
		}

		if (rs2 != null) {
			try {
				rs2.close();
			} catch (Exception e) {

			}
			rs2 = null;
		}

	}

	public static List<Reservation> getItineary(int rid, int pid) {

		List<Reservation> res = new ArrayList<>();

		String query = " SELECT l.FlightId, l.AirlineId, l.fromStopNo, l.LegNo, l.deptDate "
				+ "from reservation r, legs l where l.ResrId = r.id AND r.PassengerId = ? And l.ResrId = ?";

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(query);

			ps.setInt(1, pid);
			ps.setInt(2, rid);

			rs = ps.executeQuery();

			while (rs.next()) {
				Reservation r = new Reservation();
				r.setFlightNo(rs.getInt(1));
				r.setAirlineId(rs.getString(2));
				r.setFromStopNo(rs.getInt(3));
				r.setLegNo(rs.getInt(4));
				r.setDeptDate(rs.getTimestamp(5));
				res.add(r);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSockets();
		}

		return res;

	}
}
