package Domain.Common.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import Domain.Common.Dao.MemberDao;
import Domain.Common.Dto.MemberDto;
import Domain.Common.Service.Auth.Session;




public class MemberService {

	
	//������������
	public Map<String,Session> sessionMap;
	
	private MemberDao dao;
	
	
	
	//�̱���
	private static MemberService instance;
	public static MemberService getInstance() {
		if(instance==null)
			instance = new MemberService();
		return instance;
	}
	//
	
	public MemberService() {
		dao=MemberDao.getInstance();
		sessionMap=new HashMap();
	}
	
	//ȸ�� �����ϱ�
	public boolean memberJoin(MemberDto dto) throws Exception {
		int result = dao.insert(dto);
		if(result>0)
			return true;
		return false;
	}
	
	//ȸ�� ��ȸ�ϱ�(��ü) - �缭
	public List<MemberDto> memberSearch(String sid) throws Exception{
		
		String role = this.getRole(sid);
		
		if(role.equals("ROLE_MEMBER"))		
			return dao.select();
		return null;
	}
	//ȸ�� ��ȸ�ϱ�(�Ѹ�) - �缭
	public MemberDto memberSearchOne(String role,String id) throws Exception{
		if(role.equals("ROLE_MEMBER"))		
			return dao.select(id);
		return null;
	}	
	
	
	//ȸ�� ��ȸ�ϱ�(�� ȸ��) - �α����� ȸ���� 
	public MemberDto memberSearch(String id,String sid) throws Exception {
		Session session = sessionMap.get(sid);
		
		if(session!=null && session.getId().equals(id))
			return dao.select(id);
		
		return null;
	}
	
	
	//ȸ�� �����ϱ� -- ����Ȯ��
	public boolean memberUpdate(MemberDto dto,String sid) throws Exception{
		
		Session session = sessionMap.get(sid);
		if(session!=null && session.getId().equals(dto.getId()))
		{
			int result = dao.update(dto);
			if(result>0)
				return true;
		}
		
		
		return false;
	}	
	
	//ȸ�� �����ϱ�
	public boolean memberDelete(String id,String sid) throws Exception{
		
		Session session = sessionMap.get(sid);
		if(session!=null && session.getId().equals(id))
		{
			int result = dao.delete(id);
			if(result>0)
				return true;
		}

		return false;
	}
	
	
	//�α���
	public Map<String,Object> login(String id, String pw) throws Exception{
		//1 ID/PW üũ ->Dao ���޹��� id�� ��ġ�ϴ� ������ �����ͼ� Pw��ġ Ȯ��
		MemberDto dbDto = dao.select(id);
		if(dbDto==null) {
			System.out.println("[ERROR] Login Fail... ���̵� ��ġ���� �ʽ��ϴ�");
			return null;
		}
		if(!pw.equals(dbDto.getPw())) {
			System.out.println("[ERROR] Login Fail... �н����尡 ��ġ���� �ʽ��ϴ�");
			return null;
		}
		//2 ����ڿ����� ����(Session)�� MemberService�� ����
		String sid=UUID.randomUUID().toString();
		Session session = new Session(sid,dbDto.getId(),dbDto.getRole());
		sessionMap.put(sid, session);
		
		//3 ���ǿ� ���������� Ŭ���̾�Ʈ�� �����Ҽ� �ֵ����ϴ� ���Ǳ���Id(Session Cookie) ����
		Map<String,Object> result = new HashMap();
		result.put("sid", sid);
		result.put("role", dbDto.getRole());
		return result;
	}
	
	//�α׾ƿ�
	public boolean logout(String id,String sid) {
		Session session =  sessionMap.get(sid);
		
		if( ! session.getId().equals(id) ) {
			System.out.println("[ERROR] ID�� ��ġ���� �ʽ��ϴ�.");
			return false;
		}
		sessionMap.remove(sid);
		return true;
	}
	
	//���ҹ�ȯ�Լ� 
	public String getRole(String sid) {
		Session session = sessionMap.get(sid);
		System.out.println("getRole's Session : " + session);
		if(session!=null)
			return session.getRole();
		
		return null;
	}
	
	
	
	
}