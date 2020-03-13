<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<style>

body {
	font-family:"Calibri";
	background-size: 1400px 700px;
}

h1 {
	color: white;
	padding: 80px 40px;
	font-size: 40px;
}

h3 {
	color:#000000;
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
	width: 200px;
	font-weight: bold;
	background-color: #FFFFFF;
	border: 2px solid #FFFFFF;
	border-radius: 10px;
	color: #a100ff;
	padding: 10px 24px;
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
	border: 2px solid #FFFFFF;
	border-radius: 50px;
	background-color: #a100ff;
	color: #FFFFFF;
}

.form{
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

.form h2{
	color: #a100ff;
	font-size: 40px;
	font-weight: bold;
}

input[type=text],
input[type=password] {
	font-size:20px;
	width:200px;
	background: none;
    border: none;
    border-bottom: 2px solid #8842d5;  
}

a {
   color: black;
}

</style>
<meta charset="ISO-8859-1">
<title>Benvenuto</title>
</head>
<body background="http://www.aljanh.net/data/archive/img/1328355460.jpeg">
<%
String messaggio= (String)request.getAttribute("messaggio");
%>
<% 
if(messaggio!=null){%>
<p align="center">
<a style="font-family: calibri; color:yellow;font-size:20px; font-weight: bold;">
<%out.print(messaggio);%></a> <%-- si poteva fare anche con l'espressione <%=messaggio%> --%>
</p>
<%} %>

<div align="center">
<h1 style="color: white" align="center" style="font-size:90px"style="font-family:arial"><i> Welcome To Accenture Accademy</i></h1>
</div>

<div id="mydiv" align="center">
<div class="form">
<h2>Login</h2>
<form action="login" method="post">

<input type="text" placeholder="username" name="username"><p>

<input type="password" placeholder="password" name="password"><p>
<input type="submit" class="button" value="Accedi"><p>
<a href="registrazione.jsp" target="_blank" class="button"> REGISTRAZIONE </a>
</form>
</div>
</div>
</body>
</html>