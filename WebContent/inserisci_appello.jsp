<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.time.*"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>

body {
	font-family:"Calibri";
	background-size: 1400px 700px;	
}

h3 {
	color:#000000;
}

form{
    width: 390px;
    background: rgba(254,254,254,0.7);
    border-radius: 10px;
    overflow: hidden;
    padding: 55px 5px;
    box-shadow: 0 5px 10px 0 rgba(0,0,0,.1);
    -moz-box-shadow: 0 5px 10px 0 rgba(0,0,0,.1);
    -webkit-box-shadow: 0 5px 10px 0 rgba(0,0,0,.1);
    -o-box-shadow: 0 5px 10px 0 rgba(0,0,0,.1);
    -ms-box-shadow: 0 5px 10px 0 rgba(0,0,0,.1);
}



</style>
<meta charset="ISO-8859-1">
<title>Inserisci appello</title>
</head>
<body background="http://www.aljanh.net/data/archive/img/1328355460.jpeg">
<%String messaggio =(String)request.getAttribute("messaggio");
String dataLocale = LocalDate.now().plusDays(10).format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
		int idcorso = (int)session.getAttribute("idcorso");
		
%>

<form action="InserisciApp" method="post">
<h3>Inserire data appello: </h3> <br>
<input type="date" name="data" min="<%=dataLocale%>" >
<h3>Inserisci aula</h3>
<input type="radio" name="aula" value="Aula1" checked>Aula1<br>
<input type="radio" name="aula" value="Aula2" >Aula2<br>
<input type="radio" name="aula" value="Aula3" >Aula3<br>
<input type="radio" name="aula" value="Aula4" >Aula4<br>
<input type="submit" name="inserisci" value="Crea appello" >
<input type="hidden" name="idmateria" value=<%=idcorso%>>

</form>

<% 
if(messaggio!=null){%>
<p align="center">
<a style="font-family:helvetica; color:yellow;font-size:40px">
<%out.print(messaggio);%></a> <%-- si poteva fare anche con l'espressione <%=messaggio%> --%>
</p>
<form action="logout.jsp" method="post">
<input type="submit" name="torna" value="Logout" >
</form>


<%} %>
</body>
</html>