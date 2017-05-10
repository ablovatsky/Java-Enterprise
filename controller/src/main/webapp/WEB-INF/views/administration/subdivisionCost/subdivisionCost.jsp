<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Норма час подразделений</title>
    <link href="<c:url value='//static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='//static/css/app.css' />" rel="stylesheet"/>

    <script src="<c:url value='//static/js/jquery-1.11.3.js' />" ></script>
    <script src="<c:url value='//static/js/subdivision/subdivisionCost.js' />" ></script>
</head>

<body>
<%@include file="../../header.jsp" %>
<div class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="lead">Список подразделений </span>
            <input type="month" id="month" format="MM/yyyy"  class="form-control input-sm" onchange="showSubdivisionsCost(this.value)"/>
        </div>
        <table class="table table-hover">
        </table>
    </div>
    <div class="row">
        <div class="form-actions floatRight">
            <input type="submit" id="btn" class="btn btn-primary btn-sm" value="Применить"/></a>
        </div>
    </div>
</div>
</body>
<script>
    $('#btn').bind('click', function(){
        saveCosts();
    });
</script>
</html>
