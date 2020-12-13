<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Details</title>
</head>
<body>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<h1>Flight List</h1>

	<table style="width=90%"
		border="1">
		<tr>
			<th>Id</th>
			<th>Days of Operation</th>
			<th>Total Seats</th>
			<th>Airline </th>
			<th>Fare</th>
		</tr>

		<c:forEach items="${listAccount}" var="u">
			<tr>
				<td>${u.getId()}</td>
				<td>${u.getDaysOfOperating()}</td>
				<td>${u.getTotalSeats()}</td>
				<td>${u.getAirlineId()}</td>
				<td>${u.getFare()}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>