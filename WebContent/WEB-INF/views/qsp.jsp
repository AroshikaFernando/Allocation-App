<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/material-kit.css" />
<link rel="stylesheet" href="css/style.css" />
<title>QSP list</title>

<style>
#limheight {
	/*your fixed height*/
	-webkit-column-count: 5;
	-moz-column-count: 5;
	column-count: 5;
	/*3 in those rules is just placeholder -- can be anything*/
}

#limheight li {
	display: inline-block; /*necessary*/
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

#header.fixed-header {
	position: fixed;
	top: 0;
	width: 100%;
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

<body class="signup-page" style="background: #f5f5f5">
	<div style="background: black; height: 15%; padding: 2%;">
		<div class="container" style="float: left" style="color:#FFFFF">
			<img id="wso2" src="images/wso2_transparent.png" align="left"
				height="70" width="150">
			<p>
				<br> <br> <font size="5" color="white"><i>&nbsp;&nbsp;QSP
						TRAINEE LIST</i></font>
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

					 <a href="${pageContext.request.contextPath}/summary">summary
						Report</a>
				</div>
			</div>

		</div>

	</div>


	<div style="background: #BDBBBB; margin-top: 0px">
		<br>
		<div class="container">
			<div class="card card-signup" style="background: #f5f5f5;">
				<br>
				<ul id="limheight">
				<li>
					<c:forEach items="${QSPList}" var="user">
						<li><a
							href="${pageContext.request.contextPath}/login?userName=${user}"
							style="text-decoration: none; color: black">${user}</a></li>
						<br>
						<br>
					</c:forEach>
					</li>
				</ul>
			</div>
		</div>
		<br>
	</div>
	<div id="footer">
		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>