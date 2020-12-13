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

	<h1>Airport Flight List</h1>

	<table
		border="1"
		style= "width=100%">
		<tr>
			<th>Name</th>
			<th>City</th>
			<th>Flight Id</th>
			<th>Airline Id</th>
			<th>Arrival Time</th>
			<th>Departure Time </th>
			<th>Flight Stop No</th>
		</tr>

		<c:forEach items="${stops}" var="u">
			<tr>
				<td>${u.getAirportName()}</td>
				<td>${u.getAirportCity()}</td>
				<td>${u.getFlightId()}</td>
				<td>${u.getAirlineId()}</td>				
				<td>${u.getArrTime()}</td>
				<td>${u.getDeptTime()}</td>
				<td>${u.getStopNo()}</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>