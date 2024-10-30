<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Action Tag</title>
</head>
<body>
    <jsp:useBean id="bean" class="com.juny.jsppractice.chapter4.dto.Calculator" />
    <%
        int m = bean.process(5);
        out.println(m);
    %>

</body>
</html>
