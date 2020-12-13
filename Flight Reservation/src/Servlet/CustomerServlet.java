package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CustomerDAO;
import Model.Customer;
import Model.Reservation;

/**
 * Servlet for Customer/Passenger
 * 
 * @author
 *
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Method Post
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Method Get
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("type");

		// Redirect to proper function
		try {
			switch (action) {
			case "new":
				showNewForm(request, response);
				break;
			case "insert":
				insertAccount(request, response);
				break;
			case "delete":
				deleteAccount(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateAccount(request, response);
				break;
			case "list":
				listAccount(request, response);
				break;
			case "cReservation":
				customerReservation(request, response);
				break;
			case "currentReservation":
				currentReservation(request, response);
				break;
			case "cUpdate":
				showEditForm(request, response);
				break;
			case "cItinerary":
				cItineary(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}

	/**
	 * Redirect to Register Page
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Insert new Customer using CustomerDAO object
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	private void insertAccount(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		Customer c = new Customer();
		c.setFname(request.getParameter("first_name"));
		c.setLname(request.getParameter("last_name"));
		c.setPassword(request.getParameter("password"));
		c.setAddress(request.getParameter("address"));
		c.setState(request.getParameter("state"));
		c.setCity(request.getParameter("city"));
		c.setZipCode(request.getParameter("zip_code"));
		c.setPhone(request.getParameter("phone_number"));
		c.setEmail(request.getParameter("email"));
		c.setCardNo(request.getParameter("card_number"));

		CustomerDAO.register(c);
		response.sendRedirect("CustomerServlet?type=list");
	}

	/**
	 * Deletes an existing Customer using CustomerDAO object
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	private void deleteAccount(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		CustomerDAO.delete(CustomerDAO.getRecordById(id));
		response.sendRedirect("CustomerServlet?type=list");

	}

	/**
	 * Redirect to Edit Customer Form
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		int id;
		// Manager and Customer both can change the data
		HttpSession session = request.getSession();
		if (session.getAttribute("type").equals("manager"))
			id = Integer.parseInt(request.getParameter("id"));
		else
			id = (int) session.getAttribute("currentSessionAccount");

		Customer u = CustomerDAO.getRecordById(id);
		request.setAttribute("u", u);
		RequestDispatcher dispatcher = request.getRequestDispatcher("editPassenger.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * Update existing data from Edit Form Page
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	private void updateAccount(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		Customer c = new Customer();
		c.setId(Integer.parseInt(request.getParameter("id")));
		c.setFname(request.getParameter("first_name"));
		c.setLname(request.getParameter("last_name"));
		c.setAddress(request.getParameter("address"));
		c.setState(request.getParameter("state"));
		c.setCity(request.getParameter("city"));
		c.setZipCode(request.getParameter("zip_code"));
		c.setPhone(request.getParameter("phone_number"));
		c.setCardNo(request.getParameter("card_number"));
		CustomerDAO.update(c);

		HttpSession session = request.getSession();
		if (session.getAttribute("type").equals("manager"))
			response.sendRedirect("CustomerServlet?type=list");
		else if (session.getAttribute("type").equals("customer"))
			response.sendRedirect("customerDashboard.jsp");
	}

	/**
	 * Redirect to show list of Passengers/Customers
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void listAccount(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Customer> listAccount = CustomerDAO.getAllRecords();
		request.setAttribute("listAccount", listAccount);
		RequestDispatcher dispatcher = request.getRequestDispatcher("passengerDetail.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Redirect to show All reservations
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void customerReservation(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();

		Customer c = new Customer();

		if ("manager".equals(session.getAttribute("type")))
			c.setFname(request.getParameter("cName"));
		else {
			c.setId((int) session.getAttribute("currentSessionAccount"));
			c = CustomerDAO.getRecordById(c.getId());
		}

		List<Reservation> res = CustomerDAO.getReservations(c);
		request.setAttribute("res", res);
		RequestDispatcher dispatcher = request.getRequestDispatcher("reservationDetail.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Redirect to show all current Reservations
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void currentReservation(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession(true);

		Customer c = new Customer();
		c.setId((int) session.getAttribute("currentSessionAccount"));

		c = CustomerDAO.getRecordById(c.getId());

		List<Reservation> res = CustomerDAO.getCurrentReservations(c);

		Date date = new Date(new java.util.Date().getTime());
		res.removeIf(r -> r.getDeptDate().before(date));

		request.setAttribute("res", res);
		RequestDispatcher dispatcher = request.getRequestDispatcher("reservationDetail.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Redirect to Show Itinerary of a Particular Flight
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void cItineary(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		HttpSession session = request.getSession();
		List<Reservation> res = CustomerDAO.getItineary(Integer.parseInt(request.getParameter("rid")),
				(int) session.getAttribute("currentSessionAccount"));

		request.setAttribute("res", res);
		RequestDispatcher dispatcher = request.getRequestDispatcher("travelItineary.jsp");
		dispatcher.forward(request, response);

	}

}
