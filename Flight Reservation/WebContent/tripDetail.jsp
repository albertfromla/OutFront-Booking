<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flights</title>
</head>
<body>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<h1>Airport Flight List</h1>

	<table
		border="1"
		style= "width=100%">
		<tr>
			<th>Flight Id</th>
			<th>Airline Id</th>
			<th>From</th>
			<th>To </th>
			<th>Legs</th>
			<th>Seats</th>
			<th>Total Fare</th>
			<th>Book</th>
		</tr>

		<c:forEach items="${stops}" var="u">
			<tr>
				<td>${u.getFlightId()}</td>
				<td>${u.getAirlineId()}</td>				
				<td>${u.getArrTime()}</td>
				<td>${u.getDeptTime()}</td>
				<td>${u.getStops()}</td>
				<td>${u.getSeats()}</td>
				<td>${u.getTotalFare()}</td>
				<td><a href="FlightServlet?type=book&sdid=<c:out value='${u.getS_d_id()}' />&start=
				<c:out value='${u.getStartStop()}' />&ts=<c:out value='${u.getStops()}' />&tf=<c:out value='${u.getTotalFare()}' />&
				seats=<c:out value='${u.getSeats()}' />">
				Book</a></td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>