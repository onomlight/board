package com.korea.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.SubController;

public class BoardPostController implements SubController {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		try {
			req.getRequestDispatcher("/WEB-INF/board/post.jsp").forward(req, resp);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
