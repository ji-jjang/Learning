<%@ page import="java.util.Enumeration" %><%--
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
    <table border="1">
        <tr>
            <th>요청 파라미터 이름</th>
            <th>요청 파리미터 값</th>
        </tr>
        <%
            request.setCharacterEncoding("UTF-8");

            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
              String name = (String) paramNames.nextElement();
              out.print("<tr><td>" + name + "</td>\n");
              String paramValue = request.getParameter(name);
              out.println("<td> " + paramValue + "</td></tr>\n");
            }
        %>
    </table>
</body>
</html>
