package com.korea.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.dto.ReplyDTO;
import com.korea.service.BoardService;

public class BoardReplypostController implements SubController {

	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		 
		
		
		//파라미터 받기
		String comment = req.getParameter("comment");
		String nowPage = req.getParameter("nowPage");
		System.out.println(comment + " " + nowPage);
		//입력값 검증 >> relpyDTO 추가생성 .// 오라클에 시퀀스 추가진행
		
		//1. 보드넘버를 꺼내오기위해 새션을 추가함
		HttpSession session = req.getSession();
		//2. 읽고있는 DTO꺼내기 // board dto 
		BoardDTO dto = (BoardDTO)session.getAttribute("dto");//읽고 있는 게시물
		String email = (String)session.getAttribute("email");//접속 중인 email
		
		
		//리플라이테이블에 넣어둘 내용을 채워넣기
		ReplyDTO rdto = new ReplyDTO();
		rdto.setBno(dto.getNo());
		rdto.setWriter(email);
		rdto.setContent(comment);
		
		// 입력값 검증
		//서비스 실행
		service.replypost(rdto);
		// 뷰 *생략*
		
	}

}
