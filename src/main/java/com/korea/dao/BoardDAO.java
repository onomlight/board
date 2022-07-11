package com.korea.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.korea.dto.BoardDTO;

public class BoardDAO {

	//DB연결 
		private String url = "jdbc:oracle:thin:@localhost:1521:xe";
		private String id = "c##book_ex";
		private String pw = "1234";
		
		private Connection conn=null;
		private PreparedStatement pstmt =null;
		private ResultSet rs = null;
		
		
		
		//싱글톤패턴
		private static BoardDAO instance;
		public static BoardDAO getInstance() {
			if(instance==null);
				instance=new BoardDAO();
				return instance;
		}
		private BoardDAO() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url,id,pw);
				System.out.println("DBConnected..");
			}catch(Exception e) {e.printStackTrace();}
			
		}
		
		//
		public List<BoardDTO> Select(int start , int end){
			
			ArrayList<BoardDTO> list = new ArrayList();
			
			BoardDTO dto = null;
			try {
				String sql=
				"select rownum rn,no,title,content,writer,regdate,pwd,count,ip,filename,filesize"
				+" from"
				+"("
				+"		    select /*+ INDEX_EDSC (tbl_board PK_NO) */" //힌트라는 오라클 기능 사용 
				+" 	rownum rn,no,title,content,writer,regdate,pwd,count,ip,filename,filesize"
				+" from tbl_board where rownum<=?" // 앤드값
				+")"
				+" where rn>=?"; // 스타트값
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, end);
				pstmt.setInt(2, start);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					dto=new BoardDTO();
					dto.setNo(rs.getInt("no"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setWriter(rs.getString("writer"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setPwd(rs.getString("pwd"));
					dto.setIp(rs.getString("ip"));
					dto.setFilename(rs.getString("filename"));
					dto.setFilesize(rs.getString("filesize"));
					dto.setCount(rs.getInt("count"));
					list.add(dto); //리스트에 dto 위에잇는정보들 한번더 담고 요청한 위치로 내용전달
				}
				pstmt = conn.prepareStatement("");
			}catch(Exception e) {
				e.printStackTrace();
			}finally {				
				try {rs.close();}catch(Exception e) {e.printStackTrace();}
				try {pstmt.close();}catch(Exception e) {e.printStackTrace();}
			}
			return list;
			
		}
}
