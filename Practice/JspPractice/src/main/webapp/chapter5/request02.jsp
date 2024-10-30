<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 6:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page import="java.util.Enumeration" %>
<html>
<head>
    <title>Implicit Objects</title>
</head>
<body>
  <%
    Enumeration en = request.getHeaderNames();
    while (en.hasMoreElements()) {
      String headerName = (String) en.nextElement();
      String headerValue = request.getHeader(headerName);
  %>
  <%=headerName %> : <%=headerValue %><br>
  <%
    }
  %>
</body>
</html>
