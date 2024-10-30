<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 6:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Implicit Objects</title>
</head>
<body>
    <%
        request.setCharacterEncoding("utf-8");
        String name=request.getParameter("name");
    %>
    <p>
        이름 : <%=name %><br>
        요청 정보 길이 : <%=request.getContentLength() %><br>
        클라이언트 전송 방식 : <%=request.getMethod() %><br>
        요청 URI : <%=request.getRequestURI() %><br>
        서버 이름 : <%=request.getServerName() %><br>
        서버 포트 : <%=request.getServerPort() %><br>
    </p>
</body>
</html>
