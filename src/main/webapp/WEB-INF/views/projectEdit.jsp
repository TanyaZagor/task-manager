<%@ page import="ru.zagorodnikova.tm.entity.Project" %>
<%@ page import="ru.zagorodnikova.tm.util.DateFormatterUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/WEB-INF/css/edit-style.css"%></style>
<head>
    <title>Edit project</title>
    <link href="../css/edit-style.css" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<button class="signOut" onclick="location.href='/app'">Sign out</button>
<table class="container">
    <thead>
    <tr>
        <th><h1>Name</h1></th>
        <th><h1>Description</h1></th>
        <th><h1>Date start</h1></th>
        <th><h1>Date finish</h1></th>
        <th><h1>Status</h1></th>
        <th><h1>Save</h1></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td align="left"> ${project.name} </td>
        <td align="left"> ${project.description} </td>
        <td align="left"> ${project.dateStart} </td>
        <td align="left"> ${project.dateFinish} </td>
        <td align="left"> ${project.status} </td>
    </tr>
    <form action="project-update" method="POST">
        <tr>
            <td align="left">
                <input class="input" type='text' placeholder='Name' name="name"/>
            </td>
            <td align="left">
                <input class="input" type='text' placeholder='Description' name="description"/>
            </td>
            <td align="left">
                <input class="input" type='date' placeholder='Date start' name="dateStart"/>
            </td>
            <td align="left">
                <input class="input" type='date' placeholder='Date finish' name="dateFinish"/>
            </td>
            <td align="left">
                <input class="input" type='text' placeholder='Status' name="status"/>
            </td>
                <input type='hidden' name="id" value="${project.id}"/>
            <td class="toHover">
                <button class="save">
                    <i class="material-icons" style="font-size:48px;color:#45D09E" align = "center">save</i>
                </button>
            </td>
        </tr>
    </form>
    </tbody>
</table>
</body>
</html>
