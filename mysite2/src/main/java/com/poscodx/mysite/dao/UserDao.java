package com.poscodx.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poscodx.mysite.vo.UserVo;

public class UserDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.0.198:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}
	
	public int insert(UserVo vo) {
		int result = 0;
		
		try(
				Connection conn = getConnection();
				PreparedStatement pstmt1 = conn.prepareStatement("insert user values(null,?,?,password(?),? ,current_date())");
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
				
		){
	
			pstmt1.setString(1, vo.getName());
			pstmt1.setString(2, vo.getEmail());
			pstmt1.setString(3, vo.getPassword());
			pstmt1.setString(4, vo.getGender());
			//pstmt1.setString(5, vo.getJoinDate());
			
			//5. SQL 실행
			result = pstmt1.executeUpdate();
			
			ResultSet rs = pstmt2.executeQuery();
			vo.setNo(rs.next() ? rs.getLong(1): null);
			rs.close();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		return result;
	
	}
	

	public UserVo findByNoAndPassword(String email, String password) {
		UserVo result = null;
		
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt1 = conn.prepareStatement("select no, name from user where email = ? and password = password(?)");
				
		) {
	
			pstmt1.setString(1, email);
			pstmt1.setString(2, password);

						
			ResultSet rs = pstmt1.executeQuery();
			if(rs.next()) {
				Long no =rs.getLong(1);
				String name = rs.getString(2);
				
				result = new UserVo();
				result.setNo(no);
				result.setName(name);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		return result;
	}
	
}