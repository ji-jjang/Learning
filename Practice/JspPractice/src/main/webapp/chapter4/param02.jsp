<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Action Tag</title>
</head>
<body>
    <h3>param 액션 태그</h3>
    <jsp:include page="param02_data.jsp">
        <jsp:param name="title" value='<%=java.net.URLEncoder.encode("오늘의 날짜와 시각")%>' />
        <jsp:param name="date" value="<%=java.util.Calendar.getInstance().getTime() %>" />
    </jsp:include>

</body>
</html>
