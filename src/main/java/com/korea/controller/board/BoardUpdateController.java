package com.korea.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardUpdateController implements SubController{

	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String pwd = req.getParameter("pwd");
		String nowPage = req.getParameter("nowPage");
		
		
		HttpSession session = req.getSession();
		BoardDTO dto = (BoardDTO)session.getAttribute("dto");
		
		
		// 읽고있는 게시물 [ pwd, db,session에 넣기 ]
		if(dto.getPwd().equals(pwd))//읽고있는게시물의 PW와 변경요청시 전달한PW 일치시
		{
			//서비스함수 호출 -> DAO ->DB 변경
			dto.setTitle(title); //변경된 타이틀
			dto.setContent(content); //변경된 컨텐츠
			
//			System.out.println("TITLE : " +title);
//			System.out.println("content : " + content);
//			System.out.println("pwd : " + pwd);

			
			service.UpdateBoard(dto); //업데이트 요청서비스
			session.setAttribute("dto", dto); //세션객체에 읽고있는 게시물 변경 저장
			
			
			
			//Read.jsp로 이동(no,nowPage전달)
			try {
				String MSG = "성공!!";
				req.setAttribute("MSG", MSG);
				req.getRequestDispatcher("/Board/read.do?no="+dto.getNo()+"&nowPage="+nowPage).forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{	
			//Read.jsp로 이동.. MSG 전달
			try {
				
				String MSG="패스워드 불일치!";
				req.setAttribute("MSG", MSG);
				req.getRequestDispatcher("/Board/read.do?no="+dto.getNo()+"&nowPage="+nowPage).forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

}
