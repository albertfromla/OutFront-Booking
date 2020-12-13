package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Connection.ConnectionManager;
import Model.Airport;
import Model.Flight;
import Model.Reservation;
import Model.Stops;
import Model.TripDetail;

/**
 * Data Access Object for Flight related Objects
 * 
 * @author
 *
 */
public class FlightDAO {
	static private Connection currentCon = null;
	static private ResultSet rs, rs2;
	static private PreparedStatement ps, ps2;

	/**
	 * Return all Reservations linked with this Flight
	 * 
	 * @param c
	 * @return
	 */
	public static List<Reservation> getReservations(Flight c) {

		List<Reservation> res = new ArrayList<>();

		String query = "SELECT distinct l.ResrId, r.seatNo, r.meal, r.class , l.FlightId, "
				+ "l.AirlineId, r.PassengerId, r.departure, r.arrival " + "from reservation r, legs l "
				+ "where l.FlightId=? And r.Id = l.ResrId";

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(query);

			ps.setInt(1, c.getId());

			rs = ps.executeQuery();

			while (rs.next()) {
				Reservation r = new Reservation();
				r.setResrId(rs.getInt(1));
				r.setSeatNo(rs.getString(2));
				r.setMeal(rs.getString(3));
				r.setClassType(rs.getString(4));
				r.setFlightNo(rs.getInt(5));
				r.setAirlineId(rs.getString(6));
				r.setDept(rs.getString(8));
				r.setArri(rs.getString(9));
				r.setPassId(rs.getInt(7));

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
	 * Get Record of All Flights
	 * 
	 * @return
	 */
	public static List<Flight> getAllRecords() {
		List<Flight> list = new ArrayList<>();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from flights");
			rs = ps.executeQuery();
			while (rs.next()) {
				Flight u = new Flight();
				u.setId(rs.getInt("id"));
				u.setDaysOfOperating(rs.getInt("DaysOperating"));
				u.setTotalSeats(rs.getInt("TotalSeats"));
				u.setAirlineId(rs.getString("AirlineId"));
				u.setFare(rs.getInt("fare"));

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
	 * All Flights which will Departed form this particular Airport
	 * 
	 * @param a
	 * @return
	 */
	public static List<Stops> getStops(Airport a) {
		List<Stops> list = new ArrayList<>();

		String query = "select a.name, a.city, s.FlightId, s.AirlineId, s.arrTime, s.deptTime, s.stopNo "
				+ "from stopsat s, airports a " + "where airportidorigin = a.id " + "and s.airportIdOrigin = ?";

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(query);
			ps.setString(1, a.getId());

			rs = ps.executeQuery();
			while (rs.next()) {
				Stops u = new Stops();

				u.setAirportName(rs.getString(1));
				u.setAirportCity(rs.getString(2));
				u.setFlightId(rs.getInt(3));
				u.setAirlineId(rs.getString(4));
				u.setArrTime(rs.getTimestamp(5));
				u.setDeptTime(rs.getTimestamp(6));
				u.setStopNo(rs.getInt(7));

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
	 * The flights which are most Active
	 * 
	 * @return
	 */
	public static List<Flight> getMostActiveFlights() {
		List<Flight> list = new ArrayList<>();
		List<Flight> mostActive = new ArrayList<>();
		try {

			list = FlightDAO.getAllRecords();

			// Traversing all Flights to find 'Max Number of Flights'
			int max = 0;
			for (Flight f : list) {
				int count = Integer.toString(f.getDaysOfOperating()).replace("0", "").length();
				if (count > max)
					max = count;
			}

			// Finding the Active Flight by comparing it with 'Max Number of Flights'
			for (Flight f : list) {
				int count = Integer.toString(f.getDaysOfOperating()).replace("0", "").length();
				if (count == max)
					mostActive.add(f);
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeSockets();
		}
		return mostActive;

	}

	/**
	 * Get All Airports
	 * 
	 * @return
	 */
	public static List<Airport> getAirports() {
		List<Airport> list = new ArrayList<>();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from airports");
			rs = ps.executeQuery();
			while (rs.next()) {
				Airport u = new Airport();
				u.setId(rs.getString("id"));
				u.setName(rs.getString("name"));
				u.setCity(rs.getString("city"));
				u.setCountry(rs.getString("country"));
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
	 * Return Flights which Goes from Point A to Point B based on particular Date
	 * and Time.
	 * 
	 * @param tripDetail
	 * @return
	 */
	public static List<TripDetail> getTrips(TripDetail tripDetail) {

		List<TripDetail> stops = new ArrayList<>();

		// If Flight is Non Stop then Don't search for Flight with legs
		// unless we won't find a flight here.
		if (tripDetail.isNonStop()) {
			currentCon = ConnectionManager.getConnection();
			try {
				ps = currentCon.prepareStatement(
						"SELECT s.FlightId, s.AirlineId, s.stopNo, s.arrTime, s.deptTime, s.s_d_id, f.fare "
								+ "FROM stopsat s, flights f "
								+ "WHERE s.FlightId = f.id AND s.AirlineId = f.AirlineId AND airportIdOrigin = ? And airportIdDestination = ? "
								+ " AND Day(deptTime) = Day(?) And Month(deptTime) = Month(?) ");
				ps.setString(1, tripDetail.getFlyinfFromAirport());
				ps.setString(2, tripDetail.getDestinationAirport());
				ps.setTimestamp(3, tripDetail.getDepartDate());
				ps.setTimestamp(4, tripDetail.getDepartDate());
				rs = ps.executeQuery();

				while (rs.next()) {
					TripDetail s = new TripDetail();
					s.setFlightId(rs.getInt(1));
					s.setAirlineId(rs.getString(2));
					s.setStartStop(rs.getInt(3));
					s.setStops(1);
					s.setArrTime(rs.getTimestamp(4));
					s.setDeptTime(rs.getTimestamp(5));
					s.setS_d_id(rs.getInt(6));

					// Discount Featuring based on advance booking
					Timestamp now = new Timestamp(System.currentTimeMillis());
					int diffInDays = (int) ((s.getDeptTime().getTime() - now.getTime()) / (1000 * 60 * 60 * 24));
					s.setTotalFare(tripDetail.getSeats() * rs.getInt(7));

					s.setTotalFare(s.getTotalFare() - (s.getTotalFare() / 100) * diffInDays);

					s.setSeats(tripDetail.getSeats());
					stops.add(s);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeSockets();
			}

		}

		// If No-Stop flight exists then return
		if (stops.size() > 0)
			return stops;

		// For Flights with possible one or many legs
		String query = "SELECT s.s_d_id as sdid From (SELECT s1.s_d_id as sid  FROM stopsat s1 "
				+ "where s1.airportIdOrigin = ? AND Day(deptTime) = Day(?) And Month(deptTime) = Month(?) )  as o , stopsat s WHERE o.sid = s.s_d_id "
				+ "And s.airportIdDestination = ?";
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(query);

			ps.setString(1, tripDetail.getFlyinfFromAirport());
			ps.setTimestamp(2, tripDetail.getDepartDate());
			ps.setTimestamp(3, tripDetail.getDepartDate());
			ps.setString(4, tripDetail.getDestinationAirport());

			rs = ps.executeQuery();

			while (rs.next()) {
				int s_d_id = rs.getInt("sdid");

				// Returns The Group Id of particular Flights that goes from A to B
				ps2 = currentCon.prepareStatement(
						"SELECT s.FlightId, s.AirlineId, s.stopNo , s.deptTime, s.arrTime , f.fare FROM stopsat s , flights f WHERE s.s_d_id = ?"
								+ " And f.id = s.FlightId And f.airlineId = s.AirlineId And airportIdOrigin = ?");
				ps2.setInt(1, s_d_id);
				ps2.setString(2, tripDetail.getFlyinfFromAirport());
				rs2 = ps2.executeQuery();

				TripDetail s = new TripDetail();
				rs2.next();

				s.setFlightId(rs2.getInt(1));
				s.setAirlineId(rs2.getString(2));
				s.setArrTime(rs2.getTimestamp(5));
				s.setS_d_id(s_d_id);

				int count = rs2.getInt(3);
				s.setStartStop(count);

				ps2.close();
				ps2 = null;
				rs2.close();
				rs2 = null;

				// Detail of those particular groups
				ps2 = currentCon.prepareStatement(
						"SELECT s.FlightId, s.AirlineId, s.stopNo , s.deptTime, s.arrTime , f.fare FROM stopsat s , flights f WHERE s.s_d_id = ?"
								+ " And f.id = s.FlightId And f.airlineId = s.AirlineId And airportIdDestination = ?");

				ps2.setInt(1, s_d_id);
				ps2.setString(2, tripDetail.getDestinationAirport());
				rs2 = ps2.executeQuery();

				rs2.next();
				s.setDeptTime(rs2.getTimestamp(4));
				count = (rs2.getInt(3) - count) + 1;

				s.setSeats(tripDetail.getSeats());

				// Discount Featuring based on advance booking
				Timestamp now = new Timestamp(System.currentTimeMillis());
				int diffInDays = (int) ((s.getDeptTime().getTime() - now.getTime()) / (1000 * 60 * 60 * 24));
				s.setTotalFare(tripDetail.getSeats() * rs2.getInt(6));

				s.setTotalFare(s.getTotalFare() - (s.getTotalFare() / 100) * diffInDays);
				s.setStops(count);

				stops.add(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSockets();
		}

		return stops;

	}

	/**
	 * Book Flights by making particular Reservations and Assigning legs with
	 * reservation data
	 * 
	 * @param td
	 * @param pid
	 */
	public static void bookFlight(TripDetail td, int pid) {

		for (int i = 0; i < td.getSeats(); i++) {
			String query = "INSERT INTO reservation ( totalFare, meal, PassengerId, class, "
					+ "seatNo, departure, arrival) VALUES (?, ?, ?, ?, ?, ?, ?)";

			try {
				currentCon = ConnectionManager.getConnection();

				String dept, arri;

				// The Airport for Departure
				ps = currentCon.prepareStatement("Select airportIdOrigin from stopsat where s_d_id = ? And stopno = ?");
				ps.setInt(1, td.getS_d_id());
				ps.setInt(2, td.getStartStop());

				rs = ps.executeQuery();
				rs.next();

				dept = rs.getString(1);

				ps.close();
				rs.close();
				ps = null;
				rs = null;

				// The airport for Arrival
				ps = currentCon
						.prepareStatement("Select airportIdDestination from stopsat where s_d_id = ? And stopno = ?");
				ps.setInt(1, td.getS_d_id());
				ps.setInt(2, td.getStartStop() + td.getStops() - 1);

				rs = ps.executeQuery();
				rs.next();

				arri = rs.getString(1);

				ps.close();
				rs.close();
				ps = null;
				rs = null;

				// Add a new Reservation with Airport and Passenger Data
				ps = currentCon.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, td.getTotalFare() / td.getSeats());
				ps.setString(2, "Chips");
				ps.setInt(3, pid);
				ps.setString(4, "Economy");

				Random r = new Random();
				char c = (char) (r.nextInt(26) + 'A');
				int n = (int) (Math.random() * 50) + 1;
				String s = Integer.toString(n) + c;
				ps.setString(5, s);
				ps.setString(6, dept);
				ps.setString(7, arri);

				ps.executeUpdate();

				rs = ps.getGeneratedKeys();
				rs.next();
				int rid = rs.getInt(1);

				rs.close();
				rs = null;

				ps.close();
				ps = null;

				// Making entries in Legs with Reservation id
				ps = currentCon.prepareStatement(
						"Select s.FlightId, s.AirlineId, s.deptTime from stopsat s where s.s_d_id = ? And s.stopNo >= ?  And s.stopNo < ? + ?");
				ps.setInt(1, td.getS_d_id());
				ps.setInt(2, td.getStartStop());
				ps.setInt(3, td.getStartStop());
				ps.setInt(4, td.getStops());

				rs = ps.executeQuery();

				int count = 1;
				while (rs.next()) {
					ps2 = currentCon.prepareStatement(
							"INSERT INTO legs( FlightId, AirlineId, ResrId, fromStopNo, legNo, deptDate) VALUES (?,?,?,?,?,?)");
					ps2.setString(1, rs.getString(1));
					ps2.setString(2, rs.getString(2));
					ps2.setInt(3, rid);
					ps2.setInt(4, td.getStartStop() + count - 1);
					ps2.setInt(5, count);
					ps2.setTimestamp(6, rs.getTimestamp(3));
					ps2.executeUpdate();
					count++;
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeSockets();
			}
		}
	}

	/**
	 * Return Flights data with Delayed and On-Time departure
	 * 
	 * @return
	 */
	public static List<Stops> lateFlight() {
		List<Stops> list = new ArrayList<>();

		String query = "select s.FlightId, s.AirlineId, s.arrTime, s.deptTime, s.stopNo, s.onTime " + "from stopsat s";

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(query);

			rs = ps.executeQuery();
			while (rs.next()) {
				Stops u = new Stops();

				u.setFlightId(rs.getInt(1));
				u.setAirlineId(rs.getString(2));
				u.setArrTime(rs.getTimestamp(3));
				u.setDeptTime(rs.getTimestamp(4));
				u.setStopNo(rs.getInt(5));
				u.setOnTime(rs.getInt(6));

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
	 * Close Sockets of all Open Connections
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

}
