package kz.zhanbolat.web.presentation.filter.wrapper;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.owasp.esapi.ESAPI;

public class XSSRequestWrapper extends HttpServletRequestWrapper {
	private static Pattern[] patterns = new Pattern[] {
			Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
			Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
			Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
			Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
	        Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
	        Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
	        Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
	        Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
	        Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
	        Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
	};
	
	public XSSRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return stripXSS(super.getParameter(name));
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		return stripXSS(super.getParameter(name));
	}

	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		String[] values = super.getParameterValues(name);
		
		if (values == null) {
			return null;
		}
		
		String[] encodedValues = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			encodedValues[i] = stripXSS(values[i]);
		}
		
		return encodedValues;
	}
	
	private String stripXSS(String value) {
		if (value != null) {
			value = ESAPI.encoder().canonicalize(value);
			value = value.replaceAll("\0", "");
			for (Pattern pattern : patterns) {
				value = pattern.matcher(value).replaceAll("");
			}
		}
		return value;
	}
	
}
