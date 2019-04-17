<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.zagorodnikova.tm.entity.Project" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.zagorodnikova.tm.util.DateFormatterUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<head>
    <title>Project list</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<button class="signOut" onclick="location.href='/app'">Sign out</button>
<a class="create" href="project-create?userId=<%=request.getAttribute("userId")%>">
    Add project
    <i class="material-icons" style="font-size:48px;color:#17F1D7" align = "center">add</i>
</a>
<table class="container">
    <thead>
    <tr>
        <th><h1>Name</h1></th>
        <th><h1>Description</h1></th>
        <th><h1>Date created</h1></th>
        <th><h1>Date start</h1></th>
        <th><h1>Date finish</h1></th>
        <th><h1>Status</h1></th>
        <th><h1>Edit</h1></th>
        <th><h1>Delete</h1></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="project" items="${projects}">
        <tr>
            <td align="left" class="toHover">
                <a class="link" href="task-list?projectId=${project.id}">${project.name}</a>
            </td>
            <td align="left"> ${project.description} </td>
            <td align="left"> ${project.dateCreate} </td>
            <td align="left"> ${project.dateStart} </td>
            <td align="left"> ${project.dateFinish} </td>
            <td align="left"> ${project.status} </td>
            <td class="toHover">
                <a href="project-update?id=${project.id}">
                    <i class="material-icons" style="font-size:48px;color:#45D09E" align = "center">border_color</i>
                </a>
            </td>
            <td class="toHover">
                <a href="project-remove?id=${project.id}">
                    <i class="material-icons" style="font-size:48px;color:#E20338" align = "center">delete_sweep</i>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
