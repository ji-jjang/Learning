<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/31/24
  Time: 9:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>form</title>
</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String passwd = request.getParameter("passwd");
        String name = request.getParameter("name");
        String phone1 = request.getParameter("phone1");
        String phone2 = request.getParameter("phone2");
        String phone3 = request.getParameter("phone3");
        String sex = request.getParameter("sex");
        String[] hobby = request.getParameterValues("hobby");
        String comment = request.getParameter("comment");
    %>

    <p> 아이디 : <%=id %></p>
    <p> 비밀번호 : <%=passwd %></p>
    <p> 이름 : <%=name %></p>
    <p> 연락처 : <%=phone1 %></p>
    <p> 성별: <%=sex %></p>
    <p> 취미: <%
        if (hobby != null && hobby.length > 0) {
          for (int i = 0; i < hobby.length; i++) {
            out.println(" " + hobby[i]);
          }
        }
    %>
    <p> 가입인사: <%=comment%></p>
</body>
</html>
