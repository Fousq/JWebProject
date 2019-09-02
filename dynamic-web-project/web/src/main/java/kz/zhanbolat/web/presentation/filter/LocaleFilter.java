package kz.zhanbolat.web.presentation.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(urlPatterns="/*")
public class LocaleFilter implements Filter {
	private static Logger logger = LogManager.getLogger(LocaleFilter.class);
	private FilterConfig config;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
						 FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String lang = req.getParameter("locale");
		if (lang != null) {
			session.setAttribute("lang", lang);
		}
		if (lang == null && session.getAttribute("lang") == null) {
			String userLocale = req.getLocale().toString().substring(3);
			try {
				lang = SupportedLanguages.valueOf(userLocale).getCode();
				logger.debug(lang);
			} catch (IllegalArgumentException e) {
				lang = "en";
			}
			session.setAttribute("lang", lang);
		}
		chain.doFilter(request, response);
	}
	
	private static enum SupportedLanguages {
		EN("en"), RU("ru");
		
		private String code;
		
		SupportedLanguages(String code) {
			this.code = code;
		}
		
		private String getCode() {
			return code;
		}
		
	}

	@Override
	public void destroy() {

	}
}
