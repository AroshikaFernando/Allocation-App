<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<style>
#header.fixed-header {
	position: fixed;
	top: 0;
	width: 100%;
}

.dropbtn {
	background-color: black;
	color: white;
	padding: 10px;
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
	min-width: 200px;
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
<body>

	<div id="header" class="fixed-header"
		style="background: black; height: 15%; padding: 2%;">
		<div class="container" style="float: left; color: #ffffff;">
			<img id="wso2" src="images/wso2_transparent.png" align="left"
				height="70" width="150" alt=""> <br> <br>

			<p align="justify">

				<font size="5" color="white"><i>&nbsp;&nbsp;ENGINEER'S
						REPORT</i></font>
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
					<img id="wso2" src="images/menu2.png" height="35" width="35" alt="">
				</button>
				<div class="dropdown-content" align="left">

					<a href="${pageContext.request.contextPath}/qsp">QSP trainee<br>
						pipeline</a> <a href="${pageContext.request.contextPath}/summary">summary
						Report</a>
				</div>
			</div>

		</div>



	</div>


</body>
</html>