package com.springboot.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public void write() {
		BoardDTO boardDTO= new BoardDTO();
		boardDTO.setId("dpr");
		boardDTO.setName("wait");
		boardDTO.setSubject("summertights");
		boardDTO.setContent("I love your summer tights");
		
		boardDAO.save(boardDTO); 
		//JpaRepository에 존재. 이렇게만 작성해도 insert가 됨
	}

	@Override
	public List<BoardDTO> getBoardList() {
		
		
		//findAll()은 CRUD repository에 있음(JPA repository의 부모)
		return boardDAO.findAll();
	}

}
