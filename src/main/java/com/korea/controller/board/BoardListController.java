package com.korea.controller.board;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardListController implements SubController{

	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		 
		try {
			
			//파라미터
			String tmpstart = req.getParameter("start");
			String tmpend = req.getParameter("end");
			String nowPage = req.getParameter("nowPage");
			int start=0;
			int end=0;
			if(tmpstart==null || tmpend==null)
			{
				start=1;
				end=10;
			}
			else
			{
				start = Integer.parseInt(tmpstart);
				end = Integer.parseInt(tmpend); // 나중에 수정할것
				
			}

			//입력값
			//서비스실행
			List<BoardDTO> list = service.getBoardList(start, end);
			int tcnt = service.getTotalCnt();
			
			req.setAttribute("list", list);
			req.setAttribute("tcnt", tcnt);
			//정리 6일차(페이징처리) 추가
			req.setAttribute("nowPage", nowPage);
			
			
			//쿠키 생성(게시글 읽기 새로고침시 중복count방지)
			Cookie init = new Cookie("init","true");
			resp.addCookie(init);
			
			
			req.getRequestDispatcher("/WEB-INF/board/list.jsp?nowPage="+nowPage).forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}

}
