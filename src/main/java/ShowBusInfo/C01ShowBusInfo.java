package ShowBusInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ShowBus.Info")
public class C01ShowBusInfo extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	//	System.out.println("TEST");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		
		String addr = "http://apis.data.go.kr/1613000/ExpBusInfoService/getExpBusTrminlList?serviceKey=00cdRlwrMjS4gVVR2JIDZgF4bKenwV21kqsrtLQamIji3Hg7C8SsVF7jMrJVwfCbgVbTBnyDNkNcVheAweYUFA%3D%3D&pageNo=1&numOfRows=230&_type=json";
		
		
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
//		System.out.println(sb.toString());
		resp.getWriter().write(sb.toString());
	}

}
