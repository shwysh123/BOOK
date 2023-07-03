package Controller;

import java.util.HashMap;
import java.util.Map;

public class FrontController {

	private Map<String,Object> map = new HashMap();
	//�ʱⰪ ����
	//����ڿ䱸����API Controller�� �°� ���� 
	// /req_bookinfo������ �䱸������ BookController ����
	
	private void init() {
		//�������� �䱸����-Controller Mapping()
		// /RequestUri ServiceNo Param
		map.put("/book", new BookController());		
		map.put("/member", new MemberController());
		map.put("/lend", new LendController());
		
	}
	
	public FrontController(){
		init();
	}
	
	//request�� �´� Controller�� ����,�ش���Ʈ�ѷ� ����
	//request , ServiceNo(1 select,2 insert , 3 update , 4 delete) , param
	public Map<String,Object> execute(String request,int ServiceNo ,Map<String,Object> param) {
		  Object controller =map.get(request);
		  
		  Map<String,Object> result = new HashMap();
		  
		  if(controller instanceof BookController) {
			  
			  BookController down=(BookController)controller;
			  result=down.execute(ServiceNo,param);
			  System.out.println("BOOKCONTROLLER !");
			  
			  
		  }else if(controller instanceof LendController) {
			  
			  LendController down=(LendController)controller;
			result = down.execute(ServiceNo,param);
			 
			  System.out.println("LendController !");
		  }else if(controller instanceof MemberController) {
			 
			  MemberController down=(MemberController)controller;
			 result = down.execute(ServiceNo,param);
			  
			  System.out.println("MemberController !");
			  
		  }else {
			  System.out.println("Request ERROR");
		  }
		
		  return result;
	}
	
	
}
