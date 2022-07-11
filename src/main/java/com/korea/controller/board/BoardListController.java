package com.korea.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardListController implements SubController{
	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		try {
			
			// 파라미터 
			int start=0;
			int end=0;
			// 입력값 검증
			// 서비스실행
			List<BoardDTO> list = service.getBoardList(start,end);
			
			
			
			
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(req, resp);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
