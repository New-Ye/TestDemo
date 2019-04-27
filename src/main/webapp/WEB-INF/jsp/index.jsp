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
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <title>login</title>
</head>
<body>

<h2>${name}</h2>
<h2>${msg}</h2>
<h2>${fileUrl}</h2>
<h2>${downFileMsg}</h2>

<a href="/downFile?fileName=c06343a7-e83b-41d9-b730-a3e3655644fc中文.txt">下载</a>
<form action="upFile"
      method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" value="上传" />
</form>


<table>
    <tr>
        <th>id</th>
        <th id="">姓名</th>
        <th>密码</th>
        <th>性别</th>
        <th>年龄</th>
        <th>生日</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${selectAll}" var="t">
        <tr>
            <td>
                    ${t.id}
            </td>
            <td name="name">${t.name}</td>
            <td>
                    ${t.pwd}

            </td>
            <td>
                    ${t.sex}

            </td>
            <td>
                    ${t.age}

            </td>
            <td>
                    ${t.birthdate}

            </td>
            <td>

                <a name="del">
                    <button >删除</button>
                </a>
                <a name="update">
                    <button>更改</button>
                </a>

            </td>
            <td></td>
        </tr>
    </c:forEach>
</table>
<a type="button" href="/Logout">注销</a>

<script>
    //改jQuery
    var dels=document.getElementsByName("del");
    for (var i=0;i<dels.length;i++) {
        dels[i].onclick = function (ev) {
            var a=ev.srcElement.parentElement.parentElement.parentElement.firstElementChild.nextElementSibling
            var s="/deleteByName?name="+a.innerHTML;
            //alert(s);
            window.location.href=s;
        }
    }
</script>

<script>
    //改jQuery
    var updates=document.getElementsByName("update");
    for (var i=0;i<dels.length;i++) {
        updates[i].onclick = function (ev) {
            var a=ev.srcElement.parentElement.parentElement.parentElement.firstElementChild.nextElementSibling;
            var s="/toUpdate?name="+a.innerHTML;
           // alert(s);
            window.location.href=s;
        }
    }
</script>
</body>

</html>
