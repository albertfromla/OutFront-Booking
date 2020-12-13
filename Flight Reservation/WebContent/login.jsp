<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="LoginServlet" method="post">
	<table style="width: 50%">
		<tr>
			<td>Email</td>
			<td><input type="email" name="email" required="required" /></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password" required="required" /></td>
		</tr>
		<tr>
			<td><input type="radio" name="type" value="Customer" onchange="enable(this.form);" />Customer</td>
			<td><input type="radio" name="type" value="Manager"  onchange="enable(this.form);" checked="checked"/>Manager</td>
		</tr>
		<tr>
			<td><input type="submit" value="Login" /></td>
			<td><a href="register.jsp" style="text-decoration:none;"><button id="btn" disabled="disabled">Register</button></a></td>	
		</tr>
	</table>
</form>

<%session.invalidate(); %>


<script lang="javascript">
	function enable(form) {
		if (form.type.value == "Customer") {
      		document.getElementById("btn").disabled = false;
      	} 
		else if (form.type.value == "Manager") {
			document.getElementById("btn").disabled = true;
      	}
	}
</script>

</body>
</html>
