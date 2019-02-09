<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<title>Custom Login Page</title>

<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
}
</style>
</head>
<body onload='document.loginForm.j_name.focus();'>
	<h3>Custom Login Page</h3>

	<%
		String errorString = (String) request.getAttribute("error");
		String errorDesc = (String) request.getAttribute("description");
		if (errorString != null && errorString.trim().equals("true")) {
			out.println("<span class=\"errorblock\">"+ errorDesc +". Please try again");
		}
	%>

	<form name='loginForm' action="login" method='GET' modelAttribute="userData">

		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='name' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="submit" /></td>
				<td><input name="reset" type="reset" /></td>
			</tr>
		</table>

	</form>
</body>
</html>