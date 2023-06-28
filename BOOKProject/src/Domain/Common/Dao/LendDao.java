package Domain.Common.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Domain.Common.Dto.LendDto;
import Domain.Common.Dto.MemberDto;

public class LendDao {
	private String id;
	private String pw;
	private String url;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static LendDao instance;
	public static LendDao getInstance() {
		if(instance==null)
			instance=new LendDao();
		return instance;
	}
	
	public LendDao(){
		id="root";
		pw="1234";
		url="jdbc:mysql://localhost:3306/bookdb";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url,id,pw);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//CRUD
	public int insert(LendDto dto) throws Exception{
		
		pstmt=conn.prepareStatement("insert into tbl_lend values(null,?,?,curdate(),DATE_ADD(CURDATE(), INTERVAL 7 DAY))");
		pstmt.setInt(1, dto.getBookcode());
		pstmt.setString(2,dto.getId());
		int result=pstmt.executeUpdate();
		pstmt.close();
		
		return result;
		
	}
	
	public List<LendDto> select() throws Exception{
		List<LendDto> list = new ArrayList();
		LendDto dto=null;
		pstmt=conn.prepareStatement("select * from tbl_lend");
		rs=pstmt.executeQuery();
		if(rs!=null)
		{
			while(rs.next()) {
				dto=new LendDto();
				dto.setLendId(rs.getInt("lendId"));
				dto.setBookcode(rs.getInt("bookcode"));
				dto.setId(rs.getString("id"));
				dto.setLendDate(rs.getDate("lendDate"));
				dto.setReturnDate(rs.getDate("returnDate"));
				list.add(dto);
			}
			rs.close();
		}
		
		pstmt.close();
			
		return list;
	}
	
	
	public LendDto select(int bookcode) throws Exception{
		
		LendDto dto=null;
		pstmt=conn.prepareStatement("select * from tbl_lend where bookcode=?");
		pstmt.setInt(1, bookcode);
		rs=pstmt.executeQuery();
		if(rs!=null && rs.isBeforeFirst())
		{
			rs.next();
			dto=new LendDto();
			dto.setLendId(rs.getInt("lendId"));
			dto.setBookcode(rs.getInt("bookcode"));
			dto.setId(rs.getString("id"));
			dto.setLendDate(rs.getDate("lendDate"));
			dto.setReturnDate(rs.getDate("returnDate"));	 		
			rs.close();
		}
		pstmt.close();
			
		return dto;
	}	
	
	
//	public List<BookDto> select(String keyword){
//		return null;
//	}
//	public List<BookDto> select(String keyfield,String keyword){
//		return null;
//	}
	
	public int update(MemberDto dto) throws Exception{
		pstmt=conn.prepareStatement("update tbl_member set pw=?,username=?,role=? where id=?");
		pstmt.setString(1, dto.getPw());
		pstmt.setString(2, dto.getUsername());
		pstmt.setString(3,dto.getRole());
		pstmt.setString(4, dto.getId());
		int result=pstmt.executeUpdate();
		pstmt.close();
		
		return result;
		
	}
	public int delete(String id)  throws Exception{
		pstmt=conn.prepareStatement("delete from tbl_member where id=?");
		pstmt.setString(1, id);	
		int result=pstmt.executeUpdate();
		pstmt.close();
		
		return result;
		
	}
	
	
	
	
	
}
