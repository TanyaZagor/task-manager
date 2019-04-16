<%@ page import="ru.zagorodnikova.tm.entity.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.zagorodnikova.tm.util.DateFormatterUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<head>
    <title>Task list</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<button class="signOut" onclick="location.href='/app'">Sign out</button>
<a class="create" href="task-create?projectId=<%=request.getAttribute("projectId")%>">
    Add task
    <i class="material-icons" style="font-size:48px;color:#17F1D7" align = "center">add</i>
</a>
<table class="container">
    <thead>
    <tr>
        <th><h1>Name</h1></th>
        <th><h1>Id</h1></th>
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
    <%
        List<Task> list = (List<Task>) request.getAttribute("tasks");
        for (Task task : list) { %>
    <tr>
        <td align="left"> <%= task.getName() %> </td>
        <td align="left"> <%= task.getId() %> </td>
        <td align="left"> <%= task.getDescription() %> </td>
        <td align="left"> <%= DateFormatterUtil.dateFormatter(task.getDateCreate())%> </td>
        <td align="left"> <%= DateFormatterUtil.dateFormatter(task.getDateStart()) %> </td>
        <td align="left"> <%= DateFormatterUtil.dateFormatter(task.getDateFinish()) %> </td>
        <td align="left"> <%= task.getStatus() %> </td>
        <td class="toHover">
            <a href="task-update?id=<%=task.getId()%>">
                <i class="material-icons" style="font-size:48px;color:#45D09E" align = "center">border_color</i>
            </a>
        </td>
        <td class="toHover">
            <a href="task-remove?id=<%=task.getId()%>">
                <i class="material-icons" style="font-size:48px;color:#E20338" align = "center">delete_sweep</i>
            </a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>
</html>
