package com.springboot.main;



import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(
      name ="BOARD_SEQ_GENERATOR"
       , sequenceName="BOARD_SEQ"
       , initialValue=1
       , allocationSize=1
   )
@Table(name="board")
public class BoardDTO {
	
	@Id//@Id로 설정하면 primary key로 설정된다.
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
	//특정 데이터베이스에 맞게 자동으로 생성하는 방식, 자동으로 시퀀스 테이블이 생성된다.
	@Column(name="seq")
	private int seq;
	
	@Column(name="id", nullable = false, unique=true, length=30)
	private String id;
	
	
	@Column(name="name", nullable = false, length=30)
	private String name;
	
	
	@Column(name="subject")
	private String subject;
	
	
	@Column(name="content")
	private String content;
	
	
	@CreationTimestamp//엔티티가 생성되는 시점의 시간등록
	private Timestamp logtime;
	
	

}
