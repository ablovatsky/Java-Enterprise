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

    <script src="<c:url value='//static/js/jquery-1.11.3.js' />" ></script>
    <script src="<c:url value='//static/js/util.js' />" ></script>
    <script src="<c:url value='//static/js/contract/contract.js' />" ></script>
</head>

<body>
<%@include file="../header.jsp" %>
<div class="generic-container">
    <input type="hidden" id="hdnSession" data-value=<%= session.getAttribute("contractId") %> />

    <div class="well lead"></div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="number">Номер</label>
                <div class="col-md-7">
                    <input type="text" id="number" class="form-control input-sm"/>
                    <div id="numberError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="name">Название</label>
                <div class="col-md-7">
                    <input type="text" id="name" class="form-control input-sm"/>
                    <div id="nameError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="denomination">Наименование</label>
                <div class="col-md-7">
                    <input type="text" id="denomination" class="form-control input-sm" />
                    <div id="denominationError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="axCode">Код АХ</label>
                <div class="col-md-7">
                    <input type="text" id="axCode" class="form-control input-sm" />
                    <div id="axCodeError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="timeStart">Дата начала</label>
                <div class="col-md-7">
                    <input type="month" id="timeStart" format="MM/yyyy"  class="form-control input-sm" />
                    <div id="timeStartError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="timeStop">Дата окончания</label>
                <div class="col-md-7">
                    <input type="month" id="timeStop" class="form-control input-sm" />
                    <div id="timeStopError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="cost">Стоимость без НДС, руб</label>
                <div class="col-md-7">
                    <input type="text" pattern="\d+(\.\d{2})?" id="cost" class="form-control input-sm" />
                    <div id="costError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="plannedSurcharge">Плановая моржа, руб</label>
                <div class="col-md-7">
                    <input type="text" id="plannedSurcharge" class="form-control input-sm" />
                    <div id="plannedSurchargeError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="plannedProfit">Плановая прибыль, руб</label>
                <div class="col-md-7">
                    <input type="text" pattern="\d+(\.\d{2})?" id="plannedProfit" class="form-control input-sm" />
                    <div id="plannedProfitError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="state">Состояние</label>
                <div class="col-md-7">
                    <select size="1" id="state" class="form-control input-sm" >
                    </select>
                    <div id="stateError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="commentFirst">Коментарий 1</label>
                <div class="col-md-7">
                    <input type="textarea" id="commentFirst" class="form-control input-sm" />
                    <div id="commentFirstError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="commentSecond">Коментарий 2</label>
                <div class="col-md-7">
                    <input type="textarea" id="commentSecond" class="form-control input-sm" />
                    <div id="commentSecondError" class="has-error"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">
                <input type="submit" id="btn" class="btn btn-primary btn-sm"/> или <a href="/avectis/contracts/">Отмена</a>
            </div>
        </div>


</div>

</body>
<script>
    $('#btn').bind('click', function(){
        saveContract();
    });
    $('#cost').keypress(function(key) {
        return onePointInInput(key);
    });
    $('#plannedSurcharge').keypress(function(key) {
        return onePointInInput(key);
    });
    $('#plannedProfit').keypress(function(key) {
        return onePointInInput(key);
    });
</script>
</html>