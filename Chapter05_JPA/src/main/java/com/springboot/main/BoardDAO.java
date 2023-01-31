package com.springboot.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface BoardDAO extends JpaRepository<BoardDTO, String>{
public interface BoardDAO extends JpaRepository<BoardDTO, Long>	{
	
	
}
