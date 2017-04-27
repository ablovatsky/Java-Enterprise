<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value='//static/css/top_menu.css' />" rel="stylesheet"/>
<script src="<c:url value='//static/js/main.js' />" ></script>
<div class="authbar">
    <nav role='navigation'>
        <ul>
            <li><a href="/contracts/main">Главная</a></li>
            <li><a href="#">Договоры</a></li>
            <li><a href="#">Табели</a></li>
            <li><a href="#">Отчеты</a></li>
            <li><a href="#">Администрирование</a>
                <ul>
                    <li><a href="/contracts/administration/subdivisions">Подразделения</a></li>
                    <li><a href="/contracts/administration/workers">Работники</a></li>
                </ul>
            </li>
        </ul>
        <span class="floatRight">
            <a href="/contracts/logout">Выйти</a>
        </span>
        <p class="loggedinworker floatRight"> </p>
    </nav>
</div>
