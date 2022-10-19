<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css"/> 
</head>
<body>

<nav>

<a href="index.jsp" class="logo">
<h1>Agenda For Teams</h1>
</a>


<div class="insert">
<span> O matheus é bonuito</span>
<form>
<input type="text" name="url">
<input type="submit" value="Enter">
</form>

<%@ page language = "java" import = "Parserr" %>

<%
String url = request.getParameter("url");
Parserr parser = new Parserr();
parser.parse(url);
%>

</div>

<div class="menu">
<ul>
<li><a href="#">Team</a></li>
<li><a href="#">Tasks</a></li>
</ul>
</div>

</nav>

</html>
