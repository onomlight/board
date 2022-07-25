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

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function test()
	{
		
		addr1 = document.getElementById("addr1");
		zipcode = document.getElementById("zipcode");
		
	    new daum.Postcode({
	        oncomplete: function(data) {
	        	
	        	if(data.userSelectedType==='R') //도로명 주소인경우
	        	{
	        		//alert("도로명 주소 : " + data.roadAddress);
	        		addr1.value=data.roadAddress;
	        	}
	        	else
	        	{
	        		//alert("지번 주소 : " + data.jibunAddress);
	        		addr1.value=data.jibunAddress;
	        	}
	        	zipcode.value=data.zonecode;
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	        }
	    }).open();
	}
    
</script>


	<div class="container-md" style="width:45%;margin:200px auto;text-align:center;">
	<h1 class="mb-4">회원가입</h1>
		<form action="/MemberJoin.do" method="post">
			<input type=email name=email placeholder="example@example.com" class="form-control m-2">
			<input type=password name=pwd placeholder="Enter Password"  class="form-control m-2">
			<div class="row">
				<div class="col-md-3 ms-2">
					<input type=text name=zipcode id=zipcode placeholder="zipcode" class="form-control">
				</div>
				<div class="col-md-4">
					<a href="javascript:test()" class="btn btn-primary">우편번호 검색</a>
				</div>
			</div>
			
			<input name=addr1  id=addr1 placeholder="Enter Address1"  class="form-control m-2">
			<input name=addr2 placeholder="Enter Address2"  class="form-control m-2">
			<input type=submit value=가입하기 class="btn btn-primary w-100 m-2">
			<input type=reset value=RESET class="btn btn-primary w-100 m-2">
			<a href="/"  class="btn btn-primary w-100 m-2">이전으로</a>
		 	<input type=hidden name=flag value=true >
		</form>
	</div>

</body>
</html>