package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CustomerDAO;
import Model.Customer;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		int state = CustomerDAO.register(c);
		if (state == 1)
			response.sendRedirect("customerDashboard.jsp");
		else
			response.sendRedirect("register.jsp");

	}

}
