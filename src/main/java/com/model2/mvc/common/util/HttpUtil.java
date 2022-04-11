package com.model2.mvc.common.util;

import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class HttpUtil {
	
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path){
		
		System.out.println("\n* [ HttpUtil : forward() ] ");
		
		System.out.println("\n* ==== START ==========================================================");
		
		HttpSession session = request.getSession();
		
		//���ǿ� �ִ� ��� �������� �̸��� �����´�
		Enumeration sessionNames = session.getAttributeNames();
		int sessionCount = 0;
		
		//sessionNames ��ü�� ��� ��ҵ�
		while(sessionNames.hasMoreElements()){
			String key = sessionNames.nextElement().toString();
			String value = session.getAttribute(key).toString();
			System.out.println("\n * ���� " 
								+ "\n key : " + key 
								+ "\n value : " + value + "\n");
			sessionCount++;
		}
		
		//request�� �ִ� ��� �������� �̸��� �����´�
		Enumeration requestNames = request.getAttributeNames();
		int requestCount = 0;
		
		//requestNames ��ü�� ��� ��ҵ�
		while(requestNames.hasMoreElements()){
			String key = requestNames.nextElement().toString();
			String value = request.getAttribute(key).toString();
			System.out.println("\n * request - " + requestCount+1 
								+ "\n key : " + key 
								+ "\n value : " + value + "\n");
			requestCount++;
		}
		
		System.out.println("\n* sessionCount : " + sessionCount);
		System.out.println("\n* requestCount : " + requestCount);
				
		System.out.println("\n* ==== E N D ==========================================================");
		
		try{
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}catch(Exception ex){
			System.out.println("forward ���� : " + ex);
			throw new RuntimeException("forward ���� : " + ex);
		}
	}
	
	public static void redirect(HttpServletResponse response, String path){
		
		System.out.println("\n* [ HttpUtil : redirect() ] ");
		
		try{
			response.sendRedirect(path);
		}catch(Exception ex){
			System.out.println("redirect ���� : " + ex);
			throw new RuntimeException("redirect ����  : " + ex);
		}
	}
}