package com.korea.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Part;

import com.korea.dao.BoardDAO;
import com.korea.dto.BoardDTO;

public class BoardService {

	private BoardDAO dao = BoardDAO.getInstance();
	private String UploadPath="C://upload/"; //File.seperator
	
	//싱글톤 패턴
	private static BoardService instance=null;
	public static BoardService getInstance() {
		if(instance==null)
			instance = new BoardService();
		return instance;
	}
	private BoardService() {}
	
	
	public List<BoardDTO> getBoardList(int start,int end)
	{
		return dao.Select(start, end);
	}
	
	public int getTotalCnt() {
		return dao.getTotalCount();
	}
	
	public boolean PostBoard(BoardDTO dto)
	{
		return dao.Insert(dto);
	}
	
	//파일포함 글쓰기 서비스
	public boolean PostBoard(BoardDTO dto,ArrayList<Part> parts)
	{
		//업로드 처리
		//1) 하위폴더명(Email/2022-07-14/파일쌓이기~)
		//2) 기본업로드Path+하위폴더명
		String email=dto.getWriter();
		Date now = new Date(); 
		//날짜포맷변경하기 : https://junghn.tistory.com/entry/JAVA-%EC%9E%90%EB%B0%94-%EB%82%A0%EC%A7%9C-%ED%8F%AC%EB%A7%B7-%EB%B3%80%EA%B2%BD-%EB%B0%A9%EB%B2%95SimpleDateFormat-yyyyMMdd
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		String date = simpleDateFormat.format(now); 
		
		String subPath=email+"/"+date;
		
		//3) File클래스 경로 잡고
		File RealPath= new File(UploadPath+subPath);
		
		//4) 하위폴더 생성
		if(!RealPath.exists())
			RealPath.mkdirs();
		
		
		//파일명 저장위한 StringBuffer 추가
		StringBuffer totalFilename = new StringBuffer();
		//파일사이즈 저장위한 StringBuffer 추가
		StringBuffer totalFilesize = new StringBuffer();
		
		
		//5) Parts의 각 Part 를 write()
		System.out.println("Parts : "+ parts.size());
		for(Part part : parts)
		{
			if(part.getName().equals("files"))
			{
				String FileName=getFileName(part); //파일이름 가져오기
				
				//DTO저장위한 파일명 buffer에추가
				totalFilename.append(FileName+";");
				//DTO저장위한 파일사이즈 buffer에추가				 
				totalFilesize.append(String.valueOf(part.getSize())+";");
				
				
				//파일명 ,확장자명 구분하기
				int start=FileName.length()-4;		//확장자구하기 위한 시작idx
				int end=FileName.length();			//확장자구하기 위한 끝idx
				String ext=FileName.substring(start,end);	//파일명잘라내기(확장자만)
				FileName = FileName.substring(0,start-1);	//파일명잘라내기(확장자제외)
				
				//파일명 + _UUID + 확장자
				FileName=FileName+"_"+UUID.randomUUID().toString()+ext;
				
			
				
				 try {
					part.write(RealPath+"/"+FileName); //파일업로드
				} catch (IOException e) {	 
					e.printStackTrace();
				}
			}
		}
		
		//Dto에 총파일명과 총파일사이즈를 저장
		dto.setFilename(totalFilename.toString());
		dto.setFilesize(totalFilesize.toString());
		
		//Dao 파일명전달 
		return dao.Insert(dto);
	}
	//파일명추출(PostBoard(dto,parts)가 사용)
	private String getFileName(Part part)
	{
		String contentDisp=part.getHeader("content-disposition");
		System.out.println(contentDisp);
		String[] arr = contentDisp.split(";"); // 배열화 		
		String Filename=arr[2].substring(11,arr[2].length()-1);	
		return Filename;
	}
	
}
