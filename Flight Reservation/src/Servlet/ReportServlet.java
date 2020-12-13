package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ReportDAO;
import Model.Report;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
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

		try {
			switch (action) {
			case "monthReport":
				monthReport(request, response);
				break;
			case "bestCustomer":
				bestCustomer(request, response);
				break;
			case "bestFlight":
				bestFlight(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * Redirect to show month Report
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void monthReport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String yearMonth = request.getParameter("month");
		int month = Integer.parseInt(yearMonth.split("-")[1]);
		int year = Integer.parseInt(yearMonth.split("-")[0]);

		Report r = new Report();
		r.setYear(year);
		r.setMonth(month);

		List<Report> report = ReportDAO.getMonthReport(r);

		request.setAttribute("r", report);
		RequestDispatcher dispatcher = request.getRequestDispatcher("reportDetail.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * Redirect to show best customer
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void bestCustomer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Report r = ReportDAO.getBestCustomer();

		out.write("<h3> Revenue </h3>");

		out.write("<table border=\"1\">");
		out.write("<tr> <th>Passenger Id</th> <th>Passenger Name</th> <th>Revenue</th> </tr> ");
		out.write("<tr> <td>" + r.getPassengerId() + " </td> <td>" + r.getPassengerName() + " </td> <td>" + r.getFare()
				+ " </td>  </tr>");
		out.write("</table>");

	}

	/**
	 * Redirect to show best flight
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void bestFlight(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Report r = ReportDAO.getBestFlight();

		out.write("<h3> Revenue </h3>");

		out.write("<table border=\"1\">");
		out.write("<tr> <th>Flight Id</th> <th>Airline Id</th> <th>Revenue</th> </tr> ");
		out.write("<tr> <td>" + r.getFlightId() + " </td> <td>" + r.getAirlineId() + " </td> <td>" + r.getFare()
				+ " </td>  </tr>");
		out.write("</table>");

	}

}
