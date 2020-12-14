package kr.ac.sunmoon.urs.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
		
		String type = session != null ? session.getAttribute("type") != null ? String.valueOf(session.getAttribute("type")) : "" : "";
		//String type = session.getAttribute("type") != null ? String.valueOf(session.getAttribute("type")) : "";
		if ("M".equals(type) || "E".equals(type)) {
			String path = request.getServletPath().split("/")[1];
			if ("M".equals(type) 
					&& ("lockdevice".equals(path) 
					|| "rental".equals(path))) {
				response.sendRedirect(request.getContextPath() + "/dept");
			}
			
			if ("E".equals(type) 
					&& ("dept".equals(path) 
							|| "emp".equals(path) 
							|| "student".equals(path))) {
				
				// 학생 GET 방식이면서 폼이 포함되지 않는경우 
				//System.out.println(request.getMethod() + ":" + request.getServletPath().indexOf("form"));
				if ("student".equals(path) 
						&& "GET".equals(request.getMethod()) 
						&& request.getServletPath().indexOf("form") == -1) {
					
					return true;
				}
				// 직원 수정폼과 수정
				System.out.println(session.getAttribute("memberNo"));
				if ("emp".equals(path) 
						&& ((request.getServletPath().indexOf(String.valueOf(session.getAttribute("memberNo"))) > 0 && request.getServletPath().indexOf("search") == -1) 
								|| request.getServletPath().indexOf("check") > 0
								|| "PUT".equals(request.getMethod()))) {
					
					return true;
				}
				
				response.sendRedirect(request.getContextPath() + "/lockdevice");
			}
			
			return true;
		} else {
			System.out.println("인터 1");
			response.sendRedirect(request.getContextPath() + "/common/login/form");
			
			return false;
		}
	}
}
