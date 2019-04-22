<%--
  Created by IntelliJ IDEA.
  User: NEW_YE
  Date: 2019/4/7
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>

<form action="login" method="get">
    <ul>
        <li>账号：<input type="text" name="name" id="name"></li>
    </ul>
    <ul>
        <li>密码：<input type="password" name="pwd" id="pwd"></li>
    </ul>
    <ul>
        <li>
            <input type="submit">
            <input type="reset">

        </li>
    </ul>
</form>
<p>${msg}</p>
<button><a href="/toRegister">注册</a></button>

</body>
</html>
