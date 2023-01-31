package user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import user.bean.UserDTO;
import user.service.UserService;


/*여긴 다 콜백 함수다. 요청을 받고 보내는 일만 해줘야한다.
 데이터랑 연결하는 건 서버에서 해줘야한다.Service*/

@Controller
//@Component
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "writeForm") // @RequestMapping(value = "/writeForm")차이
	public String writeForm() {
		System.out.println("asdasdasdasdasdasdasdasdasdasd");
		// return "user/writeForm";
		return "user/writeForm";

	}

	  @PostMapping(value = "write") 
	  @ResponseBody
	  public void write(@ModelAttribute UserDTO userDTO) { 
		  //System.out.println("여기 옴?");
		  userService.write(userDTO);
	  
	  }
	  
		/*
		 * @PostMapping(value = "exist")
		 * 
		 * @ResponseBody public String exist(@ModelAttribute UserDTO userDTO) {
		 * //System.out.println("여기 옴?"); String
		 * idCheck=userService.getUser(userDTO.getId()); return idCheck; }
		 */
	 
	  
	  @GetMapping(value="list")//주소 값으로 받아서 오니까 겟으로
	  public String list() {
		return "user/list";  
	  }
	  
	  
	  @PostMapping(value="getList")
	  @ResponseBody //이걸로 받으면 JSON으로 변환된다.
	  public List<UserDTO> getList() {
		  return userService.getList();
	  }
	  
	  @PostMapping(value="checkId")
	  @ResponseBody
	  public String checkId(@RequestParam String id) {
	  
		  return userService.isExistId(id);
	  }
	  
	  @GetMapping(value="updateForm")
	  public String updateForm() {
		  return "user/updateForm";
	  }
	  
	
	  @PostMapping(value = "getUser")
	  @ResponseBody
	  public UserDTO getUser(@RequestParam String id) {
		  
		  //만약 null값이면 JSON으로 변환이 안된다.
		  return userService.getUser(id);
	  }
	  
	  
	  @PostMapping(value = "update") 
	  @ResponseBody
	  public void update(@ModelAttribute UserDTO userDTO) { 
		  //System.out.println("여기 옴?");
		  userService.update(userDTO);
	  }
	  
	  @GetMapping(value = "deleteForm") 
	  public String deleteForm() { 
		  return "user/deleteForm";
	  }
	  
	  
	  @PostMapping(value = "delete") 
	  @ResponseBody
	  public void delete(@RequestParam String id) { 
		  //System.out.println("여기 옴?");
		  userService.delete(id);
	  }
	  
	 
}
