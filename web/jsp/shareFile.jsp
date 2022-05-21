<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: XZR
  Date: 2022/5/4
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件共享</title>

    <link type="text/css" rel="stylesheet" href="/css/shareFile.css">
    <link type="text/css" rel="stylesheet" href="/css/public.css">
</head>
<body>
<div class="returnIcon"><a href="/user.do?method=showFiles">返回</a></div>


<table class="sharefilelist" style="left: 8px">
    <tr>
        <th width="100px">文件名</th>
        <th width="100px">文件大小</th>
        <th width="100px">文件格式</th>
        <th width="100px">文件操作</th>
    </tr>
    <c:forEach var="file" items="${files}" varStatus="status">
        <tr style="text-align: center">
            <td width="100px">
                <span>${file.fileName}</span>
            </td>
            <td width="100px">
                <span>${file.fileSize}</span>
            </td>
            <td width="100px">
                <span>${file.fileFormat}</span>
            </td>
            <td width="100px">
                <span>
                    <form action="${pageContext.request.contextPath}/file.do" method="get">
                        <input type="hidden" value="gotoSetShareFilePwd" name="method">
                        <input type="hidden" value="${file.fileAddress}" name="address">
                        <input type="hidden" value="${file.id}" name="uid">
                        <input type="hidden" value="${file.fileName}" name="name">
                        <input type="submit" value="共享">
                    </form>
                </span>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
