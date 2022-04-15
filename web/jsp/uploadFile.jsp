<%--
  Created by IntelliJ IDEA.
  User: XZR
  Date: 2022/4/9
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>

${MESSAGE}

<form action="${pageContext.request.contextPath}/file.do" method="post" enctype="multipart/form-data">
    <input type="hidden" name="method" value="upload">
    <div>此处上传:<input type="file" name="uploadFile"></div>
    <div><input type="submit" value="上传"></div>
</form>


</body>
</html>
