<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@page import="java.io.*" %>
<%
	File dir = new File("c://upload"); // 업로드된파일경로를 먼저 연결시킴

	File [] files = dir.listFiles(); // 파일 이름들을 이제 다 가지고옴
	
	for(int i=0;i<files.length;i++) // 파일명만 빼내기위해 getname함수를 사용함
	{
		out.println("<a href=/download.do?filename="+files[i].getName().replaceAll(" ","+")+">"+files[i].getName()+"</a><br>");
	}
%>

</body>
</html>