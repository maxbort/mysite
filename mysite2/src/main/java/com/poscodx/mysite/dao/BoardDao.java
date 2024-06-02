package com.poscodx.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscodx.mysite.vo.BoardVo;

public class BoardDao {
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.0.198:3306/webdb?charset=utf8";
//			String url = "jdbc:mariadb://192.168.30.207:3306/webdb?charset=utf8";

//			String url = "jdbc:mariadb://192.168.219.104:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: "+ e);
		}
		
		return conn;
	}
	
	public int insert(BoardVo vo) {
		int result = 0;
		
		try(
				Connection conn = getConnection();
				PreparedStatement pstmt1 = 
						conn.prepareStatement("insert into board(title, contents, reg_date, g_no, o_no, depth, user_no)" +
												"values(?,?,now(),(select ifnull(max(g_no), 0) + 1 from board a),1,0,?)");
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
				)
		
		
		{
			
			
			pstmt1.setString(1, vo.getTitle());
			pstmt1.setString(2, vo.getContents());
			pstmt1.setLong(3, vo.getUser_no());
			
			result = pstmt1.executeUpdate();
			
			ResultSet rs = pstmt2.executeQuery();
			vo.setNo(rs.next() ? rs.getLong(1):null);
			rs.close();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}
	
	public List<BoardVo> findAll(String kwd) {
		List<BoardVo> result = new ArrayList<>();
		
		if(kwd != "") {

			try(
					Connection conn = getConnection();	
					PreparedStatement pstmt = conn.prepareStatement(
							" select a.no, a.title, a.hit, a.reg_date,a.user_no, a.g_no, a.o_no, a.depth, b.name from board a join user b"+
								" where a.user_no = b.no and a.title like ? or b.name like ? order by  g_no desc, o_no asc");
					
			){
				pstmt.setString(1, "%" + kwd + "%");
				pstmt.setString(2, "%" + kwd + "%");
				ResultSet rs = pstmt.executeQuery();
	
				//6. 결과 처리
				while(rs.next()) {
					Long no = rs.getLong(1);
					String title = rs.getString(2);
					Integer hit = rs.getInt(3);
					String reg_date = rs.getString(4);
					Long user_no = rs.getLong(5);
					Integer g_no = rs.getInt(6);
					Integer o_no = rs.getInt(7);
					Integer depth = rs.getInt(8);
					String user_name = rs.getString(9);
					
					BoardVo vo = new BoardVo();
					vo.setNo(no);
					vo.setTitle(title);
					vo.setHit(hit);
					vo.setReg_date(reg_date);
					vo.setUser_no(user_no);
					vo.setUser_name(user_name);
					vo.setG_no(g_no);
					vo.setO_no(o_no);
					vo.setDepth(depth);
					
					result.add(vo);
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			
			return result;
		}
		else {
			try(
					Connection conn = getConnection();	
					PreparedStatement pstmt = conn.prepareStatement(
							" select a.no, a.title, a.hit, a.reg_date,a.user_no, a.g_no, a.o_no, a.depth, b.name from board a join user b"+
								" where a.user_no = b.no order by  g_no desc, o_no asc");
					
			){

				ResultSet rs = pstmt.executeQuery();
	
				//6. 결과 처리
				while(rs.next()) {
					Long no = rs.getLong(1);
					String title = rs.getString(2);
					Integer hit = rs.getInt(3);
					String reg_date = rs.getString(4);
					Long user_no = rs.getLong(5);
					Integer g_no = rs.getInt(6);
					Integer o_no = rs.getInt(7);
					Integer depth = rs.getInt(8);
					String user_name = rs.getString(9);
					
					BoardVo vo = new BoardVo();
					vo.setNo(no);
					vo.setTitle(title);
					vo.setHit(hit);
					vo.setReg_date(reg_date);
					vo.setUser_no(user_no);
					vo.setUser_name(user_name);
					vo.setG_no(g_no);
					vo.setO_no(o_no);
					vo.setDepth(depth);
					
					result.add(vo);
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			
			return result;
		}
	}
	
	
	public BoardVo findView(Long no) {
		BoardVo result = new BoardVo();
		try(
				Connection conn = getConnection();	
				PreparedStatement pstmt = conn.prepareStatement(
						" select no, title, contents, user_no, g_no, o_no, depth from board where no = ?");
				
				
		) {//6. 결과 처리
		
			pstmt.setLong(1, no);
	        try (ResultSet rs = pstmt.executeQuery()) { // ResultSet을 try-with-resources로 처리
	            if (rs.next()) { // 포인터를 첫 번째 행으로 이동
	                Long no1 = rs.getLong(1);
	                Long user_no = rs.getLong(4);
	                String title = rs.getString(2);
	                String contents = rs.getString(3);
	                int g_no = rs.getInt(5);
	                int o_no = rs.getInt(6);
	                int depth = rs.getInt(7);
	                
	                result.setNo(no1);
	                result.setTitle(title);
	                result.setContents(contents);
	                result.setUser_no(user_no);
	                result.setG_no(g_no);
	                result.setO_no(o_no);
	                result.setDepth(depth);
	            } else {
	                System.out.println("No data found for no: " + no);
	            }
	        }
				
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}

	public int deleteByNo(Long no, Long no1) {
		int result = 0;
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from board where no = ? and user_no = ?");) {
			pstmt.setLong(1, no);
			pstmt.setLong(2, no1);
			System.out.println(no);
			System.out.println(no1);
			result = pstmt.executeUpdate();
			pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		}
		return result;
	}
	
	public int update(BoardVo vo) {
		int result = 0;

		try (Connection conn = getConnection();
				PreparedStatement pstmt1 = conn
						.prepareStatement("update board set title = ?, contents = ? where no = ? and user_no = ?");
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");) {
			pstmt1.setString(1, vo.getTitle());
			pstmt1.setString(2, vo.getContents());
			pstmt1.setLong(3, vo.getNo());
			pstmt1.setLong(4, vo.getUser_no());

			result = pstmt1.executeUpdate();

			ResultSet rs = pstmt2.executeQuery();
			vo.setNo(rs.next() ? rs.getLong(1) : null);
			rs.close();
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		}

		return result;
	}
	
	public int hitUp(BoardVo vo) {
		int result = 0;

		try (Connection conn = getConnection();
				PreparedStatement pstmt1 = conn.prepareStatement("update board set hit = hit + 1 where no = ?");) 
		{
			pstmt1.setLong(1, vo.getNo());
			result = pstmt1.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		}

		return result;
	}
	
	public int reply(BoardVo vo) {
		int result = 0;
		
		try(
				Connection conn = getConnection();
				PreparedStatement pstmt1 = 
						conn.prepareStatement("insert into board(title, contents, reg_date, g_no, o_no, depth, user_no)" +
												"values(?,?,now(),?,?,?,?)");
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
				)
		
		
		{
			
			
			pstmt1.setString(1, vo.getTitle());
			pstmt1.setString(2, vo.getContents());
			pstmt1.setInt(3, vo.getG_no());
			pstmt1.setInt(4, vo.getO_no());
			pstmt1.setInt(5, vo.getDepth());
			
			pstmt1.setLong(6, vo.getUser_no());
			
			result = pstmt1.executeUpdate();
			
			ResultSet rs = pstmt2.executeQuery();
			vo.setNo(rs.next() ? rs.getLong(1):null);
			rs.close();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;
	}
	
	public int replyBeforeUpdate(int g_no, int o_no) {
		int result = 0;
		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("update board set o_no = o_no + 1 "
															+ "where g_no = ? and o_no >= ?");
		) {
			pstmt.setInt(1, g_no);
			pstmt.setInt(2, o_no);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("error: " + e);
		} 
		
		return result;
	}
	

}
