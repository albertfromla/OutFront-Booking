<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Details</title>
</head>
<body>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<h1>Customer List</h1>

	<table style="width=90%"
		border="1">
		<tr>
			<th>Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Address</th>
			<th>State</th>
			<th>City</th>
			<th>Zip Code</th>
			<th>Phone Number</th>
			<th>Email</th>
			<th>Card Number</th>
			
			<th>Edit</th>
			<th>Delete</th>
		</tr>

		<c:forEach items="${listAccount}" var="u">
			<tr>
				<td>${u.getId()}</td>
				<td>${u.getFname()}</td>
				<td>${u.getLname()}</td>
				<td>${u.getAddress()}</td>
				<td>${u.getState()}</td>
				<td>${u.getCity()}</td>
				<td>${u.getZipCode()}</td>
				<td>${u.getPhone()}</td>
				<td>${u.getEmail()}</td>
				<td>${u.getCardNo()}</td>
				
				<td><a href="CustomerServlet?type=edit&id=<c:out value='${u.getId()}' />">Edit</a></td>
				<td><a href="CustomerServlet?type=delete&id=<c:out value='${u.getId()}'/>">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="CustomerServlet?type=new"><button type="submit">Add New Customer</button></a>


</body>
</html>