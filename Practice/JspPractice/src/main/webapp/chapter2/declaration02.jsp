<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Scripting Tag</title>
</head>
<body>
<%! int sum(int a, int b) {
  return a + b;
} %>
  <%
    out.println("2 + 3 = " + sum(2, 3));
  %>
</body>
</html>
