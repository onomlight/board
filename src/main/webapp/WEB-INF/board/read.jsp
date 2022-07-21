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
	<%
		
		String MSG=(String)request.getAttribute("MSG");
		if(MSG!=null)
		{
			%>
			<script>
				alert('<%=MSG%>');
			</script>
			<%
			session.setAttribute("MSG",null);
		}
	
	
	%>
	
	<div class="container-md" id=wrapper style="margin:100px auto;">
		<!-- TopMenu -->
		<%@include file="/resources/includes/topmenu.jsp" %>
			
		<!-- NAV -->
		<%@include file="/resources/includes/nav.jsp" %>
		
		<!-- MainContents -->
		<div id=maincontents style="margin-top:15px;">
			<!-- PagePath -->
			<div>
				<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
					<ol class="breadcrumb">
					    <li class="breadcrumb-item"><a href="#">Home</a></li>
					    <li class="breadcrumb-item"><a href="#">Board</a></li>
					    <li class="breadcrumb-item active" aria-current="page">Read</li>
					</ol>
				</nav>
			</div>
			<h1>글내용</h1>                               
			<%@page import="com.korea.dto.*" %>
			<%
				BoardDTO dto = (BoardDTO)request.getAttribute("dto");
				String nowPage=(String)request.getAttribute("nowPage");
				
				String[] filelist=null;
				String[] filesize=null;
				if(dto.getFilename()!=null)
				{
					filelist = dto.getFilename().split(";");
					filesize = dto.getFilesize().split(";");
				}
				
				//시작번호 계산
				int np = Integer.parseInt(nowPage);
				int numPerPage=10;
				int start=(np*numPerPage)-numPerPage+1;
				
				//끝번호 계산 
				int end=np*numPerPage;
				 
				
			 
				
			%>
			<form action="" method="post" >
				<input id=title name="title" class="form-control mb-3 w-50" value="<%=dto.getTitle()%>">
				<input name="writer" class="form-control mb-3 w-50" value="<%=dto.getWriter()%>" disabled> 

				
				<textarea id=content name="content" class="form-control mb-3 w-50" style="height:500px;"><%=dto.getContent() %></textarea>
				
				
				<a  href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop2">글수정</a>
				<a href="/Board/list.do?nowPage=<%=nowPage%>&start=<%=start %>&end=<%=end %>"  class="btn btn-primary">리스트</a>
				<a href="#"  class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop3">글삭제</a>
				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
				 첨부파일 보기
				</button>
			</form>
			<!-- Modal -->
			<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="staticBackdropLabel">첨부파일 리스트</h5>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			         <!-- 첨부파일  -->
			         	<%@page import="java.net.URLEncoder" %>
			         	<%		         	
			         		if(filelist!=null)
			         		{
								for(int i=0;i<filelist.length;i++){	
									
									String tmpfilename = filelist[i].substring(0,filelist[i].lastIndexOf("_"));
									tmpfilename += filelist[i].substring(filelist[i].lastIndexOf("."),filelist[i].length());
									
									filelist[i] = URLEncoder.encode(filelist[i],"utf-8").replaceAll("\\+", "%20");							 
										out.println("<a href=/Board/download.do?filename="+filelist[i]+">"+tmpfilename+"("+filesize[i]+" byte)</a><br>");
									}
			         		}
			         		else
			         		{
			         			out.println("파일 없음");
			         		}
			         		
						%>
			         
			      </div>
			      <div class="modal-footer">	
			      	       
			        <a id="downall" class="btn btn-primary" href="#">모두받기(NoZIP)</a>
			        <a  class="btn btn-primary" href="/Board/downloadAll.do">모두받기(ZIP)</a>
			     
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			      </div>
			      
			      
			      <!-- 다중파일 무압축 받기 -->
			      <form name=multiform>
			      		<%
			      			for(int  i=0;i<filelist.length;i++)
			      			{
			      				%>
			      				<input type=hidden name=file value=<%=filelist[i] %>>
			      				<%
			      			}
			      		%>
			      </form>
			      
			      <script>
			      $(document).ready(function(){
			            
			    	  	form = document.multiform;
			            var iFrameCnt = 0; //프레임 개수확인 ,프레임 이름 지정
			               
			               $('#downall').click(function(){ //다운로드 이미지 실행
			                   
			                  	for(i=0;i<form.childElementCount;i++)
			                	{
			                       fileName =form[i].value;
			                       var url = "/Board/download.do?filename="+fileName;                           
			                       fnCreateIframe(iFrameCnt); // 보이지 않는 iframe 생성, name는 숫자로  
			                       $("iframe[name=" + iFrameCnt + "]").attr("src", url);     
			                       iFrameCnt++;      
			                       fnSleep(1000); 
			                	}
			                  
			                   
			               });
							fnCreateIframe = function (name){
			                   
			                   var frm = $('<iframe name="' + name + '" style="display: none;"></iframe>');
			                   frm.appendTo("body");
			 
			               } 
			              fnSleep = function (delay){
			                   
			                   var start = new Date().getTime();
			                   while (start + delay > new Date().getTime());
			 
			               };
			               
			               
			               
			        });
			      </script>
			      
			      
			    </div>
			  </div>
			</div>
		</div>
		<!-- Footer -->
			<!-- 댓글남기기 -->
		<<div id=replycontainer class="w-50" style="position:absolute;left:53%;top:37%;">
			<form>
				<div>
					<textarea id=comment class="form-control mb-3" style="height:200px;width:580px;" placeholder="댓글을입력해주세요"></textarea>
					<input type=hidden name=nowPage value=<%=nowPage %> >	
				</div>
				<div class="mb-3">
					<a href="javascript:postreply" class="btn btn-secondary">댓글 남기기</a>
				</div>
			</form>
			<div class="mb-2">
				댓글수 : <span>100</span>
			</div>
			<div style="overflow:auto;height:300px;width:580px;" id="replylist">
				<!-- 여기에 댓글리스트가 출력되어야함 -->
				<div class="form-control">
					<span style="font-size: 0.5rem">example@example.com</span>
					<span style="font-size: 0.5rem">2022-07-21</span><br>
					<span>글내용@!@!@#@!@#!@#@$#!@$!@#$@!#$#@!$@</span>
				</div>
				
			</div>
		</div> 
		<!-- 댓글남기기 끝 -->	
		<script>
		function postreply() {
			// 댓글등록
		}
		
		function listreply(){
			// 댓글 목록 가져오기
		}
		function totalreplycnt() {
			// 댓글 수 
		}
		</script>
	</div>
























	<!-- 글수정 Modal -->		
		<!-- Modal -->
		<div class="modal fade" id="staticBackdrop2" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">패스워드 확인</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      
		      <form action="/Board/update.do" method="post" name="updatefrm">
			      <div class="modal-body">
			        <input type=password class="form-control"  name=pwd placeholder="INSERT PASSWORD">
			        <input type=hidden name=title>
			        <input type=hidden name=content>
			        <input type=hidden name=nowPage value=<%=nowPage %> >
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-primary" id=updatebtn>수정요청</button>
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			      </div>
		      </form>
			  
			  <script>
			  	$(document).ready(function(){
			  		
			  		$("#updatebtn").on("click",function(){
			  			
			  			form=document.updatefrm;
			  			form[1].value = $("#title").val();
			  			form[2].value =  $("#content").val();
			  			form.submit();
			  		})
			  		
			  	})
			  
			  </script>
		      
		    </div>
		  </div>
		</div>


		<!-- 글삭제 Modal -->
		<!-- Modal -->
		<div class="modal fade" id="staticBackdrop3" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">패스워드 확인</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      
		      <form action="/Board/delete.do" method="post" name="deletefrm">
			      <div class="modal-body">
			        <input type=password class="form-control"  name=pwd placeholder="INSERT PASSWORD">
			        <input type=hidden name=nowPage value=<%=nowPage %>>
			      </div>
			      <div class="modal-footer">
			        <button type="submit" class="btn btn-primary" id=deletebtn>삭제요청</button>
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			      </div>
		      </form>

</body>
</html>



