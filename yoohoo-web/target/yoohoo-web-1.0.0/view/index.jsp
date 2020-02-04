<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String UA = request.getHeader("user-agent");
    System.out.println(UA);
    UA = UA.toUpperCase();
    if (UA.contains("IPHONE") ||
            UA.contains("ANDROID")) {
        /*上线部署*/
       response.sendRedirect("http://www.yoohooabc.com/mobileRegister/register.html");
        /*线下测试*/
        // response.sendRedirect("http://localhost:8081/mobileRegister/register.html");
    } else {
     // response.sendRedirect("http://www.yoohooabc.com/student/views/study");
       response.sendRedirect("http://localhost:8083/student/views/study");
    }
%>