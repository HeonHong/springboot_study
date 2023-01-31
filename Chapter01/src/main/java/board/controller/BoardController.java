package board.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import board.bean.BoardDTO;

@RestController //@RestController가 자동으로 JSON변환
public class BoardController {
	
	public BoardController() {
		System.out.println("보드 컨트롤러 생성자 나오냐?");
	}
	
	@GetMapping(value="/board/hello")
	public String hello(String name) {
		return "안녕하세요 " + name + "님의 Board입니다.";
	}

	@GetMapping(value="/board/getBoard")
	public BoardDTO getBoard() {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setSeq(1);
		boardDTO.setName("허균");
		boardDTO.setSubject("홍길동전");
		boardDTO.setContent("동에 번쩍 서에 번쩍");
		boardDTO.setLogtime(new Date());
		
		return boardDTO;
	}
	
	@GetMapping(value="/board/getBoardList")
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setSeq(1);
		boardDTO.setName("허균");
		boardDTO.setSubject("홍길동전");
		boardDTO.setContent("동에 번쩍 서에 번쩍");
		boardDTO.setLogtime(new Date());
		list.add(boardDTO);
		//boardDTO의 주소값이 담긴다. 데이터x
		
		boardDTO = new BoardDTO();//주소값 바꿔주기
		boardDTO.setSeq(2);
		boardDTO.setName("dprlive");
		boardDTO.setSubject("jasmine");
		boardDTO.setContent("Love how you let it slide");
		boardDTO.setLogtime(new Date());
		list.add(boardDTO);
		
		return list;
	}
}
/*
 사용자가 정의한 클래스들이 자동으로 빈으로 등록되기 때문에 스프링 부트에서는 패키지 이름을 주의해서 작성해야 한다.
만약 루트 패키지인 "com.example" 가 아닌 다른 패키지에 클래스를 작성하면 스프링 컨테이너는 해당 클래스를 빈으로 등록하지 않는다. 
다른 패키지의 클래스까지 스캔 대상에 포함 시키려면 메인 클래스에 @ComponentScan을 추가하여 패키지를 직접 지정하면 된다.
*/

/*
 @RestController는 JSP 같은 뷰를 별도로 만들지 않는 대신에 컨트롤러 메소드가 리턴하는 데이터 자체를 클라이언트로 보낸다. 
 클라이언트에 전달되는 데이터는 문자열, DTO, 컬렉션 형태의 자바 객체인데, 자바 객체가 전달되는 경우에는 자동으로 JSON으로 변환하여 처리하게 된다.
 */
