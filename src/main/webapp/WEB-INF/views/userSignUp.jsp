<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/WEB-INF/css/signup-style.css"%></style>
<head>
    <title>Sign up</title>
    <link href="../css/signup-style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id='container'>
    <div class='signup'>
        <form action="user-signUp" method="POST">
            <input type='text' placeholder='Login:' name="login"/>
            <input type='text' placeholder='Password:' name="password"/>
            <input type='text' placeholder='First:' name="firstName"/>
            <input type='text' placeholder='Last:' name="lastName"/>
            <input type='text' placeholder='Email:' name="email"/>
            <input type='submit' value='Sign up' />
        </form>
    </div>
</div>
</body>
</html>
