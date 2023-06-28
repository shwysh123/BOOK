package Tests.ControllerTests;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import Controller.BookController;
import Controller.FrontController;
import Controller.MemberController;
import Domain.Common.Dto.BookDto;
import Domain.Common.Dto.MemberDto;

public class ControllerTests {

	@Test
	public void test1_FrontController() {
		FrontController fcontroller = new FrontController();
		assertNotNull(fcontroller);	//NULL����Ȯ��
		fcontroller.execute("/book",1 ,null);
		fcontroller.execute("/member",2 ,null);
		fcontroller.execute("/lend",3 ,null);
		fcontroller.execute("/lfwefwefdate",4 ,null);
	}
	@Test
	public void test2_FrontController_SubController_Init() {
		FrontController fcontroller = new FrontController();
		assertNotNull(fcontroller);	//NULL����Ȯ��
		//fcontroller.execute("/book",1 ,null);	//1 select
		
		Map<String,Object> param= new HashMap();
		param.put("bookname", "�̰����ڹٴ�");
		param.put("publisher", "�Ѻ��̵��");
//		fcontroller.execute("/book",2 ,param);	//2 insert
		
		fcontroller.execute("/member",3 ,null);	//3 update
		fcontroller.execute("/lend",4 ,null);	//4 delete
//		fcontroller.execute("/lfwefwefdate",4 ,null);
	}
	
	@Test
	public void test2_FrontController_SubController_param()
	{
		FrontController fcontroller = new FrontController();
		assertNotNull(fcontroller);	//NULL����Ȯ��
		//fcontroller.execute("/book",1 ,null);	//1 select
		
		Map<String,Object> param= new HashMap();
		param.put("bookcode", 1234);
		param.put("bookname", "�̰����ڹٴ�");
		//param.put("publisher", "�Ѻ��̵��");
		param.put("isbn", "111-1111");
		fcontroller.execute("/book", 2, param);
		
	}
	
	@Test
	public void test3_BookController() throws Exception{
		
		BookController controller = new BookController();
		
		//��ȸ
		Map<String,Object> result = controller.execute(1, null);
		List<BookDto> list = (List<BookDto>)result.get("result");
		//����
		list.stream().forEach((dto)->{System.out.println(dto);});
		
	}
	@Test
	public void test4_MemberController() throws Exception{
		MemberController controller = new MemberController();
		//�α���
		Map<String, Object> param = new HashMap();
		param.put("id","user1");
		param.put("pw","1234");
		Map<String, Object> result = controller.execute(5, param);
		String sid = (String)result.get("result");
		System.out.println("�α��� ����! Sid : "+sid);
		//��ü�����ȸ
		param.put("sid", sid);
		Map<String, Object> result2 = controller.execute(1, param);
		List<MemberDto> list = (List<MemberDto>)result2.get("result");
		list.stream().forEach((dto)->{System.out.println(dto);});
	}
	
	
	

}