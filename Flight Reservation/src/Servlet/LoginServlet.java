package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CustomerDAO;
import DAO.FlightDAO;
import DAO.ManagerDAO;
import Model.Airport;
import Model.Customer;
import Model.Manager;

/**
 * Servlet implementation class LoginServlet
 */
@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter("type");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		try {

			if (password.isEmpty() || email.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.include(request, response);

			}

			if (type.equals("Manager")) {

				Manager acc = new Manager();
				acc.setPassword(password);
				acc.setEmail(email);

				acc = ManagerDAO.login(acc);

				if (acc.isValid()) {
					HttpSession session = request.getSession(true);
					session.setAttribute("type", "manager");
					session.setAttribute("currentSessionAccount", acc.getName());

					List<Airport> category = FlightDAO.getAirports();
					session.setAttribute("category", category);
					response.sendRedirect("managerDashboard.jsp"); // logged-in page

				} else {
					response.sendRedirect("login.jsp"); // login page
				}

			} else {

				Customer acc = new Customer();
				acc.setPassword(password);
				acc.setEmail(email);

				acc = CustomerDAO.login(acc);

				if (acc.isValid()) {
					HttpSession session = request.getSession(true);
					session.setAttribute("currentSessionAccount", acc.getId());
					session.setAttribute("type", "customer");

					List<Airport> category = FlightDAO.getAirports();
					session.setAttribute("category", category);

					response.sendRedirect("customerDashboard.jsp"); // logged-in page
				} else {
					response.sendRedirect("login.jsp"); // login page
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}