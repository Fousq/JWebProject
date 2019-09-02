package kz.zhanbolat.web.presentation.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns= {"/profile", "/advert", "/delete", "/manage", "/edit"})
public class AuthorizationFilter implements Filter {
	private FilterConfig config;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request; 
		HttpServletResponse resp = (HttpServletResponse) response;
		Object userId = req.getSession().getAttribute("id");
		if (userId == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
