package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter {
	
	@Override
	public void destroy() {
		// 필터가 삭제될때 실행
		// 주로 필터가 사용한 자원을 반납
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 필터 기능 수행
		
		// 1. request 파라미터를 이용해서 요청의 필터 작업 수행
		
		// 2. 체인의 다음 필터 처리
		// 필터는 한개가 아니라 여러개를 같이 사용할수 있다.
		// 이 필터 기능 다음에 수행할 필터가 기능을 수행하게 된다.
		chain.doFilter(request, response);
		
		// 3. response 객체를 이용해서 응답의 필터링 작업 수행
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 필터 초기화 작업
	}

}
