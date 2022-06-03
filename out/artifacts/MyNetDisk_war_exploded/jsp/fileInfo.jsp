<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: XZR
  Date: 2022/5/15
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件信息</title>
</head>
<body>

<table class="fileInfoList" style="left: 108px">
    <tr>
        <th width="100px">文件分享码</th>
        <th width="100px">文件分享地址</th>
    </tr>
    <c:forEach var="file" items="${shareFiles}" varStatus="status">
            <td>
                <span>${file.filePassword}</span>
            </td>
            <td>
                <span>${file.fileShareAddress}</span>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
