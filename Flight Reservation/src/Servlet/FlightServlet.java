package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.FlightDAO;
import Model.Airport;
import Model.Flight;
import Model.Reservation;
import Model.Stops;
import Model.TripDetail;

/**
 * Servlet implementation class FlightServlet
 */
@WebServlet("/FlightServlet")
public class FlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Post Method
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Get Method
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("type");

		// Redirect to proper function
		try {
			switch (action) {
			case "fReservation":
				flightReservation(request, response);
				break;
			case "fList":
				listFlight(request, response);
				break;
			case "fAirport":
				listAirportFlight(request, response);
				break;
			case "fActiveFlights":
				mostActiveFlights(request, response);
				break;
			case "trip":
				showTrip(request, response);
				break;
			case "book":
				bookFlight(request, response);
				break;
			case "flate":
				lateFlights(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * Redirects to show Reservation of a particular flight
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void flightReservation(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Flight c = new Flight();
		c.setId(Integer.parseInt(request.getParameter("fId")));
		List<Reservation> res = FlightDAO.getReservations(c);
		request.setAttribute("res", res);
		RequestDispatcher dispatcher = request.getRequestDispatcher("reservationDetail.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Redirect to show all Flights
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void listFlight(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<Flight> listAccount = FlightDAO.getAllRecords();
		request.setAttribute("listAccount", listAccount);
		RequestDispatcher dispatcher = request.getRequestDispatcher("flightDetail.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * Redirect to show all flight from a particular Airport
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void listAirportFlight(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Airport a = new Airport();
		a.setId(request.getParameter("airport"));
		List<Stops> stops = FlightDAO.getStops(a);
		request.setAttribute("stops", stops);
		RequestDispatcher dispatcher = request.getRequestDispatcher("stopsDetail.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * Redirect to show most Active Flights
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void mostActiveFlights(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<Flight> listAccount = FlightDAO.getMostActiveFlights();
		request.setAttribute("listAccount", listAccount);
		RequestDispatcher dispatcher = request.getRequestDispatcher("flightDetail.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * Redirect to Show flights form Point A to Point B
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void showTrip(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String startAirport = request.getParameter("flyingFrom");
		String endAirport = request.getParameter("flyingTo");

		String nonStop = request.getParameter("nonStop");
		String tripType = request.getParameter("tripType");

		int noOfSeats = Integer.parseInt(request.getParameter("seats"));

		Timestamp startTime = null, endTime = null;

		startTime = new Timestamp(Date.valueOf(request.getParameter("dept")).getTime());

		if (tripType.equals("roundTrip"))
			endTime = new Timestamp(Date.valueOf(request.getParameter("retu")).getTime());

		if (startAirport.equals(endAirport)) {
			response.sendRedirect("customerDashboard.jsp");
			return;
		}

		if ((endTime != null && startTime.after(endTime))) {
			response.sendRedirect("customerDashboard.jsp");

			return;
		}

		TripDetail tripDetail = new TripDetail();
		tripDetail.setFlyinfFromAirport(startAirport);
		tripDetail.setDestinationAirport(endAirport);
		tripDetail.setDepartDate(startTime);
		tripDetail.setReturnDate(endTime);
		tripDetail.setNonStop("nonStop".equals(nonStop));
		tripDetail.setOneWay(tripType.equals("oneWay"));
		tripDetail.setSeats(noOfSeats);

		List<TripDetail> stops = FlightDAO.getTrips(tripDetail);
		if (stops.size() == 0) {
			tripDetail.setDepartDate(new Timestamp(startTime.getTime() + 24 * 3600 * 1000));
			stops = FlightDAO.getTrips(tripDetail);
		}

		if (stops.size() == 0) {
			tripDetail.setDepartDate(new Timestamp(startTime.getTime() - 24 * 3600 * 1000));
			stops = FlightDAO.getTrips(tripDetail);
		}

		request.setAttribute("stops", stops);

		RequestDispatcher dispatcher = request.getRequestDispatcher("tripDetail.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * Books a flight form Point A to Point B using FLightDAO object
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void bookFlight(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		int sdid = Integer.parseInt(request.getParameter("sdid"));
		int startStop = Integer.parseInt(request.getParameter("start"));
		int totalStop = Integer.parseInt(request.getParameter("ts"));
		int totalFare = Integer.parseInt(request.getParameter("tf"));
		int seats = Integer.parseInt(request.getParameter("seats"));

		TripDetail td = new TripDetail();
		td.setS_d_id(sdid);
		td.setStartStop(startStop);
		td.setTotalFare(totalFare);
		td.setStops(totalStop);
		td.setSeats(seats);

		HttpSession session = request.getSession();

		FlightDAO.bookFlight(td, (int) session.getAttribute("currentSessionAccount"));

		RequestDispatcher dispatcher = request.getRequestDispatcher("customerDashboard.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * Redirect to show late flights
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void lateFlights(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<Stops> listAccount = FlightDAO.lateFlight();
		request.setAttribute("stops", listAccount);
		RequestDispatcher dispatcher = request.getRequestDispatcher("OnTimeFlights.jsp");
		dispatcher.forward(request, response);

	}

}
