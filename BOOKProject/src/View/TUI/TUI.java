package View.TUI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Controller.FrontController;
import Domain.Common.Dto.BookDto;

public class TUI {
	private String sid; // SessionId
	private String id; // UserId
	private String role;
	private Scanner sc = new Scanner(System.in);
	private FrontController controller;
	TUI(){
		controller = new FrontController();
	}
	
	public void MainMenu() {

		while (true) {
			System.out.println("--------------------------");
			System.out.println("MAIN");
			System.out.println("--------------------------");
			System.out.println("1 ������ȸ");
			System.out.println("2 �α���");
			System.out.println("3 ȸ������");
			System.out.println("4 ����");
			System.out.print("��ȣ : ");
			int num = sc.nextInt();
			switch(num)
			{
			case 1:
				break;
			case 2:
				loginMenu();
				break;
			case 3:
				break;
			case 4:
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(-1);
			}
		}

	}

	public void loginMenu() {
		System.out.println("--------------------------");
		System.out.println("�α���");
		System.out.println("--------------------------");
		System.out.print("ID : " );
		String id = sc.next();
		System.out.print("PW : " );
		String pw = sc.next();
		
		//�α��� ����!!
		Map<String,Object> param = new HashMap();
		param.put("id", id);
		param.put("pw", pw);
		
		
		Map<String,Object> result = controller.execute("/member", 5 , param);
		String sid = (String)result.get("sid");
		String role = (String)result.get("role");
		if(sid!=null) {
			this.sid = sid;
			this.id = id;
			this.role = role;
		}
		//���� �����
		if(role.equals("ROLE_MEMBER"))
			�缭Menu();
		else if(role.equals("ROLE_USER"))
			ȸ��Menu();
	}
	public void �缭Menu() {
		while (true) {
			System.out.println("--------------------------");
			System.out.println("�缭�޴�");
			System.out.println("--------------------------");
			System.out.println("[����]			[ȸ��]			[�뿩]");
			System.out.println("1 ������ȸ�ϱ�		5 ȸ�� ��ü��ȸ		7 ���� �뿩"	);
			System.out.println("2 �����߰��ϱ�		6 ȸ�� �ܰ���ȸ		8 ���� �ݳ�"	);
			System.out.println("3 ���������ϱ�		 						"	);
			System.out.println("4 ���������ϱ�								"	);	
			System.out.println("9 ��������");
			System.out.println("10�α׾ƿ�");
			System.out.print("��ȣ : ");
			int num = sc.nextInt();
			switch(num)
			{
			case 1:
				Map<String, Object> result1 = controller.execute("/book", 1, null);
				List<BookDto> list = (List<BookDto>)result1.get("result");
				list.stream().forEach((dto)->{System.out.println(dto);});
				break;
			case 2:
				System.out.print("�����ڵ� ������ ���ǻ� ISBN �Է� : ");
			int bookcode = sc.nextInt();
			String bookname = sc.next();
			String publisher = sc.next();
			String isbn = sc.next();
			
			Map<String,Object> param = new HashMap();
			param.put("bookcode", bookcode);
			param.put("bookname", bookname);
			param.put("publisher", publisher);
			param.put("isbn", isbn);
			param.put("sid", sid);
			Map<String, Object> result2 = controller.execute("/book", 2, param);
			Boolean isInsert = (Boolean)result2.get("result1");
			if(isInsert==true)
				System.out.println("[INFO] ���� ��� �Ϸ�!");
			break;
			case 3:
				break;
			
		
			case 7 :
				//�뿩�ϱ�
				int req_bookcode = sc.nextInt();
				String userid = sc.next();
				Map<String , Object> lend_param = new HashMap();
				lend_param.put("bookcode", req_bookcode);
				lend_param.put("id", userid);
				lend_param.put("sid", sid);
				Map<String,Object> result7 = controller.execute("/lend", 2 , lend_param);
				Boolean isLend =(Boolean)result7.get("result");
				if(isLend){
					System.out.println("[INFO] �뿩�Ϸ�!");
				}
				return;	
			case 10 : 
				//�α׾ƿ�

				return ;
			}
			
		}
	}

	public void ȸ��Menu() {
		while (true) {
			System.out.println("--------------------------");
			System.out.println("ȸ���޴�");
			System.out.println("--------------------------");
			System.out.println("[����]			[ȸ��]			[�뿩]");
			System.out.println("1 ������ȸ�ϱ�		5 ���� ������ȸ		8 �뿩 ���� ��ȸ"	);
			System.out.println("2 �����߰��ϱ�		6 ���� ��������		9 �뿩 ���� ����"	);
			System.out.println("3 ���������ϱ�		7 ȸ�� Ż��					"	);
			System.out.println("4 ���������ϱ�									"	);
			
			System.out.println("10 ��������");
			System.out.println("11 �α׾ƿ�");
			System.out.print("��ȣ : ");
			int num = sc.nextInt();
			switch(num)
			{
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			
			case 5 : 
				return ;
				
			}
		}
	}

	public void BookMenu() {
		System.out.println("--------------------------");
		System.out.println("���� ���� �Է�");
		System.out.println("--------------------------");
		System.out.printf("�����ڵ� ������ ���ǻ� ISBN������ �Է� : " );
		int bookcode = sc.nextInt();
		String bookname = sc.next();
		String publisher = sc.next();
		String isbn = sc.next();
		
		//��Ʈ�ѷ� ȣ��!
		
	}

	public void MemberMenu() {
		System.out.println("--------------------------");
		System.out.println("ȸ�� ���� �Է�");
		System.out.println("--------------------------");
		System.out.printf("ID PW USERNAME ROLE ������ �Է� : " );
		String id = sc.next();
		String pw = sc.next();
		String username = sc.next();
		String role = sc.next();
		
	}

	public void LendMenu() {
		System.out.println("--------------------------");
		System.out.println("�뿩 ���� �Է�");
		System.out.println("--------------------------");
		
		System.out.printf("BOOKCODE USERID �Է� : " );
		int bookcode = sc.nextInt();
		String id = sc.next();
		
	}

}