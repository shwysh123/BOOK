package Tests.DaoTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import Domain.Common.Dao.BookDao;
import Domain.Common.Dao.LendDao;
import Domain.Common.Dao.MemberDao;
import Domain.Common.Dto.BookDto;
import Domain.Common.Dto.LendDto;
import Domain.Common.Dto.MemberDto;

public class DaoTests {

	@Test
	public void BookDaoTest1() {
		BookDao dao = new BookDao();
		//객체연결 여부 확인 Test함수
		assertNotNull(dao);
	}
	
	@Test
	public void BookDaoTest2_insert() throws Exception {
		BookDao dao = new BookDao();
		assertNotNull(dao);
		int result=dao.insert(new BookDto(1,"JAVA의정석","A출판사","1111"));
		assertEquals(1,result); //기대값,실제값
	}
	@Test
	public void BookDaoTest3_insert_post100() throws Exception {
		BookDao dao = new BookDao();
		assertNotNull(dao);
		int result=0;
		for(int i=1;i<100;i++) {
			result+=dao.insert(new BookDto(1+i,"JAVA의정석"+i,"A출판사","1111"+i));
		}
		assertEquals(99,result); //기대값,실제값
		
	}
	@Test
	public void BookDaoTest4_select() throws Exception {
		BookDao dao = new BookDao();
		assertNotNull(dao);
 
		List<BookDto> list = dao.select();
		assertNotNull(list);
		
		list.stream().forEach((dto)->{
			System.out.println(dto);
		} );
	}
	
	@Test
	public void BookDaoTest5_select_bookcode() throws Exception {
		BookDao dao = new BookDao();
		assertNotNull(dao);
 
		BookDto dto = dao.select(10);
		assertNotNull(dto);
		
		System.out.println(dto);
		 
	}	
	//MemberDao ----------------------------------------------------
	@Test
	public void MemberDaoTest1_insert() throws Exception {
		MemberDao dao = new MemberDao();
		assertNotNull(dao);
		int result=dao.insert(new MemberDto("user1","1111","홍길동","ROLE_USER"));
		assertEquals(1,result); //기대값,실제값	
	}
	
	@Test
	public void MemberDaoTest2_update() throws Exception {
		MemberDao dao = new MemberDao();
		assertNotNull(dao);
		int result=dao.update(new MemberDto("user1","1234","hongGilDong","ROLE_MEMBER"));
		assertEquals(1,result); //기대값,실제값		
	}
	
	@Test
	public void MemberDaoTest3_delete() throws Exception {
		MemberDao dao = new MemberDao();
		assertNotNull(dao);
		int result=dao.delete("user1");
		assertEquals(1,result); //기대값,실제값		
	}	
	@Test
	public void MemberDaoTest4_postMember() throws Exception {
		MemberDao dao = new MemberDao();
		assertNotNull(dao);
		int result=0;
		for(int i=1;i<=5;i++) {
			result += dao.insert(new MemberDto("user"+i,"1234","username"+i,"ROLE_USER"));
		}
		for(int i=6;i<=10;i++) {
			result += dao.insert(new MemberDto("member"+i,"1234","member"+i,"ROLE_MEMBER"));
		}	
		assertEquals(10,result);
	}
	@Test
	public void MemberDaoTest5_select() throws Exception {
		MemberDao dao = new MemberDao();
		assertNotNull(dao);
		List<MemberDto> list = dao.select();
		
		list.stream().forEach( (dto)->{ System.out.println(dto);} );
		
		//단건 조회
		MemberDto dto =  dao.select("user2");
		System.out.println("단건조회 : " + dto);
		
	}	
	//LendDao-----------------------------------------
	@Test
	public void LendDaoTest1_insert() throws Exception{
		
		LendDao dao = new LendDao();
		assertNotNull(dao);	//Unit Test Function
		
		LendDto dto = new LendDto();
		dto.setBookcode(1);
		dto.setId("user1");
		int result= dao.insert(dto);
		
		assertEquals(1,result);//Unit Test Function
		 
	}
	//ReserveDao-----------------------------------------

}






