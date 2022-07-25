package com.korea.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.korea.dto.MemberDTO;

public class MemberDAO {
	//DB연결 
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "c##book_ex";
	private String pw = "1234";
	
	private Connection conn=null;
	private PreparedStatement pstmt =null;
	private ResultSet rs = null;
	
	
	
	//싱글톤패턴
	private static MemberDAO instance;
	public static MemberDAO getInstance() {
		if(instance==null);
			instance=new MemberDAO();
			return instance;
	}
	private MemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pw);
			System.out.println("DBConnected..");
		}catch(Exception e) {e.printStackTrace();}
		
	}
	
	//INSERT함수
	public boolean insert(MemberDTO dto)
	{
		try {
			pstmt=conn.prepareStatement("insert into tbl_member values(?,?,?,?,?,?)");
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getAddr1());
			pstmt.setString(4, dto.getAddr2());
			pstmt.setInt(5, dto.getGrade());
			pstmt.setString(6, dto.getZipcode());
			
			int result = pstmt.executeUpdate();
			if(result>0)
				return true;
			
		}catch(Exception e) {
		e.printStackTrace();
		} finally {
			try {pstmt.close();}catch(Exception e1) {e1.printStackTrace();}
		}
		
		return false;
	}
	public MemberDTO Select(String email) {
		MemberDTO dto = new MemberDTO();
		try {
			pstmt=conn.prepareStatement("select * from tbl_member where email=?");
			pstmt.setString(1, email);
			// 정보 이메일을 넣고 진행함
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 널이아니라면 확인

					dto.setEmail(email); // 이메일은 중복허용하지않음 프라이머리키라 // 
					dto.setPwd(rs.getString("pwd"));
					dto.setAddr1(rs.getString("addr1"));
					dto.setAddr2(rs.getString("addr2"));
					dto.setGrade(rs.getInt("grade"));
					return dto;

			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//서블릿이라 자원제거해야함!!!
			try{rs.close();}catch(Exception e) {e.printStackTrace();}
			try{pstmt.close();}catch(Exception e) {e.printStackTrace();}
		}
		return null;
	}
	public boolean Update (MemberDTO dto) {
		
		try {
			pstmt =conn.prepareStatement
					("update tbl_member set addr1=?,addr2=?,grade=?,pwd=? where email=? ");
			pstmt.setString(1, dto.getAddr1());
			pstmt.setString(2, dto.getAddr2());
			pstmt.setInt(3, dto.getGrade());
			pstmt.setString(4, dto.getPwd());
			pstmt.setString(5, dto.getEmail());
			
			int result = pstmt.executeUpdate();
			
			if(result>0)
				return true;
		}catch (Exception e) { 
			e.printStackTrace();

		}
		return false;
	}
}
