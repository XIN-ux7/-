package com.liddhome.admin.filter;

import com.liddhome.admin.entity.Admin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AdminLoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 初始化方法的实现
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		StringBuffer url = request.getRequestURL();
		String path = url.toString();
		if (path.endsWith("login.do")) {
			chain.doFilter(request, res);
		} else {
			Admin admin = (Admin) request.getSession().getAttribute("sessionAdmin");
			if (admin == null) {
				request.setAttribute("msg", "您还未登录！");
				request.getRequestDispatcher("/adminjsps/login.jsp").forward(request, res);
			} else {
				chain.doFilter(request, res);
			}
		}
	}

	@Override
	public void destroy() {
		// 销毁方法的实现
	}
}