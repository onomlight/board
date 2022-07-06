package com.korea.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.SubController;
import com.korea.service.MemberService;

public class MemberInfoController implements SubController{

	MemberService service = MemberService.getInstance();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		
		//view로이동
		try {
			req.getRequestDispatcher("/WEB-INF/member/myinfo.jsp")
			.forward(req, resp);
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
		
	}

}
