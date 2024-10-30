<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 9:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
  <%!
    String makeItLower(String data) {
      return data.toLowerCase();
    }
  %>
  <%
    out.println(makeItLower("JUNhhhDAYDKSI!"));
  %>
</body>
</html>
