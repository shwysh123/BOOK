package Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.Common.Dto.BookDto;
import Domain.Common.Service.BookService;

public class BookController {

	private BookService service;
	
	public BookController(){
		service = BookService.getInstance();
	}
	
	// 1 Select , 2 Insert , 3 Update , 4 Delete
	public Map<String,Object> execute(int serviceNo, Map<String, Object> param) {

		if (serviceNo == 1) {
			// 1 �Ķ���� ����(����)
			// 2 �Է°� ����(����)
			// 3 ���� ����(���񽺸���۾� ���� ó��)
			List<BookDto> list=null;
			try {
				list =  service.getAllBook();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 4 View�� ����
			System.out.println("Book_Select Block!");
			Map<String,Object> result = new HashMap();
			result.put("result", list);
			return result;
			
		} else if (serviceNo == 2) {
			// 1 �Ķ���� ����
			Integer bookcode = (Integer) param.get("bookcode");
			String bookname = (String) param.get("bookname");
			String publisher = (String) param.get("publisher");
			String isbn = (String) param.get("isbn");
			String sid = (String) param.get("sid");
			
			// 2 (������)�Է°� ����
			if (bookcode == null || bookname == null || publisher == null || isbn == null) {
				System.out.println("[ERROR] Data Validation Check Error!");
				return null; // �Լ�����
			}
			// 3 ���� ����
			BookDto dto = new BookDto(bookcode,bookname,publisher,isbn);
			System.out.println("Dto : "+dto);
			
			
			Boolean rValue=false;
			try {
				rValue = service.addBook(dto,sid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 4 View�� ����
			System.out.println("Book_Insert Block!");
			Map<String,Object> result = new HashMap();
			result.put("result", rValue);
			return result;
			
		} else if (serviceNo == 3) {
			// 1 �Ķ���� ����
			Integer bookcode = (Integer) param.get("bookcode");
			String bookname = (String) param.get("bookname");
			String publisher = (String) param.get("publisher");
			String isbn = (String) param.get("isbn");
			String sid = (String) param.get("sid");
			
			// 2 (������)�Է°� ����
			if (bookcode == null || bookname == null || publisher == null || isbn == null) {
				System.out.println("[ERROR] Data Validation Check Error!");
				return null; // �Լ�����
			}
			// 3 ���� ����
			BookDto dto = new BookDto(bookcode,bookname,publisher,isbn);
			System.out.println("Dto : "+dto);
			
			Boolean rValue=false;
			try {
				rValue = service.updateBook(dto, sid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 4 View�� ����
			System.out.println("Book_Update Block!");
			Map<String,Object> result = new HashMap();
			result.put("result", rValue);
			return result;
			
		} else if (serviceNo == 4) {
			// 1 �Ķ���� ����
			Integer bookcode = (Integer) param.get("bookcode");
			String sid = (String) param.get("sid");
			
			// 2 (������)�Է°� ����
			if (bookcode == null) {
				System.out.println("[ERROR] Data Validation Check Error!");
				return null; // �Լ�����
			}

			// 3 ���� ����	
			Boolean rValue=false;
			try {	
				rValue = service.removeBook(bookcode, sid);
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			// 4 View�� ����
			System.out.println("Book_Delete Block!");
			Map<String,Object> result = new HashMap();
			result.put("result", rValue);
			return result;
		}
		
		
		return null;

	}

}