package filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class NullParameterRequestWrapper extends HttpServletRequestWrapper {
	
	// ��û�� ó�� 
	// ��û�� �Ķ���͸� Map ���·� ������ �ֽ��ϴ�.
	// �츮�� ��û�� ó���Ҷ��� �Ķ���͸� Map ���·� ���� ����Ѵ�.
	private Map<String, String[]> parameterMap;
	
	public NullParameterRequestWrapper(HttpServletRequest request) {
		super(request);
		// ������ ��û�� �ִ� �Ķ���͸� �����ؾ� �ϱ� ������
		// request���� �Ķ���͸��� �����ɴϴ�.
		parameterMap = new HashMap<String, String[]>(request.getParameterMap());
	}
	// �츮�� ��û�� ���� ����� ���̱� ������
	// ���� request ��ü�� �ϴ� �ϵ鵵 ��� ����� �Ѵ�.
	// �Ķ���� ���� �޼ҵ���� �������̵� ���ش�.
	// getParameter, getParameterNames, getParameterMap, getParameterValues
	
	public void checkNull(String[] parameterNames) {
		// �츮�� ������ �Ķ���Ͱ� null �ϰ�� ( ���� ��� )
		// �Ķ������ �⺻���� �߰����ش�.
		// �츮�� ������ �ִ� �Ķ���ʹ� map �� ���·� ����
		// map �� �ش� Ű�� ���� ã������ �� ����� null �̸� �Ķ���Ͱ� ���°Ű�
		// ����� ������ �Ķ���Ϳ� ���� ����
		
		for(int i=0; i<parameterNames.length; i++) {
			String param = parameterNames[i];
			if(!parameterMap.containsKey(param)) {
				// �츮�� �������ִ� �Ķ���� �ʿ� 
				// �ش� �Ķ���͸� Ű�� ������ �ִ��� Ȯ��
				// Ű�� ������ �ִٸ� �ش��ϴ� value�� �ֱ� ������
				// �Ķ���� ���� ���� �Ѵٴ� ��
				// containsKey(key�̸�) : map �ȿ� key�̸����� ���� ����Ǿ� �ִ��� Ȯ��
				// ����Ǿ� �ִٸ� true, ����ȵǾ��ִٸ� false
				
				// �Ķ���� ������ֱ� (null �� ���)
				String[] values = new String[] {"�� ����"};
				// ���� �Ķ���� �Ķ���͸ʿ� �ֱ�
				parameterMap.put(param, values);
			}
			
		}
		
		
	}
	
	
	@Override
	public String getParameter(String name) {
		// �츮�� servlet���� ����ߴ� getParameter()
		// ���ڿ� �迭���� ��ġ�ϴ� ù��° ��Ҹ� ������ �ָ� �ȴ�.
		String[] values = getParameterValues(name);
		if(values != null && values.length > 0) {
			return values[0];
		}
		return null;
	}
	
	@Override
	public Enumeration<String> getParameterNames() {
		// TODO Auto-generated method stub
		// map�� key ������ enumeration Ÿ������ ��ȯ�ؼ� ��ȯ
		return Collections.enumeration(parameterMap.keySet());
	}
	
	@Override
	public String[] getParameterValues(String name) {
		// �츮�� �������ִ� map���� key�� ���� value�� ������ �ָ� �ȴ�.
		return parameterMap.get(name);
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		
		return this.parameterMap;
	}
	
}
