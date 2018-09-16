<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.js"></script>
<script type="text/javascript">
		$(document).ready(function() 
		    { 
			$("#myTable1").tablesorter(); 
		    } 
		); 
		</script>

<title>Engagements List</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/material-kit.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="css/batch.css">

<style>
html, body {
	height: 100%;
}

#header.fixed-header {
	position: fixed;
	top: 0;
	width: 100%;
}

#tableContainer-1 {
	height: 100%;
	width: 100%;
	display: table;
}

#tableContainer-2 {
	vertical-align: middle;
	align: center;
	display: table-cell;
	height: 100%;
}

#myTable {
	margin: 0 auto;
}

#footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: black;
	color: white;
	text-align: center;
}

.dropbtn {
	background-color: black;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
	cursor: pointer;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #F1F1F1;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #f1f1f1
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown:hover .dropbtn {
	background-color: black;
}

#sorting{
	cursor:pointer;
}

#home {
	text-decoration: none;
	display: inline-block;
	padding: 8px 8px;
}

#home:hover {
	background-color: black;
	color: black;
}

.previous {
	background-color: black;
	color: black;
}
</style>

</head>

<body class="signup-page" style="background: #BDBBBB">

	<div id="header" class="fixed-header"
		style="background:black; height: 15%; padding: 2%">
		<div class="container" style="float: left" style="color:#FFFFF">
			<img id="wso2" src="images/wso2_transparent.png" align="left"
				height="70" width="150">
			<p>
				<br> <br> <font size="5" color="white"><i>&nbsp;&nbsp;ENGAGEMENTS
						SUMMARY</i></font>
			</p>
		</div>

		<div style="float: right">
			<a id="home" href="${pageContext.request.contextPath}/home"
				class="previous"> <img id="wso2" src="images/home2.png"
				height="40" width="40">
			</a>
		</div>

		<div align="right">

			<div class="dropdown">
				<button class="dropbtn">
					<img id="wso2" src="images/menu2.png" height="33" width="33" alt="">
				</button>
				<div class="dropdown-content" align="left">

					<a href="${pageContext.request.contextPath}/qsp">QSP trainee
						pipeline</a>
				</div>
			</div>

		</div>

	</div>


	<div id="tableContainer-1" class="wrapper"
		style="margin-top: 8%; height: 95%">
		<div id="tableContainer-2" class="header"
			style="background: #BDBBBB; align: center">

			<div class="container" style="background: #BDBBBB">
				<table id="myTable1">

					<colgroup>
						<col span="7" style="background-color: #F1F1F1">
					</colgroup>
					<thead>
						<tr id="sorting" style="color: black" valign="middle">
							<th align="center" height="50">&nbsp;&nbsp;Person</th>
							<th align="center">Total #of dev services</th>
							<th align="center">Total #of QSPs</th>
							<th align="center">Total #of QSPs in last 12 months</th>
							<th align="center">Total #of dev services days</th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach items="${sumList.keySet()}" var="person">
							<tr style="color: black" valign="middle">
								<td width="15%">&nbsp;&nbsp;${person}</td>
								<c:forEach items="${sumList.get(person)}" var="value">
									<td width="8%" height="25">${value}</td>
								</c:forEach>
							</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<header id="footer">
		<jsp:include page="_footer.jsp"></jsp:include>
	</header>

</body>

</html>