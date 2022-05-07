package filter;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class NullParameterFilter implements Filter {
	
	String[] parameterName;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 필터가 수행할 기능
		// 처리할 일 : 요청 파라미터를 검사해서 id, name이라는 파라미터에 null 값이 있으면
		// 다른 값으로 채워준다.
		System.out.println("필터가 동작합니다.");
		
		// request에서 파라미터를 가져와서 변경하는것이기 때문에
		// request자체가 변경
		// 이단계를 수행하기위해서 다른 클래스의 도움이 필요
		// HttpServletRequestWrapper : 필터가 요청을 변경한 결과를 다시 request로 만들어줌
		NullParameterRequestWrapper requestWrapper 
		= new NullParameterRequestWrapper((HttpServletRequest)request);
		
		// null 검사해서 null이면 기본값으로 채워주기
		requestWrapper.checkNull(parameterName);
		
		// 다음 단계로 요청을 전달 ( 기존 요청을 우리가 새로 만든 요청으로 바꿔서 전달 )
		chain.doFilter(requestWrapper, response);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String names = filterConfig.getInitParameter("parameterName");
		
		System.out.println(names);
		// names 를 찍어보니 "id,names" 이형태로 문자열이 저장되어있다.
		StringTokenizer st = new StringTokenizer(names, ",");
		// "id,names" ===> "id" , "names" 형태로 잘라줌
		// 잘라낸 결과 갯수만큼 배열의 크기를 초기화
		parameterName = new String[st.countTokens()];
	
		for(int i=0; i<parameterName.length; i++) {
			parameterName[i] = st.nextToken();
		}
		
	}

}
