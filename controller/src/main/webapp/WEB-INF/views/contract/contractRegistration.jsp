<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Договор</title>
    <link href="<c:url value='//static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='//static/css/app.css' />" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
    <script src="<c:url value='//static/js/jquery-1.11.3.js' />" ></script>
    <script src="<c:url value='//static/js/worker/worker.js' />" ></script>
</head>

<body>
<%@include file="../header.jsp" %>
<div class="generic-container">
    <input type="hidden" id="hdnSession" data-value=<%= session.getAttribute("ssoId") %> />

    <div class="well lead"></div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="firstName">Имя</label>
                <div class="col-md-7">
                    <input type="text" id="firstName" class="form-control input-sm"/>
                    <div id="firstNameError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="lastName">Фамилия</label>
                <div class="col-md-7">
                    <input type="text" id="lastName" class="form-control input-sm" />
                    <div id="lastNameError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="patronymic">Отчество</label>
                <div class="col-md-7">
                    <input type="text" id="patronymic" class="form-control input-sm" />
                    <div id="patronymicError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="ssoId">SSO ID</label>
                <div class="col-md-7">
                    <input type="text" id="ssoId" pattern="^[a-zA-Z]+$" class="form-control input-sm" />
                    <div id="ssoIdError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="password">Пароль</label>
                <div class="col-md-7">
                    <input type="password" id="password" class="form-control input-sm" />
                    <div id="passwordError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="email">Почтовый адрес</label>
                <div class="col-md-7">
                    <input type="email" id="email" class="form-control input-sm" />
                    <div id="emailError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="workerProfiles">Права</label>
                <div class="col-md-7">
                    <select multiple id="workerProfiles" class="form-control input-sm"  >
                    </select>
                    <div id="workerProfilesError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="subdivision">Подразделение</label>
                <div class="col-md-7">
                    <select size="1" id="subdivision" class="form-control input-sm" >
                    </select>
                    <div id="subdivisionError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">
                <input type="submit" id="btn" class="btn btn-primary btn-sm"/> или <a href="/avectis/administration/workers">Отмена</a>
            </div>
        </div>
</div>
</body>
<script>
    $('#btn').bind('click', function(){
        saveWorker();
    });
</script>
</html>