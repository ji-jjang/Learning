<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 9:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Scripting Tag</title>
</head>
<body>
  <h2>Scripting Tag</h2>
  <%! int count = 3;

  String makeItLower(String data) {
    return data.toLowerCase();
  } %>

  <%
    for (int i = 1; i <= count; ++i) {
      out.println("Java Server Pages " + i + ".<br>");
    }
  %>

  <%= makeItLower("Hello World") %>
</body>
</html>
