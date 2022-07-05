package com.korea.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.member.MemberJoinController;

public class FrontController extends HttpServlet{
	//URL : SubController객체
	HashMap <String,SubController> list = null;
	
	@Override
	public void init() throws ServletException {
		list = new HashMap();
		
		list.put("/MemberJoin.do", new MemberJoinController());
//		list.put("MemberSerach", null);
		// 서브컨트롤러에 내용이 함께 들어가게됨
		
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text.html; charset-UTF-8");
		
		String url = req.getRequestURI();// url 정보를 뽑아온다음에 미리 저장해 놓은 init안에 있는 정보들을 받아옴
		System.out.println("URL : " + url); 
		SubController sub=list.get(url); 
		if(sub!=null)
			sub.execute(req, resp);
	}
//		list.get(url).execute(req, resp); // 그 후에 내용을 익스큐트를 한 후 정볼르 받아서 하게됨
	

		
	
	

 

}

/* 1. Reqest 요청 ( 사용자 요청 )
 * 2. FontController's Service 함수 URL 확인
 * 3. URL에 해당하는 SubController객체주소 확인
 * 4. SubController의 execute 를 실행
 * 
 * */
