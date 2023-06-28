package Controller;

import java.util.Map;

import Domain.Common.Dto.LendDto;

public class LendController {

	// 1 Select , 2 Insert , 3 Update , 4 Delete
	public void execute(int serviceNo, Map<String, Object> param) {

		if (serviceNo == 1) {
			// 1 �Ķ���� ����(����)
			// 2 �Է°� ����(����)
			// 3 ���� ����(���񽺸���۾� ���� ó��)
			// 4 View�� ����
			System.out.println("Lend_Select Block!");
		} else if (serviceNo == 2) {
			// 1 �Ķ���� ����
			Integer bookcode = (Integer) param.get("bookcode");
			String id = (String) param.get("id");

			// 2 �Է°� ����
			if(bookcode==null||id==null) {
				System.out.println("[ERROR]Data Validation Check Error");
				return ;
			}
			// 3 ���� ����
			LendDto dto = new LendDto(0,bookcode,id,null,null);
			System.out.println("Dto : " + dto);
			// 4 View�� ����
			System.out.println("Lend_Insert Block!");
			
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
				return ;
			}
			// 3 ���� ����
			LendDto dto = new LendDto(0,bookcode,id,null,null);
			System.out.println("Dto : " + dto);
			// 4 View�� ����
			System.out.println("Lend_Delete Block!");
		}

	}

}
