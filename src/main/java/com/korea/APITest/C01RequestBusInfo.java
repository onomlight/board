package com.korea.APITest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReqBus.Info")
public class C01RequestBusInfo extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		
		//파라미터 받기
		String serviceKey="00cdRlwrMjS4gVVR2JIDZgF4bKenwV21kqsrtLQamIji3Hg7C8SsVF7jMrJVwfCbgVbTBnyDNkNcVheAweYUFA%3D%3D";
		String numOfRows="100";
		String pageNo="1";
		String _type="json";
		String depTerminalId=req.getParameter("depTerminalId");	//출발터미널ID
		String arrTerminalId=req.getParameter("arrTerminalId");	//목적지터미널ID
		String depPlandTime=req.getParameter("depPlandTime");	//출발날짜
		depPlandTime=depPlandTime.replaceAll("-", "");			// - 부호 빼기
		String busGradeId=req.getParameter("busGradeId");		//버스등급
		
		//경로 설정
		String addr="http://apis.data.go.kr/1613000/ExpBusInfoService/getStrtpntAlocFndExpbusInfo?"
				+"serviceKey="+serviceKey
				+"&depTerminalId="+depTerminalId
				+"&arrTerminalId="+arrTerminalId
				+"&depPlandTime="+depPlandTime
				+"&busGradeId="+busGradeId
				+"&numOfRows="+numOfRows
				+"&pageNo="+pageNo
				+"&_type=json";
		
//		System.out.println(addr);
		
		
		URL url = new URL(addr); 
		// 크롤링 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
		//					보조 스트림[고속전송하게끔]		보조스트림 				기본스트림			1바이트를 2바이트로 바꿈
		// 스트림을 두번 묶어서 사용하게함
		
		StringBuffer sb = new StringBuffer(); // 받아오는 데이터 버퍼들을 문자열에 계속 덧붙일것
		String str = null;
		
		while(true) {
			str=br.readLine();
			if(str==null)
				break;
			sb.append(str);
		}
		br.close();
		System.out.println(sb.toString()); // json 형태로 변환되어 전달함 
		resp.getWriter().write(sb.toString());
		
	}

	

}





