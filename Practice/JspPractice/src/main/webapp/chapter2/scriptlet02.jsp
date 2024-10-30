<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 9:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br>
<%
for (int i = 1; i <= 10; ++i) {
  if (i % 2 == 0)
    out.println(i + "<br>");
}
%>

</body>
</html>
