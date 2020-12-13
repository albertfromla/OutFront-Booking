<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book a Trip</title>
</head>
<body>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


	<form action="FlightServlet?type=trip" method="post">
		<table border="1">
			<tr>
				<td colspan="2"><h3 style="text-align: center">Book a Trip</h3></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="radio" name="tripType" value="oneWay" onchange="enable(this.form);" checked="checked">One Way Trip
					<input type="radio" name="tripType" value="roundTrip" onchange="enable(this.form);">Round Trip
				</td>
			</tr>
			<tr>
				<td><label>Flying From</label>
				<select name="flyingFrom">
					<c:forEach items="${sessionScope.category}" var="category">
        				<option value="${category.id}">${category.city} - ${category.name}</option>
    			</c:forEach>
    			</select>
    			</td>

				<td><label>Destination</label>
				<select name="flyingTo">
					<c:forEach items="${sessionScope.category}" var="category">
        				<option value="${category.id}">${category.city} - ${category.name}</option>
    			</c:forEach>
    			</select>
    			</td>
			</tr>
			<tr>
				<td>
					<label>Departing</label>
					<input type="date"  name="dept" required="required">						
				</td>
				<td>
					<label>Returning</label>
					<input type="date" id="retu" name="retu" required="required" disabled="disabled">						
				</td>				
			</tr>
			<tr>
				<td colspan="2">
					<input type="checkbox" name="nonStop" value="nonStop"/>Non Stop
				</td>
			</tr>
			<tr>
				<td> No. of Seats <input type="number" name="seats" min="1" value="1"/> </td> 
				<td>
					<input type="submit" value="Search" >
				</td>
			</tr>
		</table>
	</form>
	
	<br><br>
	
<table border="1">	
	<tr><td>
	<form action="CustomerServlet?type=cReservation" method="post">
		<label>All Reservations</label>
		<input type="submit" value="View">
	</form>
	</td></tr>
	
	<tr><td>
	<form action="CustomerServlet?type=currentReservation" method="post">
		<label>Active Reservations</label>
		<input type="submit" value="View">
	</form>
	</td></tr>

	<tr><td>
	<form action="CustomerServlet?type=cUpdate" method="post">
		<label>Update Profile</label>
		<input type="submit" value="Edit">
	</form>
	</td></tr>
	
	<tr><td>
	<form action="CustomerServlet?type=cItinerary" method="post">
		<label>Travel Itinerary</label>
		<input type="number" name="rid" min="1" placeholder="Reservation Id" required="required"/>
		<input type="submit" value="View">
	</form>
	</td></tr>
	
	<tr><td>
		<form action="ReportServlet?type=bestFlight" method="post">
			<label>Flight - Best Seller</label>
			<input type="submit" value="View"/>
		</form>
	</td></tr>
	
	<tr><td>
		<a href="login.jsp"><button>Logout</button></a>
	</td></tr>


</table>

<script lang="javascript">
	function enable(form) {
		if (form.tripType.value == "oneWay") {
      		document.getElementById("retu").disabled = true;
      	} 
		else if (form.tripType.value == "roundTrip") {
			document.getElementById("retu").disabled = false;
      	}
	}
</script>


</body>
</html>