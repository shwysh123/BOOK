package Tests.ServiceTests;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import Domain.Common.Dto.BookDto;
import Domain.Common.Service.BookService;
import Domain.Common.Service.LendService;
import Domain.Common.Service.MemberService;
import Domain.Common.Service.Auth.Session;

public class ServicesTests {

	@Test
	public void test1_BookService() throws Exception{
		
		BookService service = new BookService();
		assertNotNull(service);
		
		service.addBook(new BookDto(101,"이것이리눅스다","B출판사","123456"),"ROLE_MANAGER");
		
		List<BookDto> list =  service.getAllBook();
		list.stream().forEach((dto)->{ System.out.println(dto); });	
	}
	
	@Test
	public void test2_MemberService_login() throws Exception {
		MemberService service = new MemberService();
		String sid = service.login("u1", "1234");
		System.out.println("sid : " +sid);
		Session mySession = service.sessionMap.get(sid);
		System.out.println("mySession : " + mySession );
		
	}
	
	@Test
	public void test3_LendService_ReqLend() throws Exception{
		
		MemberService memberService = new MemberService();
		BookService bookService = new BookService();
		LendService lendService = new LendService();
		assertNotNull(lendService);
		
		lendService.setBookService(bookService);
		lendService.setMemberService(memberService);
		
		//로그인
		String login_sid=memberService.login("member9", "1234");
		System.out.println("login_Sid : " + login_sid); 
		//대여요청
		boolean islend = lendService.reqLend(login_sid, "user1", 3);
		
		
	}

}












