package com.liddhome.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 初始化方法的实现
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		Object user = request.getSession().getAttribute("sessionUser");
		if (user == null) {
			request.setAttribute("code", "error");
			request.setAttribute("msg", "您还未登录！");
			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, res);
		} else {
			chain.doFilter(request, res);
		}
	}

	@Override
	public void destroy() {
		// 销毁方法的实现
	}
}