<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

#tableContainer-1 {
	height: 100%;
	width: 100%;
	display: table;
}

#tableContainer-2 {
	vertical-align: middle;
	display: table-cell;
	height: 100%;
}

#myTable {
	margin: 0.5 auto;
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

#sorting {
	cursor: pointer;
}

#home {
    text-decoration: none;
    display: inline-block;
    padding: 16px 16px;
}

#home:hover {
    background-color: orange;
    color: black;
}

.previous {
    background-color:orange;
    color: black;
}
</style>

</head>

<body class="signup-page" style="background: #BDBBBB">

	<header>
		
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



	<div id="tableContainer-1" class="wrapper" style="margin-left: 20%">
		<div id="tableContainer-2" class="header"
			style="background: #BDBBBB; margin-top: 15%; vertical-align: middle">

			<div class="container" style="background: #BDBBBB; height: 100%">

				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<div>
					<font size="4" color="#C66022"> <%out.print(request.getAttribute("userName")); %>

					</font>
					<%out.println(" : your engagements are here."); %>
				</div>
				<br>
				<table id="myTable1">

					<colgroup>
						<col span="7" style="background-color: #F1F1F1">
					</colgroup>
					<thead>

						<tr id="sorting" style="color: black" valign="middle">
							<th align="center" height="50">&nbsp;&nbsp;&nbsp;Engagement
								ID</th>
							<th align="center">&nbsp;Allocation ID</th>
							<th align="center">&nbsp;&nbsp;&nbsp;Allocation Type</th>
							<th align="center">&nbsp;&nbsp;Start Date</th>
							<th align="center">&nbsp;&nbsp;End Date</th>
							<th align="center">&nbsp;&nbsp;Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${engagementList}" var="engagement">
							<tr style="color: black" valign="middle">
								<td width="14%" height="25">
									&nbsp;&nbsp;&nbsp;${engagement.getEnId()}</td>
								<td width="11%">${engagement.getAlId()}</td>
								<td width="29%">${engagement.getAltype()}</td>
								<td width="13%">${engagement.getStart()}</td>
								<td width="13%">${engagement.getEnd()}</td>
								<td width="9%">${engagement.getStatus()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- <jsp:include page="_footer.jsp"></jsp:include> -->
		</div>

	</div>
	<header id="footer">
		<jsp:include page="_footer.jsp"></jsp:include>
	</header>

</body>

</html>