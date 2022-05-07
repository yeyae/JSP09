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
		// �α��� ���� => true, �α��� ���� => false
	}
	
//	@Test
//	public void joinTest() {
//		service = new MemberService();
//		
//		Member member = new Member();
//		member.setId("admin");
//		member.setPw("1234");
//		member.setEmail("admin@admin.com");
//		member.setName("���");
//		boolean result = service.join(member);
//		assertEquals(true, result);
//		// ȸ������ ������ ����
//	}
	
	@Test
	public void modifyTest() {
		service = new MemberService();
		MemberDao dao = new MemberDao();
		Member member = dao.selectOne("admin");
		member.setEmail("adminadmin@email.com");
		boolean result = service.modify(member);
		assertEquals(true, result);
		// ȸ������ ������ ����
	}
	
}
