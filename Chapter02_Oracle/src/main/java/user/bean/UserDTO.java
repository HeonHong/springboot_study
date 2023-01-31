package user.bean;

import lombok.Data;

@Data
public class UserDTO {
	private String name;
	private String id;
	private String pwd;
	/*
	 * chapter02_SpringMaven SungJukInput에서 DTO를 이용하는 것과 다른 이유는 그땐 자바에서 바로 데이터를
	 * 받아야해서 bean을 해놓은 거고 여기선 index에서 옮겨오는 거니까 bean생성할 필요 없음.
	 */
}
