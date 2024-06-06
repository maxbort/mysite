package com.poscodx.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
//	public BoardRepository(SqlSession sqlSession) {
//		this.sqlSession = sqlSession;
//	}
	
	public int insert(BoardVo vo) {
		return sqlSession.insert("board.insert", vo);
		
	}
	
	public void delete(Long no, Long userNo) {
		Map<String, Long> map = new HashMap<String, Long>();
		
		map.put("no", no);
		map.put("userNo", userNo);
		
		sqlSession.delete("board.delete", map);
	}
	
	public List<BoardVo> findAll(String keyword, int currentPage, int size){
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("keyword", keyword);
		map.put("startIndex", (currentPage-1)*5);
		map.put("size", size);
				
		return sqlSession.selectList("board.findAll",map);
	}
	
	
	public void updateOrderNo(int gNo, int oNo) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("gNo", gNo);
		map.put("oNo", oNo);
		sqlSession.update("board.updateOrderNo", map);
	}

	
	public int update(BoardVo vo) {
		return sqlSession.update("board.update", vo);
	}

	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}

	public int hitUp(Long no) {
		return sqlSession.update("board.hitUp", no);
		
	}
	

	
	public BoardVo findByNoandUserNo(Long boardNo, Long userNo) {
		Map<String,Long> map = new HashMap<String,Long>();
		
		
		map.put("no", boardNo);
		map.put("userNo", userNo);
		
		return sqlSession.selectOne("board.findByNoandUserNo", map);
	}
	
	public int getTotalArticle(String keyword) {	
		return sqlSession.selectOne("board.GetTotalArticle", keyword);
	}
}
