package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionManager;
import Model.Report;

/**
 * Data Access Objects for Report Creations
 * 
 * @author
 *
 */
public class ReportDAO {
	static private Connection currentCon = null;
	static private ResultSet rs;
	static private PreparedStatement ps;

	/**
	 * Reservation Reports of a particular Month
	 * 
	 * @param r
	 * @return
	 */
	public static List<Report> getMonthReport(Report r) {

		List<Report> reports = new ArrayList<>();

		String query = "SELECT DISTINCT l.AirlineId as aid, r.PassengerId as pid, r.totalFare as tf, "
				+ "l.FlightId as fid, l.ResrId as rid, r.seatNo as sn " + "FROM legs l , reservation r "
				+ "WHERE MONTH(deptDate) = ? AND YEAR(deptDate) = ? " + "And l.ResrId = r.Id";

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(query);
			ps.setInt(1, r.getMonth());
			ps.setInt(2, r.getYear());

			rs = ps.executeQuery();

			while (rs.next()) {

				Report re = new Report();
				re.setAirlineId(rs.getString("aid"));
				re.setPassengerId(rs.getInt("pid"));
				re.setFare(rs.getInt("tf"));
				re.setFlightId(rs.getInt("fid"));
				re.setReservationId(rs.getInt("rid"));
				re.setSeatNo(rs.getString("sn"));

				reports.add(re);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSockets();
		}

		return reports;
	}

	/**
	 * Returns Customer with most Generated Revenue
	 * 
	 * @return
	 */
	public static Report getBestCustomer() {
		Report r = new Report();

		String query = "SELECT p.id as id, p.fname as name, sum(r.totalFare) as fare \r\n"
				+ "FROM reservation r , passengers p  \r\n" + "WHERE r.PassengerId = p.id \r\n"
				+ "group by r.PassengerId";
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(query);

			rs = ps.executeQuery();

			int max = 0;
			while (rs.next()) {
				// Find max customer from all the customers based on total sum of spending
				if (rs.getInt(3) > max) {

					r.setPassengerId(rs.getInt(1));
					r.setPassengerName(rs.getString(2));
					r.setFare(rs.getInt(3));
					max = rs.getInt(3);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSockets();
		}

		return r;
	}

	/**
	 * Returns Flight with most Generated Revenue
	 * 
	 * @return
	 */
	public static Report getBestFlight() {
		Report r = new Report();

		String query = "SELECT fid, aid, sum(tf) as totalFare\r\n"
				+ "FROM (SELECT DISTINCT r.totalFare as tf , i.FlightId as fid, i.AirlineId as aid, i.ResrId as rid\r\n"
				+ "	  FROM legs i, reservation r \r\n" + "	  WHERE i.ResrId = r.Id\r\n"
				+ "      GROUP BY i.FlightId,i.AirlineId,i.ResrId) as GroupTable\r\n" + "GROUP by fid,aid";
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(query);

			rs = ps.executeQuery();

			int max = 0;
			while (rs.next()) {
				// Find max Flight from all the Flights based on total sum of spending
				if (rs.getInt(3) > max) {
					r.setFlightId(rs.getInt(1));
					r.setAirlineId(rs.getString(2));
					r.setFare(rs.getInt(3));
					max = rs.getInt(3);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSockets();
		}

		return r;
	}

	/**
	 * Revenue of a particular FLight
	 * 
	 * @param fid
	 * @return
	 */
	public static Report getFlightRevenue(int fid) {
		Report r = new Report();

		String query = "Select sum(totalFare) FROM ( SELECT distinct r.Id as rid  FROM legs l, reservation r "
				+ "where l.ResrId = r.Id  And l.FlightId = ?  GROUP BY r.id ) as T,  reservation r "
				+ "where T.rid = r.Id";
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(query);
			ps.setInt(1, fid);

			rs = ps.executeQuery();

			while (rs.next()) {
				r.setFare(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSockets();
		}

		return r;
	}

	/**
	 * Revenue of a particular Customer
	 * 
	 * @param fid
	 * @return
	 */
	public static Report getCustomerRevenue(int fid) {
		Report r = new Report();

		String query = "select sum(r.totalFare) from reservation r where r.PassengerId = ?";
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(query);
			ps.setInt(1, fid);
			rs = ps.executeQuery();

			while (rs.next()) {
				r.setFare(rs.getInt(1));
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			closeSockets();
		}

		return r;
	}

	/**
	 * Close any Socket whihc is Open
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
