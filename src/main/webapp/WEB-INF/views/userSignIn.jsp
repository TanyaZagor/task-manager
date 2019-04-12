<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/WEB-INF/css/signup-style.css"%></style>
<head>
    <title>Sign up</title>
    <link href="../css/signup-style.css" rel="stylesheet" type="text/css">
</head>
<body
<div id='container'>
    <div class='signup'>
        <form action="user-signIn" method="POST">
            <input type='text' placeholder='Login:' name="login"/>
            <input type='text' placeholder='Password:' name="password"/>
            <input type='submit' value='Sign in' />
        </form>
    </div>
</div>
</body>
</html>
