<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reservations</title>
</head>
<body>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<h1>Reservation List</h1>

	<table
		border="1"
		style = "width=90%">
		<tr>
			<th>Reservation Id</th>
			<th>Flight No</th>
			<th>Airline</th>
			<th>Passenger</th>
			<th>Seat No</th>
			<th>Meal</th>
			<th>Class Type</th>
			<th>Departure</th>
			<th>Arrival</th>
			<th>Flight</th>
<!--			<th>Leg No</th> -->
<!--  		<th>From Stop No</th>-->
		</tr>

		<c:forEach items="${res}" var="u">
			<tr>
				<td>${u.getResrId()}</td>
				<td>${u.getFlightNo()}</td>
				<td>${u.getAirlineId()}</td>
				<td>${u.getPassId()}</td>
				<td>${u.getSeatNo()}</td>
				<td>${u.getMeal()}</td>
				<td>${u.getClassType()}</td>
				<td>${u.getDept()}</td>
				<td>${u.getArri()}</td>
				<td>${u.getDomestic()}</td>

			</tr>
		</c:forEach>
	</table>
</body>
</html>