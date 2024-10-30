<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 9:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>counting</title>
</head>

<%! int count = 0; %>

<body>
  Page Count is
  <%
    out.println(++count);
  %>
</body>
</html>
