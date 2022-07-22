package com.korea.controller.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardReplycntController implements SubController {
	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession session = req.getSession();
		
		BoardDTO dto = (BoardDTO)session.getAttribute("dto");
		
		int totalcnt = service.getTotalReplyCnt(dto.getNo());
		
		try {
			PrintWriter out = resp.getWriter();
			
			out.println(totalcnt);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		 
		
	}

}
