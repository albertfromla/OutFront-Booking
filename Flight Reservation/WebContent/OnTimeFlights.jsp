<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Airport Flight Details</title>
</head>
<body>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<h1>On-Time / Delayed Flights</h1>

	<table
		border="1"
		style= "width=100%">
		<tr>
			<th>Flight Id</th>
			<th>Airline Id</th>
			<th>Arrival Time</th>
			<th>Departure Time </th>
			<th>Flight Stop No</th>
			<th>Detail</th>
		</tr>

		<c:forEach items="${stops}" var="u">
			<tr>
				<td>${u.getFlightId()}</td>
				<td>${u.getAirlineId()}</td>				
				<td>${u.getArrTime()}</td>
				<td>${u.getDeptTime()}</td>
				<td>${u.getStopNo()}</td>
				<td>${u.getOnTime()}</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>