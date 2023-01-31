package com.springboot.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController// responsebody 대신 json으로 바꿔준다
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//@RequestMapping(value="/board/write")
	@GetMapping(value="/board/write")
	public String write() {
		boardService.write();
		return "게시판 등록 성공";
	}
	
	@GetMapping(value="/board/getBoardList")
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> list = boardService.getBoardList();
		return list;
	}
	
	
	
	
	
}
