<%--
  Created by IntelliJ IDEA.
  User: NEW_YE
  Date: 2019/4/7
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
<form action="register" method="post">
    <ul>
        <li>注册${msg}</li>
        <li>用户名：<input type="text" name="name" id="name"></li>
        <li>密码：<input type="password" name="pwd" id="pwd"></li>
        <li>性别:
            <%--<select>--%>
            <%--<option value="1" name="sex" id="1">男</option>--%>
            <%--<option value="0" name="sex" id="2">女</option>--%>
            <%--</select>--%>
            <input type="radio" name="sex" value="1"/><label>男</label>
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
