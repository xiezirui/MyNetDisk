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

<div class="header">
    <span id="logo"><img src="/static/img/logo/logo.svg" alt="logo"></span>
    <span style="position: fixed;left: 840px;margin-top: 4px"><h1>我的网盘</h1></span>
    <span class="select-area">
        <form action="${pageContext.request.contextPath}/user.do" method="get">
        <input type="hidden" name="method" value="showFiles">
        <input type="text" name="fileSelectName">
        <input type="submit" value="搜索">
    </form>
    </span>
    <span><a href="/user.do?method=logout">退出登录</a></span>
    <span><a href="/user.do?method=setting">用户设置</a></span>
</div>


<a class="file-upload" href="/jsp/uploadFile.jsp"><img src="/static/img/icons/upload/fileUpload-icon.svg" alt="fileUpload-icon"></a>
<a class="file-share" href="/file.do?method=showShareFile"><img src="/static/img/icons/share/fileShareState-icon.svg" alt="share"></a>
<a class="file-download" href="/jsp/downloadFile.jsp">文件下载</a>

<!--用户已上传文件列表-->
<table class="filelist" style="left: 108px">
    <tr>
        <th width="100px">文件状态</th>
        <th width="100px">文件名</th>
        <th width="100px">文件大小</th>
        <th width="100px">文件格式</th>
        <th width="100px">文件操作</th>
    </tr>
    <c:forEach var="file" items="${files}" varStatus="status">
        <tr style="text-align: center">
            <td width="100px">
                <span>
                    <c:if test="${file.fileShareState == 0}">
                        <img src="/static/img/icons/state/isShareState-N.svg" alt="No">
                    </c:if>
                    <c:if test="${file.fileShareState == 1}">
                        <img src="/static/img/icons/state/isShareState-Y.svg" alt="Yes">
                    </c:if>
                </span>
            </td>
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
                        <input type="hidden" value="download" name="method">
                        <input type="hidden" value="${file.fileAddress}" name="address">
                        <input type="hidden" value="${file.fileName}" name="name">
                        <input type="submit" value="下载">
                    </form>
                </span>
                <span><button id="deleteButton" value="${file.fileAddress},${file.fileName}" onclick="delfile()">删除</button></span>
                <span>
                    <form action="${pageContext.request.contextPath}/file.do">
                        <input type="hidden" name="method" value="fileInfo">
                        <input type="hidden" name="address" value="${file.fileAddress}"/>
                        <input type="submit" value="信息">
                    </form>
                </span>
            </td>
        </tr>
    </c:forEach>
</table>








</body>
</html>
