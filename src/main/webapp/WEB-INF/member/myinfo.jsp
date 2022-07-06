<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@include file="/resources/includes/link.jsp" %>
<link rel="stylesheet" href="resources/css/common.css" >

</head>
<body>

	<div class="container-md" id=wrapper style="width:80%;margin:100px auto;">
		<!-- TopMenu -->
		<%@include file="/resources/includes/topmenu.jsp" %>
			
		<!-- NAV -->
		<%@include file="/resources/includes/nav.jsp" %>
		
		<!-- MainContents -->
		<div id=maincontents style="border:1px solid gray;margin-top:15px;">
			
			<table class="table w-75 table-striped" style="margin:100px auto;">
				<caption>회원 정보</caption>
				<tr>
					<td>Email</td>
					<td>test@test.com</td>
				</tr>
				<tr>
					<td>Addr1</td>
					<td>대구대구</td>
				</tr>
				<tr>
					<td>Addr2</td>
					<td>대구대구대구</td>
				</tr>
				<tr>
					<td>
						<button class="btn btn-primary w-50">정보수정</button>
					</td>
					<td>
						<button class="btn btn-secondary w-50">메인이동</button>
					</td>
				</tr>
			</table>
			
			
		</div>
		
		<!-- Footer -->
		
	
	</div>


</body>
</html>