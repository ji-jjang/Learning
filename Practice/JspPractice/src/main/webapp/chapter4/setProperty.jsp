<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 3:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Action Tag</title>
</head>
<body>
  <jsp:useBean id="person" class="com.juny.jsppractice.chapter4.dto.Person" scope="request" />
  <jsp:setProperty name="person" property="id" value="20230824" />
  <jsp:setProperty name="person" property="name" value="홍홍이" />
  <p> 아이디 : <% out.println(person.getId()); %></p>
  <p> 이 름 : <% out.println(person.getName()); %></p>
</body>
</html>
