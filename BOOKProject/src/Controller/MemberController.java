package Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.Common.Dto.MemberDto;
import Domain.Common.Service.MemberService;

public class MemberController {
	
	private MemberService service;
	
	public MemberController(){
		service = MemberService.getInstance();
	}
	
	
	//[CRUD                                      ]
	//[ 1 Select , 2 Insert , 3 Update , 4 Delete] 5 �α���, 6 �α׾ƿ�
	public Map<String,Object> execute(int serviceNo, Map<String, Object> param) {

		if (serviceNo == 1) {
			// 1 �Ķ���� ����(����)
			String sid = (String)param.get("sid");
			// 2 �Է°� ����(����)
			// 3 ���� ����(���񽺸���۾� ���� ó��)
			List<MemberDto> list = null;
			try {
				list = service.memberSearch(sid);
			}catch(Exception e){
				e.printStackTrace();
			}
			// 4 View�� ����
			System.out.println("Member_Select Block!");
			Map<String,Object> result = new HashMap();
			result.put("result",list);
			return result;
		} else if (serviceNo == 2) {
			// 1 �Ķ���� ����
			String id = (String) param.get("id");
			String pw = (String) param.get("pw");
			String username = (String) param.get("username");
			String role = (String) param.get("role");
			// 2 �Է°� ����
			if (id == null || pw == null || username == null || role == null) {
				System.out.println("[ERROR] Data Validation Check Error!");
				return null;
			}
			// 3 ���� ����
			MemberDto dto = new MemberDto(id, pw, username, role);
			System.out.println("Dto : " + dto);
			Boolean rValue = false;
			try {

			}catch(Exception e) {
				e.printStackTrace();
			}
			// 4 View�� ����
			System.out.println("Member_Insert Block!");
			Map<String,Object> result = new HashMap();
			result.put("result", rValue);
			return result;
		} else if (serviceNo == 3) {
			// 1 �Ķ���� ����
			String id = (String) param.get("id");
			String pw = (String) param.get("pw");
			String username = (String) param.get("username");
			String role = (String) param.get("role");
			String sid = (String)param.get("sid");
			// 2 �Է°� ����
			if (id == null || pw == null || username == null || role == null) {
				System.out.println("[ERROR] Data Validation Check Error!");
				return null;
			}
			// 3 ���� ����
			MemberDto dto = new MemberDto(id, pw, username, role);
			System.out.println("Dto : " + dto);
			Boolean rValue = false;
		try {
			rValue = service.memberUpdate(dto, sid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			// 4 View�� ����
			System.out.println("Member_Update Block!");
			Map<String,Object> result = new HashMap();
			result.put("result", rValue);
			return result;
		} else if (serviceNo == 4) {
			// 1 �Ķ���� ����
			String id = (String) param.get("id");
			String pw = (String) param.get("pw");
			String sid = (String)param.get("sid");
			// 2 �Է°� ����
			if (id == null || pw == null) {
				System.out.println("[ERROR] Data Validation Check Error!");
				return null;
			}
			// 3 ���� ����
			Boolean rValue = false;
			try {
				rValue = service.memberDelete(id, sid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 4 View�� ����
			System.out.println("Member_Delete Block!");
			Map<String,Object> result = new HashMap();
			result.put("result", rValue);
			return result;
		}else if(serviceNo == 5) {
			//1 �Ķ���� ����
			String id = (String) param.get("id");
			String pw = (String) param.get("pw");
			//2 �Է°� ����
			if (id == null || pw == null) {
				System.out.println("[ERROR] Data Validation Check Error!");
				return null;
			}
			//3 ���� ����
//			MemberDto dto = new MemberDto(id,pw,null,null);
			Map<String,Object> result = new HashMap();
			String sid = null;
			 try {
				result = service.login(id, pw);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//4 View�� ����
			return result;
		}else if(serviceNo == 6) {
			//1 �Ķ���� ����
			//2 �Է°� ����
			//3 ���� ����
			//4 View�� ����
		}
		return null;

	}
}
