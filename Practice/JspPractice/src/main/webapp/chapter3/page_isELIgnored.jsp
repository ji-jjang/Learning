<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page isELIgnored="true" %>
<html>
<head>
    <title>Directive Tags</title>
</head>
<body>
    <%
        request.setAttribute("RequestAttribute", "request 내장 객체");
    %>
    ${requestScope.RequestAttribute}
</body>
</html>
