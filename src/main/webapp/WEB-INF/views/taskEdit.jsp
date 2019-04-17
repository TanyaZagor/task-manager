
<%@ page import="ru.zagorodnikova.tm.util.DateFormatterUtil" %>
<%@ page import="ru.zagorodnikova.tm.entity.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/WEB-INF/css/edit-style.css"%></style>
<head>
    <title>Edit task</title>
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
        <td align="left"> ${task.name} </td>
        <td align="left"> ${task.description} </td>
        <td align="left"> ${task.dateStart}</td>
        <td align="left"> ${task.dateFinish}</td>
        <td align="left"> ${task.status}</td>
    </tr>
    <form action="task-update" method="POST">
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
                <select name="status">
                    <option>done</option>
                    <option>in progress</option>
                    <option>scheduled</option>
                </select>
            </td>
            <input type='hidden' name="id" value="${task.id}"/>
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
