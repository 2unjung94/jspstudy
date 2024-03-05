<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
  request.setCharacterEncoding("UTF-8");
  String title = request.getParameter("title");
%>
<title><%=title%></title>

<!-- custom css  개발할때는 ?dt=<%=System.currentTimeMillis()%>추가해서 캐싱되지 않도록 할 것 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/body.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/footer.css">

</head>
<body>

  <div calss="header-wrap">
    <div>
      <a href="<%=request.getContextPath()%>/pkg03_include/main1.jsp">main1</a>
      <a href="<%=request.getContextPath()%>/pkg03_include/main2.jsp">main2</a>
    </div>
  </div>
  
  <div class="body-wrap">