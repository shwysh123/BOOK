package Controller;

import java.util.HashMap;
import java.util.Map;

import Domain.Common.Dto.LendDto;
import Domain.Common.Service.LendService;

public class LendController {

		LendService service;
		
		public LendController(){
			service = LendService.getInstance();
		}
	// 1 Select , 2 Insert , 3 Update , 4 Delete , 5 ȸ���뿩�����ȸ
	public Map<String,Object> execute(int serviceNo, Map<String, Object> param) {

		if (serviceNo == 1) {
			// 1 �Ķ���� ����(����)
			String sid = (String)param.get("sid");
			// 2 �Է°� ����(����)
			if(sid==null) {
				System.out.println("[ERROR] Data Validation Check...");
				return null;
			}
			// 3 ���� ����(���񽺸���۾� ���� ó��)
			// service.getAllLend(sid);
			// 4 View�� ����
			System.out.println("Lend_Select Block!");
		} else if (serviceNo == 2) {
			// 1 �Ķ���� ����
			Integer bookcode = (Integer) param.get("bookcode");
			String id = (String) param.get("id");
			String sid = (String) param.get("sid");
			// 2 �Է°� ����
			if(bookcode==null||id==null||sid==null) {
				System.out.println("[ERROR]Data Validation Check Error");
				return null;
			}
			// 3 ���� ����
			LendDto dto = new LendDto(0,bookcode,id,null,null);
			System.out.println("Dto : " + dto);
			Boolean rValue = false;
			try {
				rValue = service.reqLend(sid, id, bookcode);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 4 View�� ����
			System.out.println("Lend_Insert Block!");
			Map<String,Object> result = new HashMap();
			result.put("result", rValue);
			return result;
		} else if (serviceNo == 3) {
			// 1 �Ķ���� ����
			// 2 �Է°� ����
			// 3 ���� ����
			// 4 View�� ����
			System.out.println("Lend_Update Block!");
		} else if (serviceNo == 4) {
			// 1 �Ķ���� ����
			Integer bookcode = (Integer) param.get("bookcode");
			String id = (String) param.get("id");

			// 2 �Է°� ����
			if(bookcode==null||id==null) {
				System.out.println("[ERROR]Data Validation Check Error");
				return null;
			}
			// 3 ���� ����
			LendDto dto = new LendDto(0,bookcode,id,null,null);
			System.out.println("Dto : " + dto);
			// 4 View�� ����
			System.out.println("Lend_Delete Block!");
		}
			return null;
	}

}
