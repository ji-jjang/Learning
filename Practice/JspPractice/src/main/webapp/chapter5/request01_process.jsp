<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 6:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Implicit Objects</title>
</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");
        String userId=request.getParameter("id");
        String password=request.getParameter("passwd");
    %>
    <p> 아이디: <%=userId %></p>
    <p> 비밀번호: <%=password %></p>
</body>
</html>
