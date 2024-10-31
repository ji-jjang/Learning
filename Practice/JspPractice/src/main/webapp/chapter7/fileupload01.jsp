


<%--

    implementation 'commons-fileupload:commons-fileupload:1.5'
    implementation 'commons-io:commons-io:2.17.0'
    의존성 추가해서 파일 업로드, 라이브러리는 javax.servlet을 사용하지만 현재 내가 사용하는 서블릿은 jakarta.servlet으로 호환성 문제 발생.
    따라서 JSP에서 파일 업로드를 처리하
    파일 업로드하는 부분은 서블릿을 이용해서 처리.

  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/31/24
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Upload</title>
</head>
<body>
    <form name="fileForm" method="post" enctype="multipart/form-data"
          action="${pageContext.request.contextPath}/fileupload">
        <p> 이 름 : <input type="text" name="name"></p>
        <p> 제 목 : <input type="text" name="subject"></p>
        <p> 파 일 : <input type="file" name="filename" multiple></p>
        <p> <input type="submit" value="파일 올리기"> </p>
    </form>
</body>
</html>
