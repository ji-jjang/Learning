<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 1:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Action Tag</title>
</head>
<body>
    <p> 아이디: <%=request.getParameter("id")%></p>
        <%
            String name = request.getParameter("name");
        %>
    <p> 이 름: <%=java.net.URLDecoder.decode(name)%></p>
</body>
</html>
