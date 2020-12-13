<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Itinerary List</title>
</head>
<body>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<h1>Itinerary List</h1>

	<table
		border="1"
		style = "width=90%">
		<tr>
			<th>Flight No</th>
			<th>Airline</th>
			<th>Leg No</th>
	  		<th>From Stop No</th>
	  		<th>Departure</th>
		</tr>

		<c:forEach items="${res}" var="u">
			<tr>
				<td>${u.getFlightNo()}</td>
				<td>${u.getAirlineId()}</td>
				<td>${u.getLegNo()}</td>
				<td>${u.getFromStopNo()}</td>
				<td>${u.getDeptDate()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>