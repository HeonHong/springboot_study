package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //스프링 부트로 만든 어플리케이션 시작 클래스임을 의미한다.
public class HelloTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloTestApplication.class, args);
		
		System.out.println("Hello Spring Boot");
	}

}


/*
Spring Boot
- 스프링 프레임워크의 서브 프로젝트로 만들어졌다.
- 스프링 부트는 스프링 프레임워크를 사용 가능한 상태로 만들어주는 도구 정도로 이해할 수 있다.
- 스프링 부트는 다른 프레임워크처럼 커맨드 도구를 제공하고 톰캣이나 제티 같은 내장 서버를 통해 
  복잡한 설정과 실행을 간소화했다.
*/
/*
스프링 부트 퀵스타트
스프링 부트로 프로젝트를 생성하면 스프링을 비롯한 어떤 라이브러리도 개발자가 신경 쓸 필요가 없다. 스프링 부트가 모든 라이브러리를 자동으로 다운로드하고 관리해준다. 스프링 컨테이너를 위한 XML 환경설정 파일 역시 작성하지 않는데, 이는 스프링 부트가 기본적으로 모든 빈(Bean) 설정을 XML이 아닌 어노테이션으로 처리하기 때문이다.

생성되는 프로젝트를 웹 프로젝트로 패키징하여 실행하려면 WAR로 설정해야 하지만 스프링 부트는 웹 애플리케이션도 JAR 파일로 패키징하여 실행할 수 있다.
 */
/* 
 스프링 부트 애플리케이션 실행
- 스프링 부트로 만든 애플리케이션을 실행하기 위해서는 메인 클래스를 작성해야 한다.
- 프로젝트를 만들면 이미 src/main/java 폴더에 [프로젝트명+Application.java] 라는 main() 메소드를
  가진 파일이 만들어진다.
 */