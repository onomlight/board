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
			
			// 파라미터  // board list에서 내용을 추가하여 수정해야함
			String tmpstart = req.getParameter("start");
			String tmpend = req.getParameter("end");
			String nowPage = req.getParameter("nowPage");
			// 추가내용  // for문에서 전달할 파라미터 값을 설정함
			int start=0;
			int end=0;
			if(tmpstart ==null || tmpend==null)
			{
				 start=1;
				 end=10;
			}
			else
			{
				start = Integer.parseInt(tmpstart);
				end = Integer.parseInt(tmpend);
				
			}
			
			
			// 입력값 검증
			// 서비스실행
			List<BoardDTO> list = service.getBoardList(start,end);
			int tcnt = service.getTotalCnt();
			
			
			
			
			
			req.setAttribute("list", list);
			req.setAttribute("tcnt", tcnt);
			//정리 6일차(페이징처리) 추가
			req.setAttribute("nowPage", nowPage);
			
			
			
			
			req.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(req, resp);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
