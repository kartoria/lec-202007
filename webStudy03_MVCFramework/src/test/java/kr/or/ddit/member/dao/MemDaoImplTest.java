package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MemDaoImplTest {
	private IMemberDao dao = MemberDaoImpl_JDBC.getInstance();
	private String mem_id;
	
	@Before
	public void setup() {
		System.out.println("setup");
		mem_id = "a002";
	}
	
	@Test
	public void testSelectMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectMemberList() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteMember() {
		int rowcnt = dao.deleteMember(mem_id);
		assertEquals(1, rowcnt);
	}

}
