<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 11/3/24
  Time: 7:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Validation</title>
</head>
<script type="text/javascript">
  function checkForm() {
    alert("아이디 : " + document.loginForm.id.value +
            "비밀번호 : " + document.loginForm.passwd.value);
  }
</script>
<body>
<form name="loginForm">
  <p> 아이디 : <input type="text" name="id"> </p>
  <p> 비밀번호 : <input type="password" name="passwd"> </p>
  <p> <input type="submit" value="전송" onclick="checkForm()"> </p>
</form>

</body>
</html>
