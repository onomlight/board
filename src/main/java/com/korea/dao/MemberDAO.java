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
		
		
		return false;
	}
}
