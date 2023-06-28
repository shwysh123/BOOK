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
			// 1 파라미터 추출(생략)
			// 2 입력값 검증(생략)
			// 3 서비스 실행(서비스모듈작업 이후 처리)
			List<BookDto> list=null;
			try {
				list =  service.getAllBook();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 4 View로 전달
			System.out.println("Book_Select Block!");
			Map<String,Object> result = new HashMap();
			result.put("result", list);
			return result;
			
		} else if (serviceNo == 2) {
			// 1 파라미터 추출
			Integer bookcode = (Integer) param.get("bookcode");
			String bookname = (String) param.get("bookname");
			String publisher = (String) param.get("publisher");
			String isbn = (String) param.get("isbn");
			String sid = (String) param.get("sid");
			
			// 2 (데이터)입력값 검증
			if (bookcode == null || bookname == null || publisher == null || isbn == null) {
				System.out.println("[ERROR] Data Validation Check Error!");
				return null; // 함수종료
			}
			// 3 서비스 실행
			BookDto dto = new BookDto(bookcode,bookname,publisher,isbn);
			System.out.println("Dto : "+dto);
			
			
			Boolean rValue=false;
			try {
				rValue = service.addBook(dto,sid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 4 View로 전달
			System.out.println("Book_Insert Block!");
			Map<String,Object> result = new HashMap();
			result.put("result", rValue);
			return result;
			
		} else if (serviceNo == 3) {
			// 1 파라미터 추출
			Integer bookcode = (Integer) param.get("bookcode");
			String bookname = (String) param.get("bookname");
			String publisher = (String) param.get("publisher");
			String isbn = (String) param.get("isbn");
			String sid = (String) param.get("sid");
			
			// 2 (데이터)입력값 검증
			if (bookcode == null || bookname == null || publisher == null || isbn == null) {
				System.out.println("[ERROR] Data Validation Check Error!");
				return null; // 함수종료
			}
			// 3 서비스 실행
			BookDto dto = new BookDto(bookcode,bookname,publisher,isbn);
			System.out.println("Dto : "+dto);
			
			Boolean rValue=false;
			try {
				rValue = service.updateBook(dto, sid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 4 View로 전달
			System.out.println("Book_Update Block!");
			Map<String,Object> result = new HashMap();
			result.put("result", rValue);
			return result;
			
		} else if (serviceNo == 4) {
			// 1 파라미터 추출
			Integer bookcode = (Integer) param.get("bookcode");
			String sid = (String) param.get("sid");
			
			// 2 (데이터)입력값 검증
			if (bookcode == null) {
				System.out.println("[ERROR] Data Validation Check Error!");
				return null; // 함수종료
			}

			// 3 서비스 실행	
			Boolean rValue=false;
			try {	
				rValue = service.removeBook(bookcode, sid);
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			// 4 View로 전달
			System.out.println("Book_Delete Block!");
			Map<String,Object> result = new HashMap();
			result.put("result", rValue);
			return result;
		}
		
		
		return null;

	}

}