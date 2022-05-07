package filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class NullParameterRequestWrapper extends HttpServletRequestWrapper {
	
	// 요청을 처리 
	// 요청은 파라미터를 Map 형태로 가지고 있습니다.
	// 우리가 요청을 처리할때도 파라미터를 Map 형태로 만들어서 줘야한다.
	private Map<String, String[]> parameterMap;
	
	public NullParameterRequestWrapper(HttpServletRequest request) {
		super(request);
		// 기존의 요청에 있는 파라미터를 조작해야 하기 때문에
		// request에서 파라미터맵을 가져옵니다.
		parameterMap = new HashMap<String, String[]>(request.getParameterMap());
	}
	// 우리가 요청을 새로 만드는 것이기 때문에
	// 원래 request 객체가 하던 일들도 모두 해줘야 한다.
	// 파라미터 관련 메소드들을 오버라이딩 해준다.
	// getParameter, getParameterNames, getParameterMap, getParameterValues
	
	public void checkNull(String[] parameterNames) {
		// 우리가 지정한 파라미터가 null 일경우 ( 없는 경우 )
		// 파라미터의 기본값을 추가해준다.
		// 우리가 가지고 있는 파라미터는 map 의 형태로 존재
		// map 에 해당 키로 값을 찾았을때 그 결과가 null 이면 파라미터가 없는거고
		// 결과가 있으면 파라미터에 값이 존재
		
		for(int i=0; i<parameterNames.length; i++) {
			String param = parameterNames[i];
			if(!parameterMap.containsKey(param)) {
				// 우리가 가지고있는 파라미터 맵에 
				// 해당 파라미터를 키로 가지고 있는지 확인
				// 키로 가지고 있다면 해당하는 value도 있기 때문에
				// 파라미터 값이 존재 한다는 뜻
				// containsKey(key이름) : map 안에 key이름으로 값이 저장되어 있는지 확인
				// 저장되어 있다면 true, 저장안되어있다면 false
				
				// 파라미터 만들어주기 (null 일 경우)
				String[] values = new String[] {"값 없음"};
				// 만든 파라미터 파라미터맵에 넣기
				parameterMap.put(param, values);
			}
			
		}
		
		
	}
	
	
	@Override
	public String getParameter(String name) {
		// 우리가 servlet에서 사용했던 getParameter()
		// 문자열 배열에서 일치하는 첫번째 요소를 꺼내서 주면 된다.
		String[] values = getParameterValues(name);
		if(values != null && values.length > 0) {
			return values[0];
		}
		return null;
	}
	
	@Override
	public Enumeration<String> getParameterNames() {
		// TODO Auto-generated method stub
		// map의 key 집합을 enumeration 타입으로 변환해서 반환
		return Collections.enumeration(parameterMap.keySet());
	}
	
	@Override
	public String[] getParameterValues(String name) {
		// 우리가 가지고있는 map에서 key를 통해 value를 가져다 주면 된다.
		return parameterMap.get(name);
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		
		return this.parameterMap;
	}
	
}
