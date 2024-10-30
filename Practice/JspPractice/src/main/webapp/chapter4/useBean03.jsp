<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Action Tag</title>
</head>
<body>

    <jsp:useBean id="person" class="com.juny.jsppractice.chapter4.dto.Person" scope="request" />
    <p> 아이디 : <%=person.getId() %></p>
    <p> 이름 : <%=person.getName() %></p>
</body>
</html>
