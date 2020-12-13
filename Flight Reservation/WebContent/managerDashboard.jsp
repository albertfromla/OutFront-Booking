<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
</head>
<body>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>Dashboard</h3>

<table border="1">
	<tr><td>
		<form action="CustomerServlet?type=list" method="post">
			<label>Passenger Details</label>
			<input type="Submit" value="View"/>
		</form>
	</td></tr>
	
	<tr><td>
		<form action="ReportServlet?type=monthReport" method="post">
			<label>Report (month and year):</label>
  			<input type="month" name="month" required="required"/>
  			<input type="submit" value="View"/>
		</form>
	</td></tr>

	<tr><td>
		<form action="CustomerServlet?type=cReservation" method="post">
			<table style="with:50%">
				<tr>
					<td><label>Search Customer Reservations</label>
				</tr>
				<tr>
					<td><input type="text" placeholder="Enter Customer First Name" name="cName" required="required"/></td>
				</tr>
				<tr>
					<td><input type="Submit" value="Search"/></td>
				</tr>
			</table>
		</form>
	</td></tr>
	
	<tr><td>
		<form action="ReportServlet?type=bestCustomer" method="post">
			<label>Best Customer</label>
			<input type="submit" value="View"/>
		</form>
	</td></tr>
	
	<tr><td>
		<form action="ReportServlet?type=bestFlight" method="post">
			<label>Best Flight</label>
			<input type="submit" value="View"/>
		</form>
	</td></tr>

	<tr><td>
		<form action="FlightServlet?type=fReservation" method="post">
			<table style="with:50%">
				<tr>
					<td><label>Search Flight Reservations</label>
				</tr>
				<tr>
					<td><input type="text" placeholder="Enter Flight Id" name="fId" required="required"/></td>
				</tr>
				<tr>
					<td><input type="Submit" value="Search"/></td>
				</tr>
			</table>
		</form>
	</td></tr>

	<tr><td>
		<form action="FlightServlet?type=fActiveFlights" method="post">
			<table style="with:50%">
				<tr>
					<td><label>Most Active Flights</label>
				</tr>
				<tr>
					<td><input type="Submit" value="View"/></td>
				</tr>
			</table>
		</form>
	</td></tr>

	<tr><td>
		<form action="FlightServlet?type=flate" method="post">
			<table style="with:50%">
				<tr>
					<td><label>On-Time / Delayed Flights</label>
				</tr>
				<tr>
					<td><input type="Submit" value="View"/></td>
				</tr>
			</table>
		</form>
	</td></tr>


	<tr><td>
		<form action="FlightServlet?type=fList" method="post">
			<table style="with:50%">
				<tr>
					<td><label>All Flight</label>
				</tr>
				<tr>
					<td><input type="Submit" value="View"/></td>
				</tr>
			</table>
		</form>
	</td></tr>

	<tr><td>
		<form action="FlightServlet?type=fAirport" method="post">
			<label>Flight by Airport</label>
			<select name="airport">
				<c:forEach items="${sessionScope.category}" var="category">
        			<option value="${category.id}">${category.name}</option>
    			</c:forEach>
			</select>
  			<input type="Submit" value="View"/>
		</form>
	</td></tr>
	
	<tr><td>
		<a href="login.jsp"><button>Logout</button></a>
	</td></tr>
</table>
</body>
</html>