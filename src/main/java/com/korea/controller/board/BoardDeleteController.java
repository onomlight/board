package com.korea.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardDeleteController implements SubController{
	
	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		//파라미터
		String pwd = req.getParameter("pwd");
		String nowPage = req.getParameter("nowPage");
		//읽고 있는 게시물 꺼내기
		HttpSession session = req.getSession();
		BoardDTO dto = (BoardDTO)session.getAttribute("dto");
		//입력값
		if(dto.getPwd().equals(pwd))//패스워드일치여부
		{
			//서비스함수 실행 (게시물dto)
			service.BoardRemove(dto); // dto를 전달하고 삭제해야함
		}
		//서비스
		//뷰
	}

}
