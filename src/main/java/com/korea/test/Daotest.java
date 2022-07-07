package com.korea.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.korea.dao.MemberDAO;
import com.korea.dto.MemberDTO;

public class Daotest {

//	@Test
//	public void test() {
//		 MemberDTO dto = new MemberDTO();
//		 dto.setEmail("example@example.com");
//		 dto.setPwd("1234");
//		 dto.setAddr1("대구광역시 달서구 000");
//		 dto.setAddr2("00ART 000-000");
//		 
//		 MemberDAO dao = MemberDAO.getInstance();
//		 boolean result = dao.insert(dto);
//		 if(result)
//			 System.out.println("INSERT 성공");
//		 else
//			 System.out.println("INSERT 실패");
//	}
//	@Test
//	public void Test2() {
//		//MemberDAO's Select(email)
//		MemberDAO dao = MemberDAO.getInstance();
//		MemberDTO dto = dao.Select("example@example.com");
//		System.out.println("결과 : " +dto.toString());
//	}

	@Test
	public void Test3() {
		MemberDTO dto = new MemberDTO();
		dto.setEmail("example@example.com");
		dto.setPwd("0987");
		dto.setAddr1("쏘울");
		dto.setAddr2("양구 통동");
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.Update(dto);
	}
	
}
