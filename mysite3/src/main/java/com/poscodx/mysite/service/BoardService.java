package com.poscodx.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.mysite.repository.BoardRepository;
import com.poscodx.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	private static final int SIZE = 5;
	
	@Autowired
	private BoardRepository boardRepository;
	
	public void addContents(BoardVo vo) {
//		boardRepository.updateOrderNo(vo.getNo());
//		boardRepository.insert(vo);
		if(vo.getgNo() != null) {
			boardRepository.updateOrderNo(vo.getgNo(), vo.getoNo());
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
		System.out.println(list);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int endPage = 0;
		if(totalArticle % 5 == 0) {
			endPage = totalArticle / 5;
		} else {
			endPage = totalArticle/5 + 1;
		}
		
		resultMap.put("list", list);
		resultMap.put("keyword", keyword);
		
		resultMap.put("totalArticle", totalArticle);
		resultMap.put("listSize", SIZE);
		resultMap.put("currentPage", currentPage);
		resultMap.put("beginPage", 1);
		resultMap.put("endPage", endPage);
		resultMap.put("prevPage", currentPage-1);
		resultMap.put("nextPage", currentPage+1);
		
		
		return resultMap;
	}
	
	
}
