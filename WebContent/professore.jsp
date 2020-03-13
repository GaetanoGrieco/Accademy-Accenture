<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<style>

body {
	font-family:"Calibri";
	background-size: 1400px 700px;
	
}

#mydiv {
    position:fixed;
    top: 50%;
    left: 50%;
    width:30em;
    height:18em;
    margin-top: -9em; /*set to a negative number 1/2 of your height*/
    margin-left: -15em; /*set to a negative number 1/2 of your width*/
    /*border: 1px solid #ccc;*/
    /*background-color: #f3f3f3;*/
}
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

h2 {
   color: white;
}

</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body background="http://www.aljanh.net/data/archive/img/1328355460.jpeg">

	<%  String messaggio=(String)request.getAttribute("messaggio");
		String nome = (String) session.getAttribute("nome");
		String cognome = (String) session.getAttribute("cognome");
		String materia = (String) session.getAttribute("materia");
		ResultSet appelli = (ResultSet) request.getAttribute("appelli");
		ResultSet elenco = (ResultSet) request.getAttribute("elenco_studenti");
		String nomeMateria = (String) request.getAttribute("Materia");
		String Data = (String) request.getAttribute("Data");
		int idcorso = (int)session.getAttribute("idcorso");

	%>
	<%
		if (nome == null && cognome == null) {

			response.sendRedirect("index.jsp");
		}
	%>
	<div id=myDiv align="center">
	<h2>
		Bentornato:
		<%=nome%>
		<%=cognome%> <br>
		Docente di
		<%=materia%> <br>
		</h2><br><br><br>
		
	
	<%
		if (appelli != null && appelli.next()) {
			appelli.previous();
	%>
	<h2>
		Per la sua materia:
		<%=materia%>
		sono disponibili i seguenti appelli
	</h2>
	<table border=1>
		<tr id="firstRow">
			<th>Seleziona</th>
			<th>ID Appello</th>
			<th>Data</th>
			<th>Aula</th>
		</tr>
		<%
			while (appelli.next()) {
		%>
		<form action="CancellazioneAppello" method="post"><tr>
		<td><input type="radio" name="ID_appello" value="<%=appelli.getInt(1) %>"></td>
			<th><%=appelli.getInt(1)%></th>
			<th><%=appelli.getDate("Data")%></th>
			<th><%=appelli.getString("aula")%></th>
	<%
		}
	%>
		</tr>
	</table>
	<input type="submit" class="button" value="Cancella">
	 <button class="button" type="submit" formaction="StampaStudenti">Stampa Lista Studenti</button>
</form>
	
	<%
		}
	%>
	<%
		if (elenco != null) {
	%>

	<h2>
		Per l'esame <%=nomeMateria%>
		in data <%=Data%> si sono prenotati i seguenti studenti:
	</h2>
	<table border=1>
		<tr id="firstRow">
			<th>Nome</th>
			<th>Cognome</th>
			<th>Matricola</th>
		</tr>
		<%
			while (elenco.next()) {
		%>
		<tr>
			<th><%=elenco.getString("nome")%></th>
			<th><%=elenco.getString("cognome")%></th>
			<th><%=elenco.getString("Matricola") %></th>
			<% }%>
			<% }%>
		</tr>
	</table>
<% 
if(messaggio!=null){%>
<h2 align="center">
<a style="font-family:helvetica; color:red;font-size:40px">
<%out.print(messaggio);%></a> <%-- si poteva fare anche con l'espressione <%=messaggio%> --%>
</h2>
<%} %>
<br><br>
	<a href="inserisci_appello.jsp"><button class="button">Inserisci appello</button></a><br><br><br><br>
	<a href="logout.jsp"><button class="button">Logout</button></a>
</div>
</body>
</html>