<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/31/24
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>파일 업로드 결과</title>
</head>
<body>
<h2>파일 업로드 결과</h2>

<h3>폼 데이터:</h3>
<%
    Map<String, String> formFields = (Map<String, String>) request.getAttribute("formFields");
    for (Map.Entry<String, String> entry : formFields.entrySet()) {
        out.println(entry.getKey() + " = " + entry.getValue() + "<br>");
    }
%>

<h3>업로드된 파일 정보:</h3>
<%
    List<Map<String, Object>> uploadedFiles = (List<Map<String, Object>>) request.getAttribute("uploadedFiles");
    for (Map<String, Object> fileInfo : uploadedFiles) {
        out.println("요청 파라미터 이름 : " + fileInfo.get("fieldName") + "<br>");
        out.println("실제 파일 이름 : " + fileInfo.get("originalFileName") + "<br>");
        out.println("저장 파일 이름 : " + fileInfo.get("storedFileName") + "<br>");
        out.println("파일 콘텐츠 유형 : " + fileInfo.get("contentType") + "<br>");
        out.println("파일 크기 : " + fileInfo.get("size") + " bytes<br><br>");
    }
%>
</body>
</html>
