<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html>
<html>

<head>
     <meta charset="ISO-8859-1">
     <title>Insert title here</title>
</head>

<body>
    <h1>Edit Form</h1>
    <form action="CustomerServlet?type=update" method="post">

	    <input type="hidden" name="id" value=${u.getId() }>
		<table style="width: 50%">
			<tr>
				<td>First Name</td>
				<td><input type="text" name="first_name" value='${ u.getFname() }' required="required"/></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="last_name" value='${u.getLname() }' required="required" /></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><input type="text" name="address" value='${u.getAddress() }' required="required" /></td>
			</tr>
			<tr>
				<td>State</td>
				<td><input type="text" name="state" value='${u.getState() }' required="required" /></td>
			</tr>
			<tr>
				<td>City</td>
				<td><input type="text" name="city" value='${u.getCity() }' required="required" /></td>
			</tr>
			<tr>
				<td>Zip Code</td>
				<td><input type="text" name="zip_code" value='${u.getZipCode() }' required="required" /></td>
			</tr>
			<tr>
				<td>Phone Number</td>
				<td><input type="text" name="phone_number" value='${u.getPhone() }' required="required" /></td>
			</tr>
			<tr>
				<td>Card Number</td>
				<td><input type="text" name="card_number" value='${u.getCardNo() }' required="required" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"/></td>
			</tr>
		</table>
    </form>

</body>

</html>