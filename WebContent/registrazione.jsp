<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form di Registrazione</title>
</head>
<style>
body {
	font-family:"Calibri";
	background-size: 1400px 700px;
}

table {
	 margin-left:550px; 
     margin-right:550px;
}

b {
    color: white;
    margin-top: 1000px;
	margin-left:580px; 
}

tr{
   color:white;
}

input{
   margin-top: 15px;
}

</style>
<%
	String messaggio = (String) request.getAttribute("messaggio");
%>
<%
	if (messaggio != null) {
%>
<p align="center">
	<a style="font-family: helvetica; color: red; font-size: 20px"> <%
 	out.print(messaggio);
 %></a>
</p>
<%
	}
%>

<body background="http://www.aljanh.net/data/archive/img/1328355460.jpeg">
	<b>Registrazione Nuovo Utente </b>
	<br>
	<br>
	<table>
		<form action="Registrazione">
			<tr>
				<td>Nome:</td>
				<td><input type="text" name="nome"> <br>
			<tr>
				<td>Cognome:</td>
				<td><input type="text" name="cognome"> <br>
			<tr>
				<td>UserName:</td>
				<td><input type="text" name="uname"> <br>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="pass"> <br>
				
				<input type="submit" value="Invia">
	<input type="reset">
	</table>

	
	
	</form>
</body>
</html>