<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
	<title>Список договоров</title>
	<link href="<c:url value='//static/css/bootstrap.css' />" rel="stylesheet"/>
	<link href="<c:url value='//static/css/app.css' />" rel="stylesheet"/>
    <link href="<c:url value='//static/css/simplePagination.css' />" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />

	<script src="<c:url value='//static/js/jquery-1.11.3.js' />" ></script>
    <script src="<c:url value='//static/js/worker/workerList.js' />" ></script>
    <script src="<c:url value='//static/js/pagination/jquery.simplePagination.js' />" ></script>
</head>



<body>
<%@include file="../header.jsp" %>
	<div class="generic-container">

		<div class="well">
			<a href='/avectis/contracts/new'>Добавить новый договор</a>
		</div>
		<div class="panel panel-default">
		  	<div class="panel-heading">
                <span class="lead">Список договоров </span>
                <select size="1" id="itemsOnPage" class="form-control input-sm" >
                    <option>10</option>
                    <option>50</option>
                    <option>100</option>
                </select>
                <select size="1" id="l_subdivision" class="form-control input-sm" >
                </select>
                <input type="text" id="l_lastName" class="form-control input-sm" placeholder="Номер договора" required/>
            </div>
			<table class="table table-hover">
	    	</table>
		</div>
        <div id="light-pagination" class="pagination">
        </div>
   	</div>
</body>
<script>
    $( '#itemsOnPage' ).change(function() {
        changeItemsOnPage( this.value);
    });
    $( '#l_subdivision' ).change(function() {
        findBySubdivision( this.value);
    });
    $( '#l_lastName' ).on("change paste keyup", function() {
        findByLastName( this.value);
    });
</script>
</html>