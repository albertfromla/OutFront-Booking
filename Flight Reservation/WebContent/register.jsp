<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Outfront Booking Registration</title>
</head>
<body>
<h1>Outfront Register Form</h1>
<form action="RegisterServlet" method="post">
	<table style="width: 50%">
		<tr>
			<td>First Name</td>
			<td><input type="text" name="first_name" required="required"/></td>
		</tr>
		<tr>
			<td>Last Name</td>
			<td><input type="text" name="last_name" required="required" /></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password" required="required" /></td>
		</tr>
		<tr>
			<td>Address</td>
			<td><input type="text" name="address" required="required" /></td>
		</tr>
		<tr>
			<td>State</td>
			<td><input type="text" name="state" required="required" /></td>
		</tr>
		<tr>
			<td>City</td>
			<td><input type="text" name="city" required="required" /></td>
		</tr>
		<tr>
			<td>Zip Code</td>
			<td><input type="text" name="zip_code" required="required" /></td>
		</tr>
		<tr>
			<td>Phone Number</td>
			<td><input type="text" name="phone_number" required="required" /></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><input type="email" name="email" required="required" /></td>
		</tr>
		<tr>
			<td>Card Number</td>
			<td><input type="text" name="card_number" required="required" /></td>
		</tr>
	</table>
	<input type="submit" value="Submit" />
</form>
</body>
</html>
