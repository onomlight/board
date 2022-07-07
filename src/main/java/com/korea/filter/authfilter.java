package com.korea.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class authfilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resq, FilterChain chain)
			throws IOException, ServletException {
		 //Requset 전 처리
		System.out.println("========= Filiter 처리 (Request전)!!!! =======");
		chain.doFilter(req, resq);
		// Reponse 전 처리
		System.out.println("========= Filiter 처리 (Reponse전)!!!! =======");
		
	}

}
