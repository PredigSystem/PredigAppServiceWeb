<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%

	beanData d =  (beanData) session.getAttribute("bean.beanData");
%>

<h1>This id the jsp File</h1>
<br>

Your name is: <%=d.getName()%><br>
Your code is: <%=d.getCode()%>

</body>
</html> 