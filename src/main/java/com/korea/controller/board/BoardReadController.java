package com.korea.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardReadController implements SubController {
	
	BoardService service = BoardService.getInstance();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		 
		//파라미터
		String no = req.getParameter("no");
		String nowPage = req.getParameter("nowPage");
		

		//서비스실행
		int num = Integer.parseInt(no);
		BoardDTO dto = service.getBoardDTO(num);
		
		//세션에 읽고있는 게시물 저장(수정,삭제로 이동시 현재 읽는 게시물 확인하기 쉽다)
		HttpSession session = req.getSession();
		session.setAttribute("dto", dto);
		
		//뷰로 이동
		
		try {
		
			req.setAttribute("dto", dto);
			req.setAttribute("nowPage", nowPage);
			req.getRequestDispatcher("/WEB-INF/board/read.jsp").forward(req, resp);
		
		
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
