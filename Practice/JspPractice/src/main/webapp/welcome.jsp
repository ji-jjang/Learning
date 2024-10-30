<%--
  Created by IntelliJ IDEA.
  User: jijunhyuk
  Date: 10/29/24
  Time: 9:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Welcome</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<div class="container py-4">
    <%@include file="menu.jsp"%>
    </header>

    <%!String greeting = "도서 쇼핑몰에 오신 것을 환영합니다!";
        String tagline = "Welcome to Web Market!";%>

    <%--중간 타이틀 영역--%>
    <div class="p-5 mb-4 bg-body-tertiary rounded-3">
        <div class="container-fluid py-5">
            <h1 class="display-5 fw-bold"><%=greeting%></h1>
            <p class="col-md-8 fs-4">BookMarket</p>
        </div>
    </div>

    <%--본문 영역--%>
    <div class="row align-items-md-stretch   text-center">
        <div class="col-md-12">
            <div class="h-100 p-5">
                <h3><%=tagline%></h3>
                <%
                    Date day=new java.util.Date();
                    String am_pm;
                    int hour = day.getHours();
                    int minutes = day.getMinutes();
                    int second = day.getSeconds();
                    if (hour / 12 == 0) {
                      am_pm = "AM";
                    }
                    else {
                      am_pm = "PM";
                      hour = hour - 12;
                    }
                    String CT = hour + ":" + minutes + ":" + second + " " + am_pm;
                    out.println("현재 접속 시간: " + CT + "\n");
                %>
            </div>
        </div>
    </div>

    <%--바닥글 영역--%>
    <%@include file="footer.jsp"%>
</div>
</body>
</html>