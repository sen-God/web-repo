<%@ page import="com.example.advertising.model.Advertisement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Advertisement Display</title>
</head>
<body>
<%
  Advertisement ad = (Advertisement) request.getAttribute("selectedAd");
  if (ad!= null) {
%>
<h2><%= ad.getTitle() %></h2>
<img src="<%= ad.getImageUrl() %>" alt="Advertisement Image" width="300" height="200"> <!-- 展示广告图片 -->
<p><%= ad.getContent() %></p>
<%
} else {
%>
<p>No suitable advertisement found.</p>
<%
  }
%>
</body>
</html>