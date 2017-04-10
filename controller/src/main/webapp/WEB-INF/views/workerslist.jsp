<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Workers List</title>
	<link href="<c:url value='//static/css/bootstrap.css' />" rel="stylesheet"/>
	<link href="<c:url value='//static/css/app.css' />" rel="stylesheet"/>
    <script src="<c:url value='//static/js/jquery-1.11.3.js' />" ></script>
    <script src="<c:url value='//static/js/login.js' />" ></script>
</head>



<body>
	<div class="generic-container">
		<%@include file="authheader.jsp" %>

        <sec:authorize access="hasRole('ADMIN')">
            <div class="well">
                <a href="<c:url value='/newworker' />" onclick="showValues()">Add New Worker</a>
            </div>
        </sec:authorize>

		<div class="panel panel-default">
		  	<div class="panel-heading"><span class="lead">List of Workers </span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>SSO ID</th>
                        <th>Last Name</th>
                        <th>First Name</th>
                        <th>Subdivision</th>
				        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
				        	<th width="100"></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th width="100"></th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${workers}" var="worker">
					<tr>
						<td>${worker.ssoId}</td>
                        <td>${worker.lastName}</td>
                        <td>${worker.firstName}</td>
                        <td>${worker.subdivision.name}</td>
					    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
							<td><a href="<c:url value='/edit-worker-${worker.ssoId}' />" class="btn btn-success custom-width">edit</a></td>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/delete-worker-${worker.ssoId}' />" class="btn btn-danger custom-width">delete</a></td>
        				</sec:authorize>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>

   	</div>
</body>
</html>