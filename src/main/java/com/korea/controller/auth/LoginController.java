package com.korea.controller.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.MemberDTO;
import com.korea.service.MemberService;

public class LoginController implements SubController{
	MemberService service = MemberService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
			
		 System.out.println("LoginController로 진입");
		 
		 //파라미터 받기
		 String email  = req.getParameter("email");
		 String pwd = req.getParameter("pwd");
		 try {
		 //입력값 검증
		 if(email ==null || pwd==null)
		 {
			 resp.sendRedirect("/index.do");
		 }
		 //서비스 실행
		 
		 //MemberService - > ID가 있는지 DB로 확인 -> DAO에서 정보확인 - > DB로 확인된다면 True/False로 확인
		 MemberDTO dto =	service.MemberSearch(email);
		 if(dto!=null)
		 {
			 //PW 일치여부 확인
//			 if(pwd.equals(dto.getPwd()))
			 if(service.passwordEncoder.checkpw(pwd, dto.getPwd())) // 암호화된 패스워드 일치여부확인
			 {
				 //패스워드 일치
				 
				 //세션에 특정 옵션 부여 (예를 들어 계정의 grade를 저장한다거나~)
				 HttpSession session = req.getSession();
				 session.setAttribute("grade",dto.getGrade()); // grade를 먼저 세션에 넣어야함 
				 session.setAttribute("email", dto.getEmail());
				 session.setMaxInactiveInterval(60*5);
				 
				 //View로 이동
				 resp.sendRedirect("/main.do");
				 
			 }
			 else
			 {
				 //패스워드 불일치
				 req.setAttribute("MSG", "패스워드가 일치하지 않습니다..");
				 req.getRequestDispatcher("/index.do").forward(req, resp);
			 }
		 }
		 else
		 {
			 //아이디 조회 실패.. 해당 아이디가 없습니다
			 req.setAttribute("MSG", "일치하는 아이디가 없슴");
			 req.getRequestDispatcher("/index.do").forward(req, resp);
		 }
		
		 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
