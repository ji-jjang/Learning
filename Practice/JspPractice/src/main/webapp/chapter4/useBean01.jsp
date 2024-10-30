<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Action Tag</title>
</head>
<body>
  <jsp:useBean id="date" class="java.util.Date" />
  <p>
    <%
      out.print("오늘의 날짜 및 시각");
    %>
  </p>
</body>
</html>
