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
		// ���Ͱ� ������ ���
		// ó���� �� : ��û �Ķ���͸� �˻��ؼ� id, name�̶�� �Ķ���Ϳ� null ���� ������
		// �ٸ� ������ ä���ش�.
		System.out.println("���Ͱ� �����մϴ�.");
		
		// request���� �Ķ���͸� �����ͼ� �����ϴ°��̱� ������
		// request��ü�� ����
		// �̴ܰ踦 �����ϱ����ؼ� �ٸ� Ŭ������ ������ �ʿ�
		// HttpServletRequestWrapper : ���Ͱ� ��û�� ������ ����� �ٽ� request�� �������
		NullParameterRequestWrapper requestWrapper 
		= new NullParameterRequestWrapper((HttpServletRequest)request);
		
		// null �˻��ؼ� null�̸� �⺻������ ä���ֱ�
		requestWrapper.checkNull(parameterName);
		
		// ���� �ܰ�� ��û�� ���� ( ���� ��û�� �츮�� ���� ���� ��û���� �ٲ㼭 ���� )
		chain.doFilter(requestWrapper, response);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String names = filterConfig.getInitParameter("parameterName");
		
		System.out.println(names);
		// names �� ���� "id,names" �����·� ���ڿ��� ����Ǿ��ִ�.
		StringTokenizer st = new StringTokenizer(names, ",");
		// "id,names" ===> "id" , "names" ���·� �߶���
		// �߶� ��� ������ŭ �迭�� ũ�⸦ �ʱ�ȭ
		parameterName = new String[st.countTokens()];
	
		for(int i=0; i<parameterName.length; i++) {
			parameterName[i] = st.nextToken();
		}
		
	}

}
