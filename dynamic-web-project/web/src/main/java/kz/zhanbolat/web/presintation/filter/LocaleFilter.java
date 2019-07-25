package kz.zhanbolat.web.presintation.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(urlPatterns="/*")
public class LocaleFilter implements Filter {
	private static Logger logger = LogManager.getLogger(LocaleFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
						 FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		logger.debug(req.getParameter("sessionLocale"));
		if (req.getParameter("sessionLocale") != null) {
			req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
		}
		chain.doFilter(request, response);
	}
	
}
