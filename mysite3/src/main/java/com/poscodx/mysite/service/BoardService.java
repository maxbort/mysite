package com.poscodx.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.poscodx.mysite.repository.BoardRepository;
import com.poscodx.mysite.vo.BoardVo;

public class BoardService {
	
	private static final int SIZE = 5;
	
	@Autowired
	private BoardRepository boardRepository;
	
	public void addContents(BoardVo vo) {
//		boardRepository.updateOrderNo(vo.getNo());
//		boardRepository.insert(vo);
		if((Integer)(vo.getGno()) != null) {
			boardRepository.updateOrderNo(vo.getGno(), vo.getOno());
		}
		boardRepository.insert(vo);
		
	}
	
	public BoardVo getContents(Long no) {
		
		BoardVo vo = boardRepository.findByNo(no);
		if(vo != null) {
			boardRepository.hitUp(no);
		}
		
		return vo;
	}
	
	public BoardVo getContents(Long no, Long userNo) {
		BoardVo vo = boardRepository.findByNoandUserNo(no, userNo);
		
		return vo;
	}
	
	public void updateContents(BoardVo vo) {
		
		boardRepository.update(vo);
		
	}
	
	public void deleteContents(Long no, Long userNo) {
		boardRepository.delete(no, userNo);
	}
	
	
	public Map<String, Object>getContentsList(int currentPage, String keyword) {
		
		int totalArticle = boardRepository.getTotalArticle(keyword);
		
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("startIndex", (currentPage-1)*5);
		map.put("size", SIZE);
		
		List<BoardVo> list = boardRepository.findAll(keyword,currentPage,SIZE);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("list", list);
		resultMap.put("keyword", keyword);
		
		resultMap.put("totalArticle", totalArticle);
		resultMap.put("listSize", 0);
		resultMap.put("currentPage", 0);
		resultMap.put("beginPage", 0);
		resultMap.put("endPage", total);
		resultMap.put("prevPage", currentPage-1);
		resultMap.put("nextPage", currentPage+1);
		
		
		return map;
	}
	
	
}
