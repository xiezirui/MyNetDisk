<%--
  Created by IntelliJ IDEA.
  User: XZR
  Date: 2022/5/8
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分享吗设置</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/file.do" method="get">
    密码:<input type="text" name="password">
    <input type="hidden" name="method" value="setSharePwd">
    <input type="hidden" name="address" value="${address}">
    <input type="hidden" name="uid" value="${uid}">
    <input type="hidden" name="name" value="${name}">
    <input type="submit" value="确定">
</form>

</body>
</html>
