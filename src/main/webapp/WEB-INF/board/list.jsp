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
		
		<!-- PagePath -->
		<div id=maincontents style=margin-top:15px;">
			<div>
				<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
					<ol class="breadcrumb">
					    <li class="breadcrumb-item"><a href="#">Home</a></li>
					    <li class="breadcrumb-item active" aria-current="page">Library</li>
					</ol>
				</nav>
			</div>
			
			<h1>자유 게시판</h1>
	 		<%
				int totalcount=0;	//전체 게시물 수
				int numPerPage=10;	// 페이지당 표시할 게시물 수
				
				int totalPage=0;	//전체 페이지 수
				int nowPage=1;		//현재 페이지 번호
				
				int start=1;		//게시물 읽을때 사용되는 시작값
				int end =10;		//게시물 읽을때 사용되는 끝값
				
				int pagePerBlock=15;	//블럭당 표시할 페이지 수
				int totalBlock=0; 		//전체 페이징 블럭 수
				int nowBlock=1;			//현재 페이징 블럭 수 
			%>
			
			
			<%
			//상단 의 현재 페이지 번호 변경 위한 처리
					if(request.getAttribute("nowPage")!=null)
						
						nowPage=
						Integer.parseInt(
								(String)request.getAttribute("nowPage")
								);
			%>
			
			
			<%
				totalcount = (int)request.getAttribute("tcnt"); 	// 전체 게시물 수 받기
				totalPage = (int)Math.ceil(totalcount/numPerPage); 	// 전체 페이지 수 계산
				
				//전체 블럭수 계산
				totalBlock = (int)Math.ceil((double)totalPage / pagePerBlock);
				//현제 블럭숫자 계산
				nowBlock = (int)Math.ceil((double)nowPage / pagePerBlock); // 이동하기위해서 현재 블록위치찾는부분
				
				// 나눳을때 소수점이 생기는 경우가 발생되는 경우가 생김 / 그래서 더블러 처리 / 올림처리함
			%> 
			
			<script>
				/* 페이징 처리 함수 - 페이지 번호를 받아 해당 페이지를 표시  인자를 받은 페이징을 폼에다 넣거 서브밋할것*/
				function paging(pageNum)
				{
					form=document.readFrm;
					form.nowPage.value=pageNum;
					var numPerPage=<%=numPerPage%>
					form.start.value=(pageNum*numPerPage)-numPerPage+1; // 시작값은 11임
					form.end.value=(pageNum*numPerPage);
					form.action="/Board/list.do";
					form.submit();
					
					
				}
				
				/* 블럭처리 함수 - 이전 / 이후 버튼 누를때 이전블럭/다음블럭으로 이동 */
				function block(value)// value(현재블럭 숫자 -1 or +1 값) 
				{ 
					form=document.readFrm;
					// 이전 이후로 이동처리될때 처음 표시될 페이지번호
					StartPage = <%=pagePerBlock%>*(value-1)+1; // pagePerBlock 기본적으로 15로 설정했음
					numPerPage=<%=numPerPage%>
					
					form.nowPage.value=StartPage;
					
					form.start.value=(StartPage*numPerPage)-numPerPage+1;
					form.end.value=(StartPage*numPerPage);
					form.action="/Board/list.do";
					form.submit();
				}
				
				/* 처음으로 이동 처리  */
				function init() {
					form = document.initFrm;
					form.nowPage.value=1;
					form.action="/Board/list.do";
					form.submit();
				}
				
				/* 게이물 읽기 */
				function read(no) //no : 게시물 번호
				{
					form = document.readFrm;
					form.no.value=no;
					form.nowPage.value=<%=nowPage%>;
					form.action="/Board/read.do";
					form.submit();
					
				}
			</script>
				<form name=initFrm method="get">
					<input type="hidden" name="nowPage">			
					</form>
			<!-- 페이징 처리 폼  -->
			<form name="readFrm" method="get">
					<input type="hidden" name="no">		<!-- 게시물 번호 -->
				<input type="hidden" name="start"> 		<!-- DB로부터 읽을 시작 번호 -->
				<input type="hidden" name="end">		<!-- DB로부터 읽을 끝 번호 -->
				<input type="hidden" name="nowPage">	<!-- 현재 페이지 번호 -->

			
			</form>
			
			
			<!-- 현재페이지/전체페이지 -->
			<table class="table">
				<tr >
					<td style=border:0px;> <span style=color:orange;><%=nowPage %></span> / <%=totalPage %> Page</td>
					<td style=border:0px;text-align:right;>
						<button class="btn btn-secondary" onclick="init()">처음으로</button>
						<a class="btn btn-secondary" href="/Board/post.do">글쓰기</a>
					</td>
				</tr>
			</table>
			
			<table class="table">
				<tr>
					<th> NO </th>
					<th> TITLE </th>
					<th> WRITER</th>
					<th> Date</th>
					<th> COUNT</th>
				</tr>
					<%@page import ="java.util.*,com.korea.dto.*" %>
			
				<%
					ArrayList<BoardDTO>list = (ArrayList<BoardDTO>)request.getAttribute("list");
					/* System.out.println("게시물 건수 : " + list); */
					for(int i=0;i<list.size();i++)
					{
				%>
				<tr>
					<td><%=list.get(i).getNo() %></td>
					<td><a href="javascript:read('<%=list.get(i).getNo()%>')"><%=list.get(i).getTitle() %></a></td>
					<td><%=list.get(i).getWriter() %></td>
					<td><%=list.get(i).getRegdate() %></td>
					<td><%=list.get(i).getCount() %></td>
				</tr>
				<%
					}
				%>
				<tr>
					<!-- 페이지네이션 -->
					<td colspan=5  style=border-bottom:0px;>
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center">
						  
						  <!-- 이전으로 -->
						  <%
							  if(nowBlock>1)
							  {
								  
							  
						  %>
						    <li class="page-item">
						      <a class="page-link" href="javascript:block('<%=nowBlock-1 %>')" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						    <%
							  }
						    %>
						    <!-- 나우블럭이 1이하일때 이전으로 표시는 하지 않겟다  -->
						     <%
						    	int pageStart=(nowBlock-1)*pagePerBlock+1;
						    	int pageEnd=((pageStart+(pagePerBlock-1))<totalPage)?(pageStart+(pagePerBlock-1)):totalPage;
						     %>
						    <!-- 페이지 번호  -->
						    <%
						    	for(;pageStart<=pageEnd;pageStart++)
						    	{
						    %>
						  		 <li class="page-item"><a class="page-link" href="javascript:paging('<%=pageStart%>')"><%=pageStart %></a></li>
						    <%
						    	}
						    %>
						    
						    <!-- 다음으로 -->
						    <%
						    if(totalBlock > nowBlock)
						    {
						    %>
						    <li class="page-item">
						      <a class="page-link" href="javascript:block('<%=nowBlock+1 %>')" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
						    <%
						    }
						    %>
						    
						   
						  </ul>
						</nav>
					</td>
					
				</tr>
			</table>
			<a href="/Board/post.do">글쓰기</a>
			
		</div>
		
		<!-- Footer -->
		
	
	</div>


</body>
</html>