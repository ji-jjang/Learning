<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 1:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Action Tag</title>
</head>
<body>
    <h3>param 액션 태그</h3>
    <jsp:forward page="param01_data.jsp">
        <jsp:param name="id" value="admin"></jsp:param>
        <jsp:param name="name" value='<%=java.net.URLEncoder.encode("관리자") %>'></jsp:param>
    </jsp:forward>
<p>jakarta server page</p>
</body>
</html>
