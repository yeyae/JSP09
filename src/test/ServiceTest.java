package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import dao.MemberDao;
import model.Member;
import service.MemberService;

public class ServiceTest {
	
	MemberService service;
	
	@Test
	public void loginTest() {
		service = new MemberService();
		
		boolean result = service.login("idddd", "1234");
		assertEquals(true, result);
		// 로그인 성공 => true, 로그인 실패 => false
	}
	
//	@Test
//	public void joinTest() {
//		service = new MemberService();
//		
//		Member member = new Member();
//		member.setId("admin");
//		member.setPw("1234");
//		member.setEmail("admin@admin.com");
//		member.setName("운영자");
//		boolean result = service.join(member);
//		assertEquals(true, result);
//		// 회원가입 성공을 예상
//	}
	
	@Test
	public void modifyTest() {
		service = new MemberService();
		MemberDao dao = new MemberDao();
		Member member = dao.selectOne("admin");
		member.setEmail("adminadmin@email.com");
		boolean result = service.modify(member);
		assertEquals(true, result);
		// 회원정보 수정을 예상
	}
	
}
