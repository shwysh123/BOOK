package Domain.Common.Service;

import java.util.List;

import Domain.Common.Dao.BookDao;
import Domain.Common.Dto.BookDto;

public class BookService {

	
	
	private BookDao dao;

	//�̱���
	private static BookService instance;
	public static BookService getInstance() {
		if(instance==null)
			instance = new BookService();
		return instance;
	}
	//
	
	//
	private MemberService memberService;
	public BookService() {
		dao = BookDao.getInstance();
		memberService = MemberService.getInstance();
	}

	// ������ȸ�ϱ�(��ȸ��/ȸ��/�缭)
	public List<BookDto> getAllBook() throws Exception {
		System.out.println("BookService's getAllBook()");
		return dao.select();
	}
	
	public BookDto getBook(int bookcode) throws Exception {
		System.out.println("BookService's getBook()");
		return dao.select(bookcode);
	}
	
	
	// ��������ϱ�(�缭)
	public boolean addBook(BookDto dto, String sid) throws Exception {
		System.out.println("BookService's addBook()");
		
		String role = memberService.getRole(sid);
		
		if (role.equals("ROLE_MEMBER")) {
			int result = dao.insert(dto);
			if (result > 0)
				return true;
		}

		return false;
	}

	// ���������ϱ�
	public boolean updateBook(BookDto dto, String sid) throws Exception {
		
		System.out.println("BookService's updateBook()");
		String role = memberService.getRole(sid);
		
		if (role.equals("ROLE_MEMBER")) {
			int result = dao.update(dto);
			if (result > 0)
				return true;
		}

		return false;
	}

	// ���������ϱ�
	public boolean removeBook(int bookcode, String sid) throws Exception {
		System.out.println("BookService's removeBook()");
		
		String role = memberService.getRole(sid);
		
		if (role.equals("ROLE_MEMBER")) {
			int result = dao.delete(bookcode);
			if (result > 0)
				return true;
		}
		return false;
	}
}