
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/23
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>雅英</title>
</head>

<body>
<%
    String name = request.getParameter("valus");
    String date = request.getParameter("way");
%>
<%--您的姓名是：<%=name%>
您的姓名是：<%=date%>--%>
<c:if test="${ sessionScope.way=='2'}">
    <iframe src="https://view.officeapps.live.com/op/view.aspx?src=file.yoohooabc.com/<%=name%>" width='100%' height='900' frameborder='1'>
    </iframe>
</c:if>
<c:if test="${sessionScope.way =='1'}">
    <iframe src="//file.yoohooabc.com/<%=name%>" width="100%" height="900"></iframe>
</c:if>


</body>

</html>