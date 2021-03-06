package com.myclass.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.constants.SessionConstants;
import com.myclass.entity.User;

@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse resp = (HttpServletResponse) response;
//		
//		// KIỂM TRA CÁC LINK KHÔNG DÙNG FILTER
//		String action = req.getServletPath();
//		if(action.equals("/login")) {
//			chain.doFilter(request, response);
//			return;
//		}
//		
//		// -----======== ĐĂNG NHẬP =======------
//		
//		// KIỂM TRA EMAIL, PASSWORD CỦA USER
//		// TH1: Nếu user chưa thực hiện đăng nhập
//		HttpSession session = req.getSession();
//		
//		if (session.getAttribute(SessionConstants.USER_LOGIN) == null) {
//			// Chuyển hướng về trang login
//			resp.sendRedirect(req.getContextPath() + "/login");
//			return;
//		}
//		
//		
//		// -----======== PHÂN QUYỀN =======------
//		User user = (User)session.getAttribute(SessionConstants.USER_LOGIN);
//		
//		// Dùng roleName để phân quyền
//		
		chain.doFilter(request, response);

	}

}
