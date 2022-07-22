package com.korea.controller.board;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.dto.ReplyDTO;
import com.korea.service.BoardService;

public class BoardReplylistController implements SubController {
	BoardService service = BoardService.getInstance();//서비스를 먼저 받아오게 올려놓기
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	 //읽고있는 게시물의 모든 댓글 출력
		
		
		//세션확인
		HttpSession session = req.getSession();
		//1.게시물번호 받아오기
		BoardDTO dto = (BoardDTO)session.getAttribute("dto");
		//2. 내용 받아오기 형태는 어래이리스트
		
		ArrayList<ReplyDTO> list= service.getReplylist(dto.getNo());
		
		
		try {
			PrintWriter out = resp.getWriter();
//			resp.getWriter(); // 반환형이 아웃
			
				for(int i=0;i<list.size();i++) {
					out.println("<div class=\"form-control\">");
					out.println("<span style=font-size:0.5rem>"+list.get(i).getWriter()+"</span>&nbsp;&nbsp;");
					out.println("<span style=font-size:0.5rem>"+list.get(i).getRegdate()+"</span><br>");
					out.println("<span>"+list.get(i).getContent()+"</span>");
					out.println("</div>");
//				out.println(list.get(i).getWriter()+" ");
//				out.println(list.get(i).getRegdate()+" ");
//				out.println(list.get(i).getContent()+"\n");
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	 
	}

}
