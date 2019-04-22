<%--
  Created by IntelliJ IDEA.
  User: NEW_YE
  Date: 2019/4/12
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<form action="updateByName" method="post">
    <ul>
        <li>修改</li>
        <li >用户名：${name}</li>
        <input type="hidden" name="name" value="${name}" id="name">
        <li>密码：<input type="password" name="pwd" id="pwd"></li>
        <li>性别:
            <input type="radio" name="sex" value="1" checked/><label>男</label>
            <input type="radio" name="sex" value="0"/><label>女</label>
        </li>
        <li>年龄：<input type="text" name="age" id="age"></li>
        <li>生日：<input type="date" name="birthdate" id="birthdate"></li>
        <li>
            <input type="submit">
            <input type="reset">
        </li>

    </ul>
</form>
</body>

</html>
