<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<style>

.button {
	border: 2px solid #a100ff;
	font-weight: bold;
	background-color: #a100ff;
	border-radius: 5px;
	color: white;
	padding: 16px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	-webkit-transition-duration: 0.4s;
	transition-duration: 0.4s;
	cursor: pointer;
}

.button:hover {
	border: 2px solid #a100ff;
	background-color: #ffffff;
	color: #a100ff;
}

table {
	width: 50%;
	margin: 20px auto;
	table-layout: auto;
}

.fixed {
	table-layout: fixed;
}

table, td, th {
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	border: solid 1px #000;
	text-align: center;
}

.w {
	width: 400px;
}

tbody{
    background-color: rgba(255, 255, 255, 0.9);
}

#firstRow{
	font-weight: bold;
	color: #ffffff;
	background-color: #a100ff;
}

h2{
  color: white;
}

</style>
<meta charset="ISO-8859-1">
<title>Area Studenti</title>
</head>
<body background="http://www.aljanh.net/data/archive/img/1328355460.jpeg">

	<%
		String matricola = (String) session.getAttribute("matricola");
		String nome = (String) session.getAttribute("nome");
		String cognome = (String) session.getAttribute("cognome");
		String messaggio = (String) request.getAttribute("messaggio");
		ResultSet res = (ResultSet) request.getAttribute("tabella_corso");
		ResultSet res1 = (ResultSet) request.getAttribute("elenco_appelli");
		String materia = (String) request.getAttribute("materia");
		ResultSet res11 = (ResultSet) request.getAttribute("tabella_prenotazioni");
		String data = (String) request.getAttribute("data");
		String materia2 = (String) request.getAttribute("materia2");
	%>
	<%
		if (matricola == null) {

			response.sendRedirect("index.jsp");
		}
	%>
	<div align="center">
		<h2>
			Benvenuto
			<%=nome%>
			<%=cognome%>
		</h2>
		<br>
		<%
			
				if(res11!=null && res11.next()){
					res11.previous();
		%>
		<table border=1>
			<tr id="firstRow">
				<th>Seleziona</th>
				<th>ID prenotazione</th>
				<th>Materia</th>
				<th>Data</th>
				<th>Aula</th>
			</tr>

			<%
				while (res11.next()) {
			%>
			<form action="CancellaPren" method="post">
				<tr>
					<td><input type="radio" name="idprenotazione"
						value="<%=res11.getInt("idpren")%>"></td>
					<th><%=res11.getInt("idpren")%></th>
					<th><%=res11.getString("materia")%></th>
					<th><%=res11.getString("data")%></th>
					<th><%=res11.getString("aula")%></th>
					<%
						}
					%>
				</tr>
		</table>
		<br> <input type="submit" class="button"
			value="Cancella Prenotazione">
		</form>
		<br>
		<br>
		<br>
		<br>
		<%
			}
			
		%>
		<%
			if (res != null) {
		%>
		<table border=1>
			<tr id="firstRow">
				<th>Seleziona</th>
				<th>ID corso</th>
				<th>Materia</th>
				<th>Nome docente</th>
				<th>Cognome docente</th>
			</tr>
			<%
				while (res.next()) {
			%>
			<form action="Prenotazione" method="post">
				<tr>
					<td><input type="radio" name="materia"
						value="<%=res.getInt("idcorso")%>"></td>
					<th><%=res.getInt("idcorso")%></th>
					<th><%=res.getString("materia")%></th>
					<th><%=res.getString("nome")%></th>
					<th><%=res.getString("cognome")%></th>
					<%
						}
					%>
				</tr>
		</table>
		<br> <input type="submit" class="button"
			value="Seleziona materia"> <br> <br>
		<%
			}
		%>
		</form>

		<%
			if (res1 != null) {
		%>
		<p>
			Per l'esame di
			<%=materia%>
			sono disponibili i seguenti appelli:
		</p>
		<table border=1>
			<tr>
				<th>Seleziona</th>
				<th>ID Appello</th>
				<th>Data</th>
			</tr>
			<%
				while (res1.next()) {
			%>
			<form action="Prenota" method="post">
				<tr>
					<td><input type="radio" name="appello"
						value="<%=res1.getInt(1)%>"></td>
					<th><%=res1.getInt(1)%></th>
					<th><%=res1.getDate("Data")%></th>
					<%
						}
					%>
				</tr>
		</table>
		<input type="submit" class="button" value="Prenota">
		</form>
		<%
			}
		%>
		<%
			if (messaggio != null) {
		%>
		<p align="center">
			<a style="font-family: helvetica; color: red; font-size: 40px">
				<%
					out.print(messaggio);
				%>
			</a>
			<%-- si poteva fare anche con l'espressione <%=messaggio%> --%>
		</p>
		<%
			}
		%>

		<%
			if (materia2 != null && data != null) {
		%>
		<p>
			Prenotazione effettuata con successo in data
			<%=data%>
			per il corso
			<%=materia2%></p>
		<%
			}
		%>
		<br>
		<br>
		<br> <a href="logout.jsp"><button class="button">Logout</button></a>
	</div>
</body>
</html>