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
		// ���Ͱ� �����ɶ� ����
		// �ַ� ���Ͱ� ����� �ڿ��� �ݳ�
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// ���� ��� ����
		
		// 1. request �Ķ���͸� �̿��ؼ� ��û�� ���� �۾� ����
		
		// 2. ü���� ���� ���� ó��
		// ���ʹ� �Ѱ��� �ƴ϶� �������� ���� ����Ҽ� �ִ�.
		// �� ���� ��� ������ ������ ���Ͱ� ����� �����ϰ� �ȴ�.
		chain.doFilter(request, response);
		
		// 3. response ��ü�� �̿��ؼ� ������ ���͸� �۾� ����
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// ���� �ʱ�ȭ �۾�
	}

}
