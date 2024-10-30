<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 9:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Scripting Tag</title>
</head>
<body>
    <%! int data = 50; %>
    <%
        out.println("Value of the variable is: " + data);
    %>
</body>
</html>
