package Domain.Common.Dto;

import java.util.Date;


public class LendDto {
	private int lendId;			//Auto_Increment
	private int bookcode;
	private String id;			//userid
	private Date lendDate;	
	private Date returnDate;	//7���� �ݳ������� ���
	
	//����Ʈ������
	public LendDto() {}
	//ToString
	@Override
	public String toString() {
		return "LendDto [lendId=" + lendId + ", bookcode=" + bookcode + ", id=" + id + ", lendDate=" + lendDate
				+ ", returnDate=" + returnDate + "]";
	}
	//���ڹ޴� ������
	public LendDto(int lendId, int bookcode, String id, Date lendDate, Date returnDate) {
		super();
		this.lendId = lendId;
		this.bookcode = bookcode;
		this.id = id;
		this.lendDate = lendDate;
		this.returnDate = returnDate;
	}
	//Getter and Setter
	public int getLendId() {
		return lendId;
	}
	public void setLendId(int lendId) {
		this.lendId = lendId;
	}
	public int getBookcode() {
		return bookcode;
	}
	public void setBookcode(int bookcode) {
		this.bookcode = bookcode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getLendDate() {
		return lendDate;
	}
	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}


	
	
	
}