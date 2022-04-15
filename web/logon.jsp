<%--
  Created by IntelliJ IDEA.
  User: XZR
  Date: 2022/4/4
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
    <div>
        <form action="${pageContext.request.contextPath}/user.do" method="get">
            <input type="hidden" name="method" value="addUser">
            <div>用户名:<input type="text" name="username"></div>
            <div>密码:<input type="text" name="password"></div>
            <div>邮箱:<input type="text" name="email"></div>
            <div><input type="submit" value="保存"></div>
        </form>
    </div>
</body>
</html>
