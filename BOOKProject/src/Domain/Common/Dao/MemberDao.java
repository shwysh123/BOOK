package Domain.Common.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Domain.Common.Dto.MemberDto;

public class MemberDao {
	private String id;
	private String pw;
	private String url;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//½Ì±ÛÅæ
	private static MemberDao instance;
	public static MemberDao getInstance() {
		if(instance==null)
			instance=new MemberDao();
		return instance;
	}
	//
	public MemberDao(){
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
	public int insert(MemberDto dto) throws Exception{
		
		pstmt=conn.prepareStatement("insert into tbl_member values(?,?,?,?)");
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getPw());
		pstmt.setString(3,dto.getUsername());
		pstmt.setString(4, dto.getRole());
		int result=pstmt.executeUpdate();
		pstmt.close();
		
		return result;
		
	}
	
	public List<MemberDto> select() throws Exception{
		List<MemberDto> list = new ArrayList();
		MemberDto dto=null;
		pstmt=conn.prepareStatement("select * from tbl_member");
		rs=pstmt.executeQuery();
		if(rs!=null)
		{
			while(rs.next()) {
				dto=new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPw(null);
				dto.setUsername(rs.getString("username"));
				dto.setRole(rs.getString("role"));
				list.add(dto);
			}
			rs.close();
		}
		pstmt.close();
			
		return list;
	}
	public MemberDto select(String id) throws Exception{
		
		MemberDto dto=null;
		pstmt=conn.prepareStatement("select * from tbl_member where id=?");
		pstmt.setString(1, id);
		
		rs=pstmt.executeQuery();
		
		try {
		if(rs!=null&&rs.isBeforeFirst())
		{
			rs.next();
			dto=new MemberDto();
			dto.setId(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
			dto.setUsername(rs.getString("username"));
			dto.setRole(rs.getString("role"));	
			rs.close();
		}
		}catch(Exception e) {
			throw new Exception("ÀÌ°Å rs ¾ø´Â°Å´Ù..");
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