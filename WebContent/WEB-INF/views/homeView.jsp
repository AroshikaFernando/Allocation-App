<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home Page</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/material-kit.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="css/batch.css">
<link rel="stylesheet" href="css/dropbutton" />

<style>
#background {
	height: 200px;
	width: 50%;
}

#Container-1 {
	vertical-align: middle;
	position: fixed;
	height: 100%;
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
	padding: 4px;
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
	background-color:#F1F1F1;
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
</style>


</head>
<body class="signup-page" style="background: #BDBBBB">

	<header>
		<div >
			<div class="dropdown">
				<button class="dropbtn"><img id="wso2" src="images/menu.png" height="50" width="50" alt=""></button>
				<div class="dropdown-content" align="left">
					<a href="${pageContext.request.contextPath}/summary">Summary
						Report</a> <a href="${pageContext.request.contextPath}/qsp">QSP
						trainee pipeline</a>

				</div>
			</div>
		</div>

		<jsp:include page="_header.jsp"></jsp:include>
	</header>

	<div class="ia-splitter">
		<div class="ia-splitter-left">
			<div class="ia-fixed-sidebar"
				style="width: 20%; height: 83%; visibility: visible; top: 16%; left: 1%">

				<div class="acs-side-bar ia-scrollable-section">
					<div class="acs-side-bar-space-info tipsy-enabled"
						data-configure-tooltip="Edit space details">
						<div class="avatar">
							<h3>
								<b>Engineer List</b>
							</h3>
							<c:forEach items="${userList}" var="user">
								<a class="w3-bar-item"
									href="${pageContext.request.contextPath}/login?userName=${user}">${user}<br></a>
							</c:forEach>

						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

	<div class="wrapper">
		<div style="margin-left: 20%; height: 16%">
			<div class="header">

				<div class="container">
					<br>
					<br>
					<br>
					<br>
					<br>
					<div id="Container-1">
						<div class="col-md-4 col-md-offset-4">
							<!-- set the the size of the search box -->
							<div class="card card-signup">
								<!-- add the white box -->
								<form class="form" method="post"
									action="${pageContext.request.contextPath}/login">
									<div class="header"
										style="background: #FA7004; height: 10px padding: 18px;">
										<div class="social-line">
											<!-- forward orange color bar -->
											<%--<a href="#pablo" class="btn btn-simple btn-just-icon">--%>
											<%--<i class="fa fa-globe"></i>--%>
											<%--</a>--%>
										</div>
									</div>

									<p class="text-divider">Enter username to view your
										engagements</p>
									<div class="content">

										<p class="text-divider" style="align: center; color: red">${errorString}</p>

										<div class="input-group">
											<span class="input-group-addon"> <font face="verdana"
												color="black" size="4.5"><i>Username</i></font> <!-- change font of the  "username" -->
											</span>

											<div class="form-group label-floating">
												<label class="control-label">email</label> <input
													type="text" name="userName" value="${user.userName}"
													class="form-control">
											</div>
										</div>



									</div>
									<div class="footer text-center">
										<input type="submit" class="btn btn-simple btn-primary btn-lg"
											value="Search" />
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<header id="footer">
		<jsp:include page="_footer.jsp"></jsp:include>
	</header>


</body>


<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/material.min.js" type="text/javascript"></script>
<script src="js/nouislider.min.js" type="text/javascript"></script>
<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="js/material-kit.js" type="text/javascript"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/material.min.js" type="text/javascript"></script>
<script src="js/nouislider.min.js" type="text/javascript"></script>
<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="js/material-kit.js" type="text/javascript"></script>
<script src="js/bootstrap-notify.js" type="text/javascript"></script>
<script src="js/material-dashboard.js" type="text/javascript"></script>