<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 5:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Action Tag</title>
</head>
<body>
    <jsp:useBean id="person" class="com.juny.jsppractice.chapter4.dto.Person" scope="request" />
    <p> 아이디: <jsp:getProperty name="person" property="id"/></p>
    <p> 이 름: <jsp:getProperty name="person" property="name"/></p>
</body>
</html>
