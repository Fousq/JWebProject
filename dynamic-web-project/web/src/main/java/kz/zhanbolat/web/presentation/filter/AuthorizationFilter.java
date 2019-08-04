package kz.zhanbolat.web.presentation.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns= {"/profile", "/advert", "/delete", "/manage", "/edit"})
public class AuthorizationFilter implements Filter {

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
	
}
