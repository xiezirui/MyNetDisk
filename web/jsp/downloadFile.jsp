<%--
  Created by IntelliJ IDEA.
  User: XZR
  Date: 2022/4/14
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/file.do" method="get">
    <input type="hidden" name="method" value="getShare">
    地址:<input type="text" name="address">
    密码:<input type="text" name="password">
    <input type="submit" value="提交">
</form>

</body>
</html>
