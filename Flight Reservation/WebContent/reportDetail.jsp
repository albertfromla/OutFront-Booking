<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Report</title>
</head>
<body>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<h1>Month Report</h1>

	<table
		border="1"
		style= "width=90%">
		<tr>
			<th>Flight No</th>
			<th>Passenger Id</th>
			<th>Reservation Id</th>
			<th>Airline</th>
			<th>Seat No</th>
			<th>Total Fare</th>
		</tr>

		<c:forEach items="${r}" var="u">
			<tr>
				<td>${u.getFlightId()}</td>
				<td>${u.getPassengerId()}</td>
				<td>${u.getReservationId()}</td>
				<td>${u.getAirlineId()}</td>
				<td>${u.getSeatNo()}</td>
				<td>${u.getFare()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>