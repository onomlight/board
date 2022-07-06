package com.korea.controller.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;

public class LogoutController implements SubController {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		 
		HttpSession session = req.getSession();
		session.invalidate();
		
		try {
			req.setAttribute("MSG", "로그아웃됨");
			req.getRequestDispatcher("/").forward(req, resp);
//			resp.sendRedirect("/");
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		
	}

}
