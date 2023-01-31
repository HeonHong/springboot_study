package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller//bean 생성 + 생성자 메소드
@RestController
public class HelloController {
	public HelloController() {
		System.out.println("기본생성자");
	}
	
	@GetMapping(value="hello")
	//콜백 메소드. spring container가 어떤 시점이 되면 자동으로 호출하는 메소드
	//@ResponseBody
	//화면에 뿌려줄 때 사용
	//responsebody없이 하면 return에 있는 이름의 jsp를 찾는다. 
	public String hello(String name) {
		return "안녕하세요 " + name + "님";
	}
}
