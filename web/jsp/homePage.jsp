<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: XZR
  Date: 2022/4/8
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="/css/homepage.css">
<link type="text/css" rel="stylesheet" href="/css/public.css">

<script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="/js/homePage.js"></script>

<html>
<head>
    <title>我的网盘</title>
</head>
<body>

<span id="logo"><img src="/static/img/logo/logo.svg" alt="logo"></span>
<span style="position: fixed;left: 840px;margin-top: 4px"><h1>我的网盘</h1></span>
<span class="select-area">
    <form action="${pageContext.request.contextPath}/user.do" method="get">
        <input type="hidden" name="method" value="showFiles">
        <input type="text" name="fileSelectName">
        <input type="submit" value="搜索">
    </form>
</span>

<a class="file-upload" href="/jsp/uploadFile.jsp"><img src="/static/img/icons/fileUpload-icon.svg" alt="fileUpload-icon"></a>

<div>
    <table class="filelist" style="left: 108px">
        <tr>
            <th width="250px">文件名</th>
            <th width="150px">文件大小</th>
            <th width="100px">文件格式</th>
        </tr>
        <c:forEach var="file" items="${files}" varStatus="status">
            <tr style="text-align: center">

                <td width="250px">
                    <span><button id="fileDownload" value="${file.fileAddress},${file.fileName}" onclick="but()">${file.fileName}</button></span>
                </td>
                <td width="150px">
                    <span>${file.fileSize}</span>
                </td>
                <td width="100px">
                    <span>${file.fileFormat}</span>
                </td>
                <td width="100px">
                    <span><button id="deleteButton" value="${file.fileAddress},${file.fileName}" onclick="delfile()">删除</button></span>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>







</body>
</html>
