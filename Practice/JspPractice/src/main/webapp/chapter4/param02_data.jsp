<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Action Tag</title>
</head>
<body>
    <%
        String title = request.getParameter("title");
    %>
    <h3>
        <%=java.net.URLDecoder.decode(title)%>
    </h3>
    Today is :<%=request.getParameter("date") %>
</body>
</html>
