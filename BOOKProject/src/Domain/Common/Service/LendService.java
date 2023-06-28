package Domain.Common.Service;

import Domain.Common.Dao.LendDao;
import Domain.Common.Dto.BookDto;
import Domain.Common.Dto.LendDto;
import Domain.Common.Dto.MemberDto;

public class LendService {
	
	private MemberService memService;
	private BookService bookService;
	private LendDao dao;
	
	
	//�̱���
	private static LendService instance;
	public static LendService getInstance() {
		if(instance==null)
			instance = new LendService();
		return instance;
	}
	//
	
	public LendService(){
		dao=LendDao.getInstance();
		memService = MemberService.getInstance();
		bookService = BookService.getInstance();
	}
	
	//�ܺηκ��� Service�ޱ�
	public void setMemberService(MemberService memService) {
		this.memService = memService;
	}
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	
	public boolean reqLend(String sid,String userid,int bookcode) throws Exception {
		
		//�缭�α���Ȯ��,Role�ޱ�
		String role = memService.getRole(sid);
		 
		if(!role.equals("ROLE_MEMBER")) {
			System.out.println("[WARN] �缭�� �α��� �� �� �ֽ��ϴ�.");
			return false;
		}
		//ȸ���������� Ȯ��
		MemberDto dto = memService.memberSearchOne(role, userid);
		
		if(dto!=null)
		{
			//�������� ���� Ȯ��
			BookDto bdto= bookService.getBook(bookcode);
			if(bdto!=null) {
				//������ �ִٸ� �뿩���� �������� Ȯ��(LendDao�̿��ؼ� �뿩���� å���ִ��� ��ȸ)
				LendDto ldto=dao.select(bookcode);
				 		
				if(ldto==null) {
					//������ �뿩������ ���¶��
					dao.insert(new LendDto(0,bookcode,userid,null,null) );
					System.out.println("[INFO] �����뿩 �Ϸ�Ǿ����ϴ�.");
					return true;
				}
				System.out.println("[WARN] ��û�� ������ �뿩���Դϴ�.");	
				return false;
			}
			System.out.println("[WARN] �ش� ������ �������� �ʽ��ϴ�.");
			return false;
		}
		System.out.println("[WARN] �ش� ȸ���� �������� �ʽ��ϴ�.");
		return false;
	}
	
	
	
	

}