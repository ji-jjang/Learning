<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/30/24
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    int pageCount = 0;
    void addCount() {
      ++pageCount;
    }
%>
<%
    addCount();
%>
<p>이 사이트 방문은 <%=pageCount%> 번째 입니다.</p>
