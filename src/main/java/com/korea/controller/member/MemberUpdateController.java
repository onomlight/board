package com.korea.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.MemberDTO;
import com.korea.service.MemberService;

public class MemberUpdateController implements SubController {
	MemberService service = MemberService.getInstance();
	//서비스등록을 위해 설정함 
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		 
		//	파라미터 받기
		//	입력값 확인
		//	서비스 실행
		//	View 이동
		String flag = req.getParameter("flag");
		
		try {
			
			if(flag==null) // myinfo.jsp 에서 전달함
				
			{
				
			req.getRequestDispatcher("/WEB-INF/member/password.jsp").forward(req, resp);
			
			}
			else //password.jsp에서 요청받음
			{
				
//				System.out.println("!!!");
				// 패스워드 검증 일치한다면 정보수정처리
				String pwd =req.getParameter("pwd"); // 전달받은 패스워드
				
				HttpSession session =req.getSession();
				String email =(String)session.getAttribute("email"); // 정보확인을 위한 이메일 꺼내기
				MemberDTO dto = service.MemberSearch(email); // 서비스 반환형이 DTO임 
				// DTO에서 꺼내온 db는 패스워드 이메일도 있음 
				
				// 패스워드 일치여부 확인
				if(service.passwordEncoder.checkpw(pwd, dto.getPwd()))
				{
					//패스워드가 일치한 경우
					//-> 수졍된 값 파라미터 받기
					String addr1 = req.getParameter("addr1");
					String addr2 = req.getParameter("addr2");
					
					System.out.println("Addr1 : " + addr1);
					System.out.println("Addr2 : " + addr2);
					
				
					
					dto.setAddr1(addr1);
					dto.setAddr2(addr2);
					
					String newpwd = req.getParameter("newpwd");
					newpwd=	service.passwordEncoder.hashpw(newpwd,service.passwordEncoder.gensalt());
					// 마이인포에서 수정할 패스워드를 입력받고 진행함 
					dto.setPwd(newpwd);
					
					//-> dto단위로 달아서 service 전달 
					service.MemberUpdate(dto);
					
					//view 이동
					req.setAttribute("dto", dto);
					req.getRequestDispatcher("/WEB-INF/member/myinfo.jsp").forward(req, resp);
					return ;
				}
				else
				{
					//패스워드가 불일치한 경우	
					req.setAttribute("MSG", "패스워드가 일치하지 않습니다");
					req.getRequestDispatcher("/WEB-INF/member/password.jsp").forward(req, resp);
					return ; 
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
